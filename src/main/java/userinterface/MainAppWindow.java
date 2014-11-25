/**
 * 
 */
package userinterface;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cassatte.FetchDatabaseMetaData;

/**
 * @author muddana_m
 *
 */
public class MainAppWindow extends Application  {

	final static Logger logger = LoggerFactory.getLogger(MainAppWindow.class );
	
	public void start(Stage primaryStage) {
		ScrollPane scroll = new ScrollPane();
		BorderPane borderPane = new BorderPane();
		
		scroll.setContent(borderPane);
	
		borderPane.setTop(addHbox(primaryStage));
		
		List<String> names = new ArrayList<String>();
		names.add("orderId");
		names.add("customerName");
		names.add("address");
		names.add("totalPrice");
		CenterContent centerContent = new CenterContent();
		borderPane.setCenter(centerContent.getCenterContent("Line Orders", names));
		
		scroll.setContent(borderPane);
		Scene scene = new Scene(scroll, 1200, 600);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cassatte MySQL to Cassandra");
		primaryStage.show();
	}
	
	public HBox addHbox(Stage primaryStage) {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);

	    
		Label dbUrl = new Label("Database URL:");
		TextField dbTextField = new TextField();
		dbTextField.setPrefWidth(200.0);
		dbTextField.setPromptText("jdbc:mysql://localhost:3306/?");
		dbTextField.setTooltip(new Tooltip("jdbc:mysql://localhost:3306/?"));
		
		Label user = new Label("User:");
		TextField userTextField = new TextField();
		userTextField.setPrefWidth(100.0);
		userTextField.isFocused();
		
		Label pw = new Label("Password:");
		PasswordField pwBox = new PasswordField();
		pwBox.setPrefWidth(100.0);
		
		Button btn = new Button();
		btn.setText("Test Connection");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FetchDatabaseMetaData fetch = new FetchDatabaseMetaData();
				boolean result = fetch.testConnection(dbTextField.getText(), userTextField.getText(), pwBox.getText());
				showPopup(result, primaryStage);
				logger.info("Connection result " + String.valueOf(result));
			}
		});
		
		hbox.getChildren().addAll(dbUrl, dbTextField, user, userTextField, pw, pwBox, btn);
		return hbox;
	}
	
	
	public void showPopup(boolean result, Stage primaryStage) {
		StackPane pane = new StackPane();
        pane.getStyleClass().add("pane");
        
        Rectangle rectangle = new Rectangle(250, 250);
        
        rectangle.getStyleClass().add("rect");
        rectangle.setFill(Color.WHITE);
        Label text = new Label(String.valueOf(result));
        text.getStyleClass().add("text");
        pane.getChildren().addAll(rectangle, text);
        
        Popup pop = new Popup();
        pop.getContent().addAll(pane);
        pop.show(primaryStage);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
