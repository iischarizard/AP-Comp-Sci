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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Bouncer extends Application{

	public static final int WIDTH = 1280, HEIGHT = 720;
	private ArrayList<Sprite> sprites;
	
	int numSprites = 0;
	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Bouncer");
		sprites = new ArrayList<Sprite>();
		
		BorderPane rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: white");
		
		Pane playground = new Pane();
		playground.setStyle("-fx-background-color: white");
		rootPane.setCenter(playground);
		
		
		StackPane titlePane = new StackPane();
		Label title = new Label("Bouncer");
		titlePane.getChildren().add(title);
		rootPane.setTop(titlePane);
		
		VBox controls = new VBox();
		controls.setStyle("-fx-border-color: black");
     	controls.setPrefWidth(200);
		
		
		Button addCrate = new Button("+");
		
		addCrate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent ae){
				numSprites++;
				//sprites.add(new Sprite("Sprite "+(sprites.size()+1), pane, 0, 0, (int)(Math.random() * 7)+3, (int)(Math.random() * 7)+3));
				sprites.add(new Sprite("Sprite "+/*(sprites.size()+1)*/numSprites, playground, 5, 6));
				playground.getChildren().add(sprites.get(sprites.size()-1).getSprite());
				sprites.get(sprites.size()-1).play();
				controls.getChildren().add(new SpriteControl(controls, sprites, playground, sprites.get(sprites.size()-1)).getPane());
			}
			
		});
		
		
		controls.getChildren().addAll(addCrate);
		
		rootPane.setRight(controls);
		
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae ->{
			for(Sprite spriteA : sprites){
				boolean done = false;
				for(Sprite spriteB : sprites){
					if(spriteA != spriteB){
						Pane spritea = spriteA.getSprite();
						Pane spriteb = spriteB.getSprite();
						if(spritea.getBoundsInParent().intersects(spriteb.getBoundsInParent())){
							spriteA.collide(spriteB);
							spriteB.collide(spriteA);
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