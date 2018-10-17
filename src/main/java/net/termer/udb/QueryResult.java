package net.termer.udb;

/**
 * Class to be returned as a database query's result
 * @author termer
 * @since 1.0
 */
public class QueryResult {
	private ResultColumn[] _COLUMNS_ = null;
	private ResultRow[] _ROWS_ = null;
	
	/**
	 * Sets up values
	 * @param columns this result's columns
	 * @param rows this result's rows
	 * @since 1.0
	 */
	public QueryResult(ResultColumn[] columns, ResultRow[] rows) {
		_COLUMNS_ = columns;
		_ROWS_ = rows;
	}
	
	/**
	 * Returns this QueryResult's columns
	 * @return this results columns
	 * @since 1.0
	 */
	public ResultColumn[] getColumns() {
		return _COLUMNS_;
	}
	
	/**
	 * Returns this QueryResult's rows
	 * @return this results rows
	 * @since 1.0
	 */
	public ResultRow[] getRows() {
		return _ROWS_;
	}
	
	/**
	 * Returns a visual representation of this table as a String.
	 * Equivalent of calling DatabaseUtils.toVisualTable().
	 * @return a visual representation of this table as a String
	 * @since 1.0
	 */
	public String toString() {
		return DatabaseUtils.toVisualTable(this);
	}
	
	/**
	 * Returns this table as an HTML table.
	 * Equivalent of calling DatabaseUtils.toHTMLTable().
	 * @return an HTML version of this table
	 * @since 1.0
	 */
	public String toHTMLTable() {
		return DatabaseUtils.toHTMLTable(this);
	}
}
