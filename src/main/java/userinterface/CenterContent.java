/**
 * 
 */
package userinterface;

import java.util.List;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;
import userinterface.TableCellFactorySample.CheckBoxTableCell;
import cassatte.FetchDatabaseMetaData;
import domain.FieldsSelectionJava;
import domain.TableObject;

/**
 * @author muddana_m
 *
 */
public class CenterContent {
	
	
	public GridPane getCenterContent() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(0, 10, 0, 10));

		Text title = new Text("MySQL Database Tables");
		title.setFont(Font.font("Arial", FontWeight.BLACK, 14));
		gridPane.add(title, 0 , 1);
		
		
		/**
		 * Get Database Schema and add it to VBOX
		 * 
		 */
		
		FetchDatabaseMetaData metaInfo = new FetchDatabaseMetaData();
		List<TableObject> metaInfos =  metaInfo.getSchemaDefinitions();
		
		
		for (TableObject meta : metaInfos) {
	        VBox vbox = createVBox(meta.getFields());
	        VBox outerBox = new VBox();
	        
	        Text tableName = new Text(meta.getTableName());
	        tableName.setFont(Font.font("Arial", FontWeight.BLACK, 11));
	        outerBox.getChildren().add(tableName);
	        outerBox.getChildren().add(vbox);
	        gridPane.add(outerBox, 0, meta.getFields().size() + 5);
		}
			
		return gridPane;
	}
	
	public VBox createVBox(List<String> fields) {
		VBox box = new VBox();

		ObservableList<FieldsSelectionJava> obsrvfields = populateSchemaView(fields);
		int fieldsLength = obsrvfields.size();
		if (fieldsLength <= 10) {
			box.setPrefHeight(obsrvfields.size() * 30);
		} else {
			box.setPrefHeight(obsrvfields.size() * 20);
		}
		
		/**
		 * Add columns to the TableView
		 * 
		 * 1. Selected
		 * 2. FieldName
		 * 3. CheckBox
		 */
        TableColumn<FieldsSelectionJava, Boolean> selectedCol = new TableColumn<FieldsSelectionJava, Boolean>();
        selectedCol.setText("Select");
        //selectedCol.setMinWidth(50);
        selectedCol.setCellValueFactory(new PropertyValueFactory<FieldsSelectionJava, Boolean>("selected"));
 
        selectedCol.setCellFactory(new Callback<TableColumn<FieldsSelectionJava, Boolean>, TableCell<FieldsSelectionJava, Boolean>>() {
            public TableCell<FieldsSelectionJava, Boolean> call(TableColumn<FieldsSelectionJava, Boolean> p) {
                return new CheckBoxTableCell<FieldsSelectionJava, Boolean>();
            }
        });
        
        TableColumn<FieldsSelectionJava, Boolean> fieldNameCol = new TableColumn<FieldsSelectionJava, Boolean>();
        fieldNameCol.setText("FieldName");
        fieldNameCol.setCellValueFactory(new PropertyValueFactory<FieldsSelectionJava, Boolean>("fieldName"));

		TableView<FieldsSelectionJava> tableView = new TableView<FieldsSelectionJava>();
		tableView.setItems(obsrvfields);
		
        tableView.setEditable(true);

        /** Add columns to tableView */
        tableView.getColumns().addAll(selectedCol, fieldNameCol);
        
        CheckBox cb =  new CheckBox("Select all");
        cb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                Boolean old_val, Boolean new_val) {
                if (new_val) {
                  for (FieldsSelectionJava field : obsrvfields ) {
                     //field.setSeleted(new SimpleBooleanProperty(true));
                     field.selectedProperty().set(true);
                  } 
                } else {
                    for (FieldsSelectionJava field : obsrvfields ) {
                        //field.setSeleted(new SimpleBooleanProperty(true));
                        field.selectedProperty().set(false);
                     } 
                }
            }
        });

        box.getChildren().addAll(cb, tableView);      
		return box;
	}
	
	
	public ObservableList<FieldsSelectionJava> populateSchemaView(List<String> columnNames) {
		ObservableList<FieldsSelectionJava> fields = FXCollections.observableArrayList();
		
		for (String field: columnNames) {
			fields.add(new FieldsSelectionJava(true, field));
		}
		
		return fields;
	}
	
/*	public VBox addVBox(String tableName, List<String> columnNames) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(5));
		vbox.setSpacing(5);
		vbox.setMaxHeight(columnNames.size());
		vbox.setStyle("-fx-border-color: black;");
		Text title = new Text(tableName);
		title.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		vbox.getChildren().add(title);
			
		for (String colName: columnNames) {
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(3));
			hbox.setSpacing(3);
			
			Label name = new Label(colName);
			name.setMinWidth(100.00);
			name.setPrefHeight(columnNames.size());
			name.setMaxHeight(columnNames.size());
			hbox.getChildren().add(name);
			hbox.getChildren().add(new CheckBox());
			
			vbox.getChildren().add(hbox);
		}
		return vbox;
	}
*/

}


