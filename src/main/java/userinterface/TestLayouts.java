/**
 * 
 */
package userinterface;

import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author muddana_m
 *
 */
public class TestLayouts extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(15, 15, 15, 15));
		
		grid.add(addVBox(), 0, 1);

		Scene scene = new Scene(grid, 200, 100);
		primaryStage.setScene(scene);

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public VBox addVBox() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(5);
		vbox.setStyle("-fx-border-color: black;");
		Text title = new Text("Table Name");
		vbox.getChildren().add(title);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(5));
		hbox.setSpacing(5);
		hbox.getChildren().add(new Label("Col1"));
		hbox.getChildren().add(new CheckBox());
		
		vbox.getChildren().add(hbox);
		
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(5));
		hbox2.setSpacing(5);
		hbox2.getChildren().add(new Label("Col2"));
		hbox2.getChildren().add(new CheckBox());

		vbox.getChildren().add(hbox2);
		return vbox;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}
	
	

}
