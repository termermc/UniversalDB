package net.termer.udb.sql.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariDataSource;

import net.termer.udb.sql.SQLDatabaseAdapter;
import net.termer.udb.sql.SQLDatabaseUtils;
import net.termer.udb.sql.SQLQueryResult;

/**
 * Class to setup a PostgreSQL connection and interact with it
 * @author termer
 * @since 1.0
 */
public class HikariPostgreSQLDatabaseAdapter implements SQLDatabaseAdapter {
	// Whether the Driver has been loaded
	private boolean _DRIVER_LOADED_ = false;
	
	// Whether the database should reconnect in the case of a connection error
	private boolean _RECONNECT_ = false;
	
	// Database name
	private String _DBNAME_ = null;
	
	// Database address
	private String _ADDRESS_ = null;
	
	// Database port
	private int _PORT_ = 5432;
	
	// Database user
	private String _USER_ = null;
	
	// Database password
	private String _PASSWORD_ = null;
	
	// HikariCP data source
	private HikariDataSource _HIKARI_ = null;
	
	/**
	 * Stores values to use for connecting to the database
	 * @param address the address of the database
	 * @param dbname the name of the database
	 * @param user the user to log into
	 * @param password the password for the user
	 * @since 1.0
	 */
	public HikariPostgreSQLDatabaseAdapter(String address, String dbname, String user, String password) {
		// Store values
		_ADDRESS_ = address;
		_DBNAME_ = dbname;
		_USER_ = user;
		_PASSWORD_ = password;
	}
	
	/**
	 * Stores values to use for connecting to the database
	 * @param address the address of the database
	 * @param port the database port
	 * @param dbname the name of the database
	 * @param user the user to log into
	 * @param password the password for the user
	 * @since 1.0
	 */
	public HikariPostgreSQLDatabaseAdapter(String address, int port, String dbname, String user, String password) {
		// Store values
		_ADDRESS_ = address;
		_PORT_ = port;
		_DBNAME_ = dbname;
		_USER_ = user;
		_PASSWORD_ = password;
	}
	
	/**
	 * Sets up a connection pool to the specified PostgreSQL database
	 * @throws ClassNotFoundException if the PostgreSQL driver class is not found
	 * @throws IllegalAccessException if you do not have access to the PostgreSQL driver class
	 * @throws InstantiationException if instantiating a new PostgreSQL driver class fails
	 * @throws SQLException if establishing a connection to the database fails
	 * @since 1.0
	 */
	public void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Setup DriverManager
		if(!_DRIVER_LOADED_) {
			Class.forName("org.postgresql.Driver").newInstance();
			_DRIVER_LOADED_ = true;
		}
		
		// Create HikariDataSource
		_HIKARI_ = new HikariDataSource();
		_HIKARI_.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
		_HIKARI_.addDataSourceProperty("serverName", _ADDRESS_);
		_HIKARI_.addDataSourceProperty("port", Integer.toString(_PORT_));
		_HIKARI_.addDataSourceProperty("databaseName", _DBNAME_);
		_HIKARI_.addDataSourceProperty("user", _USER_);
		_HIKARI_.addDataSourceProperty("password", _PASSWORD_);
		_HIKARI_.addDataSourceProperty("cachePrepStmts", "true");
		_HIKARI_.addDataSourceProperty("prepStmtCacheSize", "250");
		_HIKARI_.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		// Create pool by requesting a Connection
		getNextConnection();
	}

	public void disconnect() throws SQLException {
		_HIKARI_.close();
	}
	
	/**
	 * Executes the specified SQL statement and returns the results
	 * @param statement the SQL statement to execute
	 * @return the results of the SQL statement
	 * @throws SQLException if something goes wrong with the database connection
	 * @throws ClassNotFoundException if reconnection to database fails
	 * @throws IllegalAccessException if reconnection to database fails
	 * @throws InstantiationException if reconnection to database fails
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(String statement) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		SQLQueryResult result = null;
		
		// Creates the statement
		Statement stmt = getNextConnection().createStatement();
		
		try {
			// Check if the statement succeeds
			if(stmt.execute(statement)) {
				result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception e) {
			// If reconnection is enabled, reconnect and resend the query
			if(_RECONNECT_) {
				disconnect();
				connect();
				// Check if the statement succeeds
				if(stmt.execute(statement)) {
					result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
				}
			}
		}
		
		return result;
	}

	/**
	 * Returns the next available connection from the connection pool.
	 * If an error occurs, null will be returned.
	 * @deprecated Does not return proper exceptions, use getNextConnection() instead
	 * @return the next available connection
	 * @since 1.0
	 */
	@Deprecated
	public Connection getConnection() {
		Connection conn = null; 
		try {
			conn = _HIKARI_.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Returns the next available connection from the connection pool
	 * @return the next available connection from the connection pool
	 * @throws SQLException if getting the connection fails
	 * @since 1.1
	 */
	public Connection getNextConnection() throws SQLException {
		return _HIKARI_.getConnection();
	}
	
	public SQLQueryResult executeQuery(PreparedStatement statement) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		SQLQueryResult result = null;
		
		// Creates the statement
		PreparedStatement stmt = statement;
		
		try {
			// Check if the statement succeeds
			if(stmt.execute()) {
				result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
			}
		} catch(SQLException e) {
			throw e;
		} catch(Exception e) {
			// If reconnection is enabled, reconnect and resend the query
			if(_RECONNECT_) {
				disconnect();
				connect();
				// Check if the statement succeeds
				if(stmt.execute()) {
					result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Returns the HikariDataSource object for this pool
	 * @return the HikariDataSource object for this pool
	 * @since 1.1
	 */
	public HikariDataSource getDataSource() {
		return _HIKARI_;
	}
	
	/**
	 * Sets the maximum amount of open connections allowed in this pool
	 * @param size the maximum pool size
	 * @since 1.1
	 */
	public void setMaximumPoolSize(int size) {
		_HIKARI_.setMaximumPoolSize(size);
	}
	
	/**
	 * Returns the maximum amount of open connections allowed in this pool
	 * @return the maximum pool size
	 * @since 1.1
	 */
	public int getMaximumPoolSize() {
		return _HIKARI_.getMaximumPoolSize();
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return getNextConnection().prepareStatement(sql);
	}
	
	public void setReconnectOnError(boolean reconnect) {
		_RECONNECT_ = reconnect;
	}
	
	public boolean getReconnectOnError() {
		return _RECONNECT_;
	}
}
