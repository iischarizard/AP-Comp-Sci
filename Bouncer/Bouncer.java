import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import java.util.ArrayList;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class Bouncer extends Application{

	public static final int WIDTH = 1280, HEIGHT = 720;
	private ArrayList<Sprite> sprites;
	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Bouncer");
		sprites = new ArrayList<Sprite>();
		
		BorderPane rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: white");
		
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color: white");
		rootPane.setCenter(pane);
		
		
		StackPane titlePane = new StackPane();
		Label title = new Label("Bouncer");
		titlePane.getChildren().add(title);
		rootPane.setTop(titlePane);
		
		VBox controls = new VBox();
		controls.setStyle("-fx-border-color: black");
     	controls.setPrefWidth(200);
		
		
		Button addCrate = new Button("+");
		
		addCrate.setOnAction(ae -> {
				
			sprites.add(new Sprite(pane, 0, 0));
			pane.getChildren().add(sprites.get(sprites.size()-1).getSprite());
			controls.getChildren().add(new SpriteControl(controls, sprites, pane, sprites.get(sprites.size()-1)).getPane());
			
			
		});
		
		
		controls.getChildren().addAll(addCrate);
		
		rootPane.setRight(controls);
		
		
		
		/*sprites.add(new Sprite(pane, 0, 0));
		sprites.add(new Sprite(pane, 0, 300));
		sprites.add(new Sprite(pane, 500, 500));
		sprites.add(new Sprite(pane, 1000, 100));
		
		for(Sprite sprite : sprites){
			pane.getChildren().add(sprite.getSprite());
		}*/
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae ->{
			for(Sprite spriteA : sprites){
				boolean done = false;
				for(Sprite spriteB : sprites){
					if(spriteA != spriteB){
						Pane spritea = spriteA.getSprite();
						Pane spriteb = spriteB.getSprite();
						if(spritea.getBoundsInParent().intersects(spriteb.getBoundsInParent())){
							spriteA.invert();
							spriteB.invert();
							done = true;
							break;
						}
					}
				}	
				if(done)
					break;	
			}
			
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		
		Scene scene = new Scene(rootPane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	
	public static void main(String[] args){
		launch(args);
	}
}