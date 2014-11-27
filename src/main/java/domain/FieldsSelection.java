/**
 * 
 */
package domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * @author muddana_m
 *
 * Object to be populated in the UI for Selection Process of Table fields. 
 *
 */
public class FieldsSelection {
	
	BooleanProperty selected;
	StringProperty fieldName;
	StringProperty fieldType;
	
	public FieldsSelection(boolean selected, String fieldName) {
		this.selected = new SimpleBooleanProperty(selected);
		this.fieldName = new SimpleStringProperty(fieldName);

	    this.selected.addListener(new ChangeListener<Boolean>() {
	    	 
	        public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
	
	            System.out.println(fieldName + " selected: " + t1);
	
	        }
	
	    });     

	}
	
	public BooleanProperty selectedProperty() {
		return selected;
	}

	
	public StringProperty getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName.set(fieldName);
	}

	public StringProperty getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType.set(fieldType);
	}
	
}
