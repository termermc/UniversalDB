package net.termer.udb;

public class DatabaseUtils {
	
	/**
	 * Returns a visual representation of the specified table as a String.
	 * @return a visual representation of the specified table as a String
	 * @since 1.0
	 */
	public static String toVisualTable(QueryResult qr) {
		String table = "";
		
		if(qr!=null) {
			// Get column with the longest name
			int longest = 0;
			for(ResultColumn col : qr.getColumns()) {
				if(col.getName().length()>longest) {
					longest = col.getName().length();
				}
			}
			
			// Print column names
			table+="# ";
			for(ResultColumn col : qr.getColumns()) {
				String name = col.getName();
				int fill = longest-name.length();
				for(int j = 0; j < fill; j++) name+=" ";
				
				table+=name+" ";
			}
			table+="\n";
			
			for(ResultRow row : qr.getRows()) {
				table+=row.getIndex()+" ";
				for(ResultField field : row.getFields()) {
					String value = substring(field.getValueAsString(),longest);
					
					String padding = "";
					int fill = longest-value.length();
					for(int j = 0; j <= fill; j++) padding+=" ";
					
					table+=value+padding;
				}
				table+="\n";
			}
		}
		
		return table;
	}
	
	/**
	 * Returns specified table as an HTML table.
	 * @return an HTML version of the specified table
	 * @since 1.0
	 */
	public static String toHTMLTable(QueryResult qr) {
		String html = "<table>\n";
		
		if(qr!=null) {
			// Columns
			html+="  <tr>\n";
			for(ResultColumn col : qr.getColumns()) {
				html+="    <th>"+col.getName()+"</th>\n";
			}
			html+="  </tr>\n";
			
			// Rows
			for(ResultRow row : qr.getRows()) {
				html+="  <tr>\n";
				
				// Fields
				for(ResultField field : row.getFields()) {
					html+="    <td>"+
								field.getValueAsString()
									.replace("<", "&lt")
									.replace(">", "&gt")
									.replace("&", "&amp")
								+"</td>\n";
				}
				
				html+="  </tr>\n";
			}
		}
		
		return html+="</table>";
	}
	
	private static String substring(String str, int length) {
		String tmp = str;
		if(tmp.length()>length) {
			tmp = tmp.substring(0, length-3)+"...";
		}
		return tmp;
	}
}
