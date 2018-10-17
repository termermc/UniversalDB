package net.termer.udb;

/**
 * Database ARRAY type class
 * @author termer
 * @since 1.0
 */
public class Array {
	Object ARRAY = null;
	
	/**
	 * Set up values
	 * @param array the Java array
	 * @since 1.0
	 */
	public Array(Object array) {
		ARRAY = array;
	}
	
	/**
	 * Returns this array's contents as a Java array.
	 * Must be cast to an array to be used.
	 * Example: (String[]) getArray()
	 * @return this array's contents
	 * @since 1.0
	 */
	public Object getArray() {
		return ARRAY;
	}
}
