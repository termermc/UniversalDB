package net.termer.udb;

/**
 * Utility class to retrieve Database field data types
 * @author termer
 * @since 1.0
 */
public class ResultFieldType {
	/**
	 * Database INT, TINYINT, BIGINT
	 * Java int
	 * @since 1.0
	 */
	public static int INT = 0;
	
	/**
	 * Database CHAR, VARCHAR, LONGVARCHAR, etc.
	 * Java String
	 * @since 1.0
	 */
	public static int TEXT = 1;
	
	/**
	 * Database BOOLEAN
	 * Java boolean
	 * @since 1.0
	 */
	public static int BOOLEAN = 2;
	
	/**
	 * Database DOUBLE
	 * Java double
	 * @since 1.0
	 */
	public static int DOUBLE = 3;
	
	/**
	 * Database FLOAT
	 * Java float
	 * @since 1.0
	 */
	public static int FLOAT = 4;
	
	/**
	 * Database NULL
	 * Java null
	 * @since 1.0
	 */
	public static int NULL = 5;
	
	/**
	 * Database OBJECT
	 * Java Object
	 * @since 1.0
	 */
	public static int OBJECT = 6;
	
	/**
	 * Database BYTE
	 * Java byte
	 * @since 1.0
	 */
	public static int BYTE = 7;
	
	/**
	 * Other, database specific type.
	 * In ResultField, it should be
	 * accessed using getObject().
	 * Java Object
	 * @since 1.0
	 */
	public static int OTHER = 8;
	
	/**
	 * Database ARRAY
	 * Java array object
	 * @since 1.0
	 */
	public static int ARRAY = 9;
}
