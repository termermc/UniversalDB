package net.termer.udb;

/**
 * Database result row class
 * @author termer
 * @since 1.0
 */
public class ResultRow {
	private ResultField[] FIELDS = null;
	private int INDEX = 0;
	private ResultColumn[] COLUMNS = {};
	
	/**
	 * Sets up values
	 * @param fields the fields in this row
	 * @param indes the index of this row
	 * @param columns list of columns
	 * @since 1.1
	 */
	public ResultRow(ResultField[] fields, int index, ResultColumn[] columns) {
		FIELDS = fields;
		INDEX = index;
		COLUMNS = columns;
	}
	
	/**
	 * Returns the field corresponding to the provided column
	 * @param column the column name for the field
	 * @return the field corresponding to the column name
	 * @since 1.1
	 */
	public ResultField getField(String column) {
		ResultField res = null;
		for(int i = 0; i < FIELDS.length; i++) {
			if(COLUMNS[i].getName().equals(column)) {
				res = FIELDS[i];
				break;
			}
		}
		return res;
	}
	
	/**
	 * Returns all ResultFields in this row
	 * @return all fields in this row
	 * @since 1.0
	 */
	public ResultField[] getFields() {
		return FIELDS;
	}
	
	/**
	 * Returns this row's vertical index on the table
	 * @return this row's index
	 * @since 1.0
	 */
	public int getIndex() {
		return INDEX;
	}
}