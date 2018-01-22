import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;

public class Bouncer extends Application{

	public static final int WIDTH = 1280, HEIGHT = 720;

	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Bouncer");
		
		BorderPane rootPane = new BorderPane();
		Pane pane = new Pane();
		
		
		rootPane.setStyle("-fx-background-color: white");
		pane.setStyle("-fx-background-color: white");
		
		rootPane.setCenter(pane);
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		
		sprites.add(new Sprite(pane, 0, 0));
		sprites.add(new Sprite(pane, 500, 300));
		
		for(Sprite sprite : sprites){
			pane.getChildren().add(sprite.getSprite());
		}
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae ->{
			for(Sprite spriteA : sprites){
				for(Sprite spriteB : sprites){
					if(spriteA != spriteB){
					}
				}		
			}
			
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		
		
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();	
		for(Sprite sprite : sprites){
			sprite.play();
		}
	}
	
	
	public static void main(String[] args){
		launch(args);
	}
}