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

	public void connect() throws Exception {
		// Setup DriverManager
		Class.forName("org.postgresql.Driver").newInstance();
		
		// Generate connection URL
		String connAddr = "jdbc:postgresql://"+_ADDRESS_;
		if(_PORT_>-1) connAddr+=":"+Integer.toString(_PORT_);
		connAddr+="/"+_DBNAME_;
		
		// Get connection to database
		conn = DriverManager.getConnection(connAddr, _USER_, _PASSWORD_);
	}

	public void disconnect() throws Exception {
		conn.close();
	}
	
	/**
	 * Executes the specified SQL statement and returns the results
	 * @param statement the SQL statement to execute
	 * @return the results of the SQL statement
	 * @throws SQLException if something goes wrong with the database connection
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(String statement) throws SQLException {
		SQLQueryResult result = null;
		
		// Creates the statement
		Statement stmt = conn.createStatement();
		
		// Check if the statement succeeds
		if(stmt.execute(statement)) {
			result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
		}
		
		return result;
	}

	public Connection getConnection() {
		return conn;
	}
	
	public SQLQueryResult executeQuery(PreparedStatement statement) throws SQLException {
		SQLQueryResult result = null;
		
		// Creates the statement
		PreparedStatement stmt = statement;
		
		// Check if the statement succeeds
		if(stmt.execute()) {
			result = SQLDatabaseUtils.ConvertResultSetToSQLQueryResult(stmt.getResultSet());
		}
		
		return result;
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}
	
	public void setReconnectOnError(boolean reconnect) {
		_RECONNECT_ = reconnect;
	}
	
	public boolean getReconnectOnError() {
		return _RECONNECT_;
	}
}
