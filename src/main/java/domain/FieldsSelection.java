/**
 * 
 */
package domain;

/**
 * @author muddana_m
 *
 * Object to be populated in the UI for Selection Process of Table fields. 
 *
 */
public class FieldsSelection {
	
	boolean selected;
	String fieldName;
	String fieldType;
	
	public FieldsSelection(boolean selected, String fieldName) {
		this.selected = selected;
		this.fieldName = fieldName;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
}
