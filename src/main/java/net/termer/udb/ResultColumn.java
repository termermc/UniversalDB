package net.termer.udb;

/**
 * Database result column
 * @author termer
 * @since 1.0
 */
public class ResultColumn {
	
	// Column index
	// Starts at 1, next 2, etc
	private int _INDEX_ = 0;
	
	// Column name
	private String _NAME_ = null;
	
	// Column data type
	private int _TYPE_ = -1;
	
	/**
	 * Setup values
	 * @param index the column index
	 * @param name the column name
	 * @param type the column's data type
	 * @since 1.0
	 */
	public ResultColumn(int index, String name, int type) {
		_INDEX_ = index;
		_NAME_ = name;
		_TYPE_ = type;
	}
	
	/**
	 * Returns the column's index
	 * Starts at 1, next 2, etc
	 * @return the column's index
	 * @since 1.0
	 */
	public int getIndex() {
		return _INDEX_;
	}
	
	/**
	 * Returns the column's name
	 * @return the column's name
	 * @since 1.0
	 */
	public String getName() {
		return _NAME_;
	}
	
	/**
	 * Returns the column's data type
	 * For instance, if it were an INT, it would be equal to ResultFieldType.INT
	 * @return the column's data type
	 * @since 1.0
	 */
	public int getDataType() {
		return _TYPE_;
	}
}
