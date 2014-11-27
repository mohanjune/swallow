package domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class FieldsSelectionJava {
	BooleanProperty seleted;
	String fieldName;
	
	public FieldsSelectionJava(boolean selected, String fieldName) {
		this.seleted = new SimpleBooleanProperty(selected);
		this.fieldName = fieldName;
		
        this.seleted.addListener(new ChangeListener<Boolean>() {
        	 
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {

                System.out.println(getFieldName() + " invited: " + t1);

            }

        }); 
	}

	public BooleanProperty selectedProperty() { return seleted; }

	
	public void setSeleted(BooleanProperty seleted) {
		this.seleted = seleted;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	
}
