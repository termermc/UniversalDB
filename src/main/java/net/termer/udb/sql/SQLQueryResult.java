package net.termer.udb.sql;

import java.sql.ResultSet;

import net.termer.udb.QueryResult;

public class SQLQueryResult extends QueryResult implements JDBCElement {
	private ResultSet RESULT_SET = null;
	
	/**
	 * Sets up values
	 * @param columns this result's columns
	 * @param rows this result's rows
	 * @param rs the JDBC ResultSet corresponding to this result
	 */
	public SQLQueryResult(SQLResultColumn[] columns, SQLResultRow[] rows, ResultSet rs) {
		super(columns, rows);
		RESULT_SET = rs;
	}

	public ResultSet getJDBCResultSet() {
		return RESULT_SET;
	}
	
	/**
	 * Returns this SQLQueryResult's columns
	 * @return this results columns
	 * @since 1.0
	 */
	public SQLResultColumn[] getColumns() {
		return (SQLResultColumn[])super.getColumns();
	}
	
	/**
	 * Returns this SQLQueryResult's rows
	 * @return this results rows
	 * @since 1.0
	 */
	public SQLResultRow[] getRows() {
		return (SQLResultRow[])super.getRows();
	}
}
