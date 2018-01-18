import javafx.application.Application;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JavaFxTest extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage){
		primaryStage.setTitle("test");
		
		Rectangle testerino = new Rectangle(0, 0, 100, 100, Color.RED);
		
		Button button = new Button("Test");
		button.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				
			}
		});
		
		StackPane pane = new StackPane();
		pane.getChildren().addAll(testerino, button);
		primaryStage.setScene(new Scene(pane, 1280, 720));
		primaryStage.show();
		
	}

}