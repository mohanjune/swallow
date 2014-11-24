/**
 * 
 */
package userinterface;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import cassatte.FetchDatabaseMetaData;
import domain.TableObject;

/**
 * @author muddana_m
 *
 */
public class CenterContent {
	
	
	public GridPane getCenterContent(String tableName, List<String> columnNames) {
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
		
		int i = 0, j = 5;
		for (TableObject meta : metaInfos) {
			gridPane.add(addVBox(meta.getTableName(), meta.getFields()), i, j);
			i = i + 10;
			j = meta.getFields().size();

			if (i >= 1100) {
				i = 0;
			}
		}
			
		return gridPane;
	}
	
	
	public VBox addVBox(String tableName, List<String> columnNames) {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(5);
		vbox.setStyle("-fx-border-color: black;");
		Text title = new Text(tableName);
		title.setFont(Font.font("Arial", FontWeight.BLACK, 11));
		vbox.getChildren().add(title);
			
		for (String colName: columnNames) {
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(5));
			hbox.setSpacing(5);
			
			Label name = new Label(colName);
			name.setMinWidth(100.00);
			hbox.getChildren().add(name);
			hbox.getChildren().add(new CheckBox());
			
			vbox.getChildren().add(hbox);
		}
		return vbox;
	}
}


