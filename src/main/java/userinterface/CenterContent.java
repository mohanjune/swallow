/**
 * 
 */
package userinterface;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
		
		gridPane.add(addVBox(tableName, columnNames), 0, 5);
			
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


