package net.termer.udb.sql.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import net.termer.udb.sql.SQLDatabaseAdapter;
import net.termer.udb.sql.SQLDatabaseUtils;
import net.termer.udb.sql.SQLQueryResult;

/**
 * Class to setup a PostgreSQL connection and interact with it
 * @author termer
 * @since 1.0
 */
public class PostgreSQLDatabaseAdapter implements SQLDatabaseAdapter {
	// Whether the Driver has been loaded
	private boolean _DRIVER_LOADED_ = false;
	
	// Whether the database should reconnect in the case of a connection error
	private boolean _RECONNECT_ = false;
	
	// Database connection
	private Connection conn = null;
	
	// Database name
	private String _DBNAME_ = null;
	
	// Database address
	private String _ADDRESS_ = null;
	
	// Database port
	private int _PORT_ = -1;
	
	// Database user
	private String _USER_ = null;
	
	// Database password
	private String _PASSWORD_ = null;
	
	/**
	 * Stores values to use for connecting to the database
	 * @param address the address of the database
	 * @param dbname the name of the database
	 * @param user the user to log into
	 * @param password the password for the user
	 * @since 1.0
	 */
	public PostgreSQLDatabaseAdapter(String address, String dbname, String user, String password) {
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
	public PostgreSQLDatabaseAdapter(String address, int port, String dbname, String user, String password) {
		// Store values
		_ADDRESS_ = address;
		_PORT_ = port;
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
	public PostgreSQLDatabaseAdapter(String address, String port, String dbname, String user, String password) {
		// Store values
		_ADDRESS_ = address;
		_PORT_ = Integer.parseInt(port);
		_DBNAME_ = dbname;
		_USER_ = user;
		_PASSWORD_ = password;
	}

	public void connect() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Setup DriverManager
		if(!_DRIVER_LOADED_) {
			Class.forName("org.postgresql.Driver").newInstance();
			_DRIVER_LOADED_ = true;
		}
		
		// Generate connection URL
		String connAddr = "jdbc:postgresql://"+_ADDRESS_;
		if(_PORT_>-1) connAddr+=":"+Integer.toString(_PORT_);
		connAddr+="/"+_DBNAME_;
		
		// Get connection to database
		conn = DriverManager.getConnection(connAddr, _USER_, _PASSWORD_);
	}

	public void disconnect() throws SQLException {
		getConnection().close();
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
		Statement stmt = getConnection().createStatement();
		
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

	public Connection getConnection() {
		return conn;
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
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return getConnection().prepareStatement(sql);
	}
	
	public void setReconnectOnError(boolean reconnect) {
		_RECONNECT_ = reconnect;
	}
	
	public boolean getReconnectOnError() {
		return _RECONNECT_;
	}
}
