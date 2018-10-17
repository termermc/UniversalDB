package net.termer.udb;

/**
 * Database result row class
 * @author termer
 * @since 1.0
 */
public class ResultRow {
	private ResultField[] FIELDS = null;
	private int INDEX = 0;
	
	/**
	 * Sets up values
	 * @param fields the fields in this row
	 * @since 1.0
	 */
	public ResultRow(ResultField[] fields, int index) {
		FIELDS = fields;
		INDEX = index;
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