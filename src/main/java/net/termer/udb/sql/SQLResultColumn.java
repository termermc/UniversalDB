package net.termer.udb.sql;

import java.sql.ResultSet;

import net.termer.udb.ResultColumn;

/**
 * SQL implementation of ResultColumn
 * @author termer
 * @since 1.0
 */
public class SQLResultColumn extends ResultColumn implements JDBCElement {
	private ResultSet _RESULT_SET_ = null;
	
	/**
	 * Sets up values
	 * @param rs the JDBC ResultSet corresponding to this column
	 * @param index the index of this column
	 * @param name the name of this column
	 * @param type the ResultFieldType of this column
	 * @since 1.0
	 */
	public SQLResultColumn(ResultSet rs, int index, String name, int type) {
		super(index, name, type);
		_RESULT_SET_ = rs;
	}
	
	public ResultSet getJDBCResultSet() {
		return _RESULT_SET_;
	}
}
