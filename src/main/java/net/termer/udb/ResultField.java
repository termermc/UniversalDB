package net.termer.udb;

/**
 * Database result field class
 * @author termer
 * @since 1.0
 */
public class ResultField {
	private int INT = -1;
	private String TEXT = null;
	private boolean BOOLEAN = false;
	private double DOUBLE = -0.1;
	private float FLOAT = -1F;
	private Object OBJECT = null;
	private byte BYTE = 0;
	private boolean NULL = false;
	private Array ARRAY = null;
	
	private int TYPE = -1;
	
	private ResultRow ROW = null;
	private ResultColumn COLUMN = null;
	private int INDEX = 0;
	
	/**
	 * Creates a new ResultField with an INT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(int value, ResultRow row, ResultColumn column, int index) {
		INT = value;
		TYPE = ResultFieldType.INT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a TEXT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(String value, ResultRow row, ResultColumn column, int index) {
		TEXT = value;
		TYPE = ResultFieldType.TEXT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a TEXT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(char value, ResultRow row, ResultColumn column, int index) {
		TEXT = ""+value;
		TYPE = ResultFieldType.TEXT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a BOOLEAN value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(boolean value, ResultRow row, ResultColumn column, int index) {
		BOOLEAN = value;
		TYPE = ResultFieldType.BOOLEAN;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a DOUBLE value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(double value, ResultRow row, ResultColumn column, int index) {
		DOUBLE = value;
		TYPE = ResultFieldType.DOUBLE;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a FLOAT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(float value, ResultRow row, ResultColumn column, int index) {
		FLOAT = value;
		TYPE = ResultFieldType.FLOAT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a NULL value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(ResultRow row, ResultColumn column, int index) {
		NULL = true;
		TYPE = ResultFieldType.NULL;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with an OBJECT value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(Object value, ResultRow row, ResultColumn column, int index) {
		OBJECT = value;
		TYPE = ResultFieldType.OBJECT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a BYTE value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(byte value, ResultRow row, ResultColumn column, int index) {
		BYTE = value;
		TYPE = ResultFieldType.OBJECT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Creates a new ResultField with a ARRAY value
	 * @param value the value
	 * @param row the ResultRow that contains this field
	 * @param column the ResultColumn this field is under
	 * @param index the index of this field in its row
	 * @since 1.0
	 */
	public ResultField(Array value, ResultRow row, ResultColumn column, int index) {
		ARRAY = value;
		TYPE = ResultFieldType.OBJECT;
		ROW = row;
		COLUMN = column;
		INDEX = index;
	}
	
	/**
	 * Indicates that this ResultField is
	 * an OTHER, and not an OBJECT.
	 * @since 1.0
	 */
	public void setOther() {
		TYPE = ResultFieldType.OTHER;
	}
	
	/**
	 * Returns the type of this ResultField
	 * For example, if it is an INT, it would be equal to ResultFieldType.INT
	 * @return the type of this ResultField
	 * @since 1.0
	 */
	public int getType() {
		return TYPE;
	}
	
	/**
	 * Returns whether this field's value has been marked NULL
	 * @return if this field is NULL
	 * @since 1.0
	 */
	public boolean isNull() {
		return NULL;
	}
	
	/**
	 * Returns the ResultRow that contains this ResultField
	 * @return the containing ResultRow
	 * @since 1.0
	 */
	public ResultRow getRow() {
		return ROW;
	}
	
	/**
	 * Sets the ResultRow that contains this ResultField
	 * @param row the ResultRow that contains this field
	 * @since 1.0
	 */
	public void setRow(ResultRow row) {
		ROW = row;
	}
	
	/**
	 * Returns the ResultColumn that this ResultField is under
	 * @return this ResultField's column
	 * @since 1.0
	 */
	public ResultColumn getColumn() {
		return COLUMN;
	}
	
	/**
	 * Sets the ResultColumn that this ResultField is under
	 * @param column the ResultColumn this field is under
	 * @since 1.0
	 */
	public void setColumn(ResultColumn column) {
		COLUMN = column;
	}
	
	/**
	 * Returns the index of this ResultField in its row
	 * @return the index of this ResultField in its row
	 * @since 1.0
	 */
	public int getIndex() {
		return INDEX;
	}
	
	/**
	 * Sets this ResultField's row index
	 * @param index this field's row index
	 * @since 1.0
	 */
	public void setIndex(int index) {
		INDEX = index;
	}
	
	/**
	 * Returns this field's INT value.
	 * Returns -1 if this field's type is not INT
	 * @return this field's INT value
	 * @since 1.0
	 */
	public int getIntValue() {
		return INT;
	}
	
	/**
	 * Returns this field's TEXT value.
	 * Returns null if this field's type is not TEXT
	 * @return this field's TEXT value
	 * @since 1.0
	 */
	public String getTextValue() {
		return TEXT;
	}
	
	/**
	 * Returns this field's BOOLEAN value.
	 * Returns false if this field's type is not BOOLEAN
	 * (since it returns false, its type should be checked
	 * with getType() to determine if it is a BOOLEAN)
	 * @return this field's BOOLEAN value
	 * @since 1.0
	 */
	public boolean getBooleanValue() {
		return BOOLEAN;
	}
	
	/**
	 * Returns this field's DOUBLE value.
	 * Returns -0.1 if this field's type is not DOUBLE
	 * @return this field's DOUBLE value
	 * @since 1.0
	 */
	public double getDoubleValue() {
		return DOUBLE;
	}
	
	/**
	 * Returns this field's FLOAT value.
	 * Returns -1F if this field's type is not FLOAT
	 * @return this field's FLOAT value
	 * @since 1.0
	 */
	public float getFloatValue() {
		return FLOAT;
	}
	
	/**
	 * Returns this field's OBJECT or OTHER value.
	 * Returns null if this field's type is not OBJECT or OTHER
	 * @return this field's OBJECT or OTHER value
	 * @since 1.0
	 */
	public Object getObjectValue() {
		return OBJECT;
	}
	
	/**
	 * Returns this field's BYTE value.
	 * Returns null if this field's type is not BYTE
	 * @return this field's BYTE value
	 * @since 1.0
	 */
	public byte getByteValue() {
		return BYTE;
	}
	
	/**
	 * Returns this field's NULL value.
	 * Always returns null, so you should use isNull()
	 * to determine if this field is NULL
	 * @return null
	 * @since 1.0
	 */
	public Object getNullValue() {
		return null;
	}
	
	public Array getArrayValue() {
		return ARRAY;
	}
	
	/**
	 * Returns this field's values represented by a String
	 * For example, the int value of 1 would be returned as "1"
	 * @return this field's value as a String
	 * @since 1.0
	 */
	public String getValueAsString() {
		String res = "NULL";
		
		if(TYPE==ResultFieldType.BOOLEAN) {
			Boolean.toString(BOOLEAN).toUpperCase();
		} else if(TYPE==ResultFieldType.BYTE) {
			Byte.toString(BYTE);
		} else if(TYPE==ResultFieldType.DOUBLE) {
			res = Double.toString(DOUBLE);
		} else if(TYPE==ResultFieldType.FLOAT) {
			res = Float.toString(FLOAT);
		} else if(TYPE==ResultFieldType.INT) {
			res = Integer.toString(INT);
		} else if(TYPE==ResultFieldType.OTHER || TYPE==ResultFieldType.OBJECT) {
			if(OBJECT!=null) {
				res = OBJECT.toString();
			}
		} else if(TYPE==ResultFieldType.TEXT) {
			res = TEXT;
		} else if(TYPE==ResultFieldType.ARRAY) {
			res = "[";
			Object[] objs = (Object[])ARRAY.getArray();
			for(int i = 0; i < objs.length; i++) {
				if(i>0) res+=",";
				res+=objs.toString();
			}
			res+="]";
		}
		
		return res;
	}
}
