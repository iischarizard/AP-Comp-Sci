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
		
		BorderPane rootPane = new BorderPane();
		HBox leftPane = new HBox();
		VBox rightPane = new VBox();
		StackPane bottomPane = new StackPane();
		FlowPane topPane = new FlowPane();
		
		
		rootPane.setStyle("-fx-background-color: white");
		leftPane.setStyle("-fx-background-color: salmon");
		rightPane.setStyle("-fx-background-color: green");
		bottomPane.setStyle("-fx-background-color: gray");
		topPane.setStyle("-fx-background-color: yellow");
		
		Sprite leftSprite = new Sprite();
		Sprite rightSprite = new Sprite();
		Sprite bottomSprite = new Sprite();
		Sprite topSprite = new Sprite();
		
		leftPane.getChildren().addAll(leftSprite.getNodes());
		rightPane.getChildren().addAll(rightSprite.getNodes());
		bottomPane.getChildren().addAll(bottomSprite.getNodes());
		topPane.getChildren().addAll(topSprite.getNodes());
		
		
		Button startStopAll = new Button("Toggle all");
		startStopAll.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				leftSprite.startStop();
				rightSprite.startStop();
				topSprite.startStop();
				bottomSprite.startStop();
			}
		});
		
		rootPane.setCenter(startStopAll);
		rootPane.setLeft(leftPane);
		rootPane.setRight(rightPane);
		rootPane.setBottom(bottomPane);
		rootPane.setTop(topPane);
		
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
}