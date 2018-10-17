package net.termer.udb.sql;

import java.sql.ResultSet;

import net.termer.udb.Array;
import net.termer.udb.ResultColumn;
import net.termer.udb.ResultField;
import net.termer.udb.ResultRow;

/**
 * SQL implementation of ResultField
 * @author termer
 * @since 1.0
 */
public class SQLResultField extends ResultField implements JDBCElement {
	private ResultSet RESULT_SET = null;
	
	/**
	 * Creates a new ResultField with an INT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(int value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a TEXT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(String value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a TEXT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(char value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a BOOLEAN value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(boolean value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a DOUBLE value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(double value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a FLOAT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(float value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with a NULL value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with an OBJECT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(Object value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	/**
	 * Creates a new ResultField with an ARRAY value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the SQLResultColumn this field is under
	 * @param index the index of this field in its row
	 * @param rs this JDBCElement's JDBC ResultSet
	 * @since 1.0
	 */
	public SQLResultField(Array value, SQLResultRow row, SQLResultColumn column, int index, ResultSet rs) {
		super(value,row,column,index);
		RESULT_SET = rs;
	}
	
	public ResultSet getJDBCResultSet() {
		return RESULT_SET;
	}
	
	/**
	 * Returns the SQLResultRow that contains this SQLResultField
	 * @return the containing SQLResultRow
	 * @since 1.0
	 */
	public SQLResultRow getRow() {
		return (SQLResultRow)super.getRow();
	}
	
	/**
	 * Sets the SQLResultRow that contains this SQLResultField
	 * @param row the SQLResultRow that contains this field
	 * @since 1.0
	 */
	public void setRow(SQLResultRow row) {
		super.setRow(row);
	}
	
	/**
	 * Sets the SQLResultRow that contains this SQLResultField
	 * If the provided value is not an SQLResultRow,
	 * the value will be rejected.
	 * @param row the SQLResultRow that contains this field
	 * @since 1.0
	 */
	@Deprecated
	public void setRow(ResultRow row) {
		if(row.getClass()==SQLResultRow.class) {
			super.setRow(row);
		}
	}
	
	/**
	 * Returns the SQLResultColumn that this SQLResultField is under
	 * @return this SQLResultField's column
	 * @since 1.0
	 */
	public SQLResultColumn getColumn() {
		return (SQLResultColumn)super.getColumn();
	}
	
	/**
	 * Sets the SQLResultColumn that this SQLResultField is under
	 * @param column the SQLResultColumn this field is under
	 * @since 1.0
	 */
	public void setColumn(SQLResultColumn column) {
		super.setColumn(column);
	}
	
	/**
	 * Sets the ResultColumn that this field is under
	 * If the provided value is not an SQLResultColumn,
	 * the value will be rejected.
	 * @param column the ResultColumn
	 * @deprecated setColumn(SQLResultColumn) should be used for SQLResultField
	 * @since 1.0
	 */
	@Deprecated
	public void setColumn(ResultColumn column) {
		if(column.getClass()==SQLResultColumn.class) {
			super.setColumn(column);
		}
	}
}
