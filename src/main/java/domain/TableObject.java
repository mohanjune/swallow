/**
 * 
 */
package domain;

import java.util.List;

/**
 * @author muddana_m
 *
 */
public class TableObject {
	String tableName;
	List<String> fields;

	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}
	
	
}
