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
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(String statement) throws SQLException;
	
	/**
	 * Executes the specified JDBC PreparedStatement and returns the results
	 * JDBC PrepatedStatements allow easy manipulation of SQL statements and
	 * minimize the risk of SQL injection attacks. To obtain a new Prepared Statement,
	 * call prepareStatement(sql).
	 * @param statement the JDBC PreparedStatement to execute
	 * @return the results of the SQL statement
	 * @throws SQLException if something goes wrong with the database connection
	 * @since 1.0
	 */
	public SQLQueryResult executeQuery(PreparedStatement statement) throws SQLException;
	
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