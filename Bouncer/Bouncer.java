import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class Bouncer extends Application{

	public static final int WIDTH = 1280, HEIGHT = 720;

	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Bouncer");
		
		Pane rootPane = new Pane();
		
		
		rootPane.setStyle("-fx-background-color: white");
		
		Sprite sprite = new Sprite();
		
		rootPane.getChildren().addAll(sprite.getSprite());
		
		
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();		
		sprite.start();
	}
	
	
	public static void main(String[] args){
		launch(args);
	}
}