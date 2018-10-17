package net.termer.udb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.termer.udb.DatabaseAdapter;

/**
 * Adapter class for SQL databases
 * @author termer
 * @since 1.0
 */
public interface SQLDatabaseAdapter extends DatabaseAdapter {
	/**
	 * Executes the specified SQL statement and returns the
	 * results as an SQLQueryResult object
	 * @param statement the SQL statement to execute
	 * @return the result as an SQLQueryResult object
	 * @throws ClassNotFoundException if reconnection to database fails
	 * @throws IllegalAccessException if reconnection to database fails
	 * @throws InstantiationException if reconnection to database fails
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(String statement) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	/**
	 * Sets whether the database should reconnect if there is a connection error
	 * when executing a query, as well as if the query should be re-sent.
	 * @param whether the database should reconnect when a connection error occurs
	 * @since 1.1
	 */
	public void setReconnectOnError(boolean reconnect);
	
	/**
	 * Returns whether the database should reconnect if there is a connection error
	 * when executing a query, as well as if the query should be re-sent.
	 * @return whether the database should reconnect when a connection error occurs
	 * @since 1.1
	 */
	public boolean getReconnectOnError();
	
	/**
	 * Executes the specified JDBC PreparedStatement and returns the results
	 * JDBC PrepatedStatements allow easy manipulation of SQL statements and
	 * minimize the risk of SQL injection attacks. To obtain a new Prepared Statement,
	 * call prepareStatement(sql).
	 * @param statement the JDBC PreparedStatement to execute
	 * @return the results of the SQL statement
	 * @throws SQLException if something goes wrong with the database connection
	 * @throws ClassNotFoundException if reconnection to database fails
	 * @throws IllegalAccessException if reconnection to database fails 
	 * @throws InstantiationException if reconnection to database fails
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(PreparedStatement statement) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
	
	/**
	 * Returns the SQL connection to this database, if any
	 * @return the SQL connection to this database
	 * @since 1.0
	 */
	public Connection getConnection();
	
	/**
	 * Creates a new prepared statement for this database. To execute, call executeQuery()
	 * with the returned PreparedStatement.
	 * @param sql the SQL for the statement
	 * @return the PreparedStatement
	 * @since 1.0
	 */
	public PreparedStatement prepareStatement(String sql) throws SQLException;
}