package net.termer.udb.sql;

import java.sql.ResultSet;

/**
 * Interface to be implemented by classes using the JDBC API
 * @author termer
 * @since 1.0
 */
public interface JDBCElement {
	/**
	 * Returns the JDBC ResultSet corresponding to this SQL element
	 * @return the JDBC ResultSet
	 * @since 1.0
	 */
	public ResultSet getJDBCResultSet();
}
