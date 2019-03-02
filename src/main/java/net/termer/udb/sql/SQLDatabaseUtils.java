package net.termer.udb.sql;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import net.termer.udb.DatabaseUtils;
import net.termer.udb.ResultFieldType;


public class SQLDatabaseUtils extends DatabaseUtils {
	public static SQLQueryResult ConvertResultSetToSQLQueryResult(ResultSet rs) throws SQLException {
		SQLQueryResult result = null;
		
		if(rs!=null) {
			// Convert results to SQLQueryResult
			
			// Get columns
			ArrayList<SQLResultColumn> columns = new ArrayList<SQLResultColumn>();
			
			for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				
				// Determine column type
				int columnType = ResultFieldType.NULL;
				int jdbcType = rs.getMetaData().getColumnType(i);
				if(jdbcType==Types.BIGINT || 
						jdbcType==Types.INTEGER || 
						jdbcType==Types.SMALLINT ||
						jdbcType==Types.TINYINT) {
					columnType = ResultFieldType.INT;
				} else if(jdbcType==Types.BOOLEAN) {
					columnType = ResultFieldType.BOOLEAN;
				} else if(jdbcType==Types.CHAR ||
						jdbcType==Types.VARCHAR ||
						jdbcType==Types.LONGVARCHAR ||
						jdbcType==Types.LONGNVARCHAR ||
						jdbcType==Types.NCHAR ||
						jdbcType==Types.NVARCHAR) {
					columnType = ResultFieldType.TEXT;
				} else if(jdbcType==Types.DOUBLE) {
					columnType = ResultFieldType.DOUBLE;
				} else if(jdbcType==Types.FLOAT) {
					columnType = ResultFieldType.FLOAT;
				} else if(jdbcType==Types.JAVA_OBJECT) {
					columnType = ResultFieldType.OBJECT;
				} else if(jdbcType==Types.OTHER) {
					columnType = ResultFieldType.OTHER;
				}
				
				// Add column
				columns.add(new SQLResultColumn(rs,i,rs.getMetaData().getColumnName(i),columnType));
			}
			
			// Get fields and rows
			ArrayList<SQLResultRow> rows = new ArrayList<SQLResultRow>();
			
			// Loop through SQL results
			boolean available = rs.first();
			int index = 1;
			while(available) {
				ArrayList<SQLResultField> fields = new ArrayList<SQLResultField>();
				for(int i = 1; i <= columns.size(); i++) {
					SQLResultColumn col = columns.get(i-1);
					
					// Use constructor corresponding to field type
					if(col.getDataType()==ResultFieldType.BOOLEAN) {
						fields.add(new SQLResultField(rs.getBoolean(i),null,col,i,rs));
					} else if(col.getDataType()==ResultFieldType.DOUBLE) {
						fields.add(new SQLResultField(rs.getDouble(i),null,col,i,rs));
					} else if(col.getDataType()==ResultFieldType.FLOAT) {
						fields.add(new SQLResultField(rs.getFloat(i),null,col,i,rs));
					} else if(col.getDataType()==ResultFieldType.INT) {
						fields.add(new SQLResultField(rs.getInt(i),null,col,i,rs));
					} else if(col.getDataType()==ResultFieldType.OBJECT) {
						fields.add(new SQLResultField(rs.getObject(i),null,col,i,rs));
					} else if(col.getDataType()==ResultFieldType.TEXT) {
						String text = "";
						Reader cs = rs.getCharacterStream(i);
						try {
							while(cs.ready()) {
								text+=(char)cs.read();
							}
						} catch (IOException e) {
							System.err.println("Failed to read characters from CharacterStream:");
							e.printStackTrace();
						}
						fields.add(new SQLResultField(text,null,col,i,rs));
					} else {
						// NULL
						fields.add(new SQLResultField(null,col,i,rs));
					}
				}
				
				SQLResultRow row = new SQLResultRow(
					fields.toArray(new SQLResultField[0]),
					index,
					columns.toArray(new SQLResultColumn[0]),
					rs
				);
				for(SQLResultField field : row.getFields()) {
					field.setRow(row);
				}
				rows.add(row);
				
				// Determine if end
				if(rs.next()) {
					index++;
				} else {
					available = false;
				}
			}
			
			// Create SQLQueryResult
			result = new SQLQueryResult(columns.toArray(new SQLResultColumn[0]), rows.toArray(new SQLResultRow[0]), rs);
		}
		
		return result;
	}
}
