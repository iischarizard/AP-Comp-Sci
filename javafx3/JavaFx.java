import javafx.application.Application;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.Scene;
import javafx.scene.layout.*;

public class JavaFx extends Application{

	public static final int WIDTH = 1280, HEIGHT = 720;

	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("test");
		
		Pane rootPane = new Pane();
		
		
		rootPane.setStyle("-fx-background-color: white");
		
		Sprite sprite = new Sprite();
		rootPane.getChildren().add(sprite.getPane());
		
		
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();		
		
		sprite.play();
	}
	
}