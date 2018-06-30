package net.termer.udb.sql;

import java.sql.ResultSet;

import net.termer.udb.ResultRow;

/**
 * SQL implementation of ResultRow
 * @author termer
 * @since 1.0
 */
public class SQLResultRow extends ResultRow implements JDBCElement {
	private ResultSet RESULT_SET = null;
	
	/**
	 * Sets up values
	 * @param fields the fields in this row
	 * @param rs the JDBC ResultSet corresponding to this row
	 */
	public SQLResultRow(SQLResultField[] fields, int index, ResultSet rs) {
		super(fields,index);
		RESULT_SET = rs;
	}
	
	public ResultSet getJDBCResultSet() {
		return RESULT_SET;
	}
	
	public SQLResultField[] getFields() {
		return (SQLResultField[])super.getFields();
	}

}
