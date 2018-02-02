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
	private ArrayList<Crate> crates;
	private int numCrates = 0;
	
	@Override
	public void start(Stage primaryStage){
		
		primaryStage.setTitle("Bouncer");
		crates = new ArrayList<Crate>();
		
		BorderPane rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: white");
		
		Pane playground = new Pane();
		playground.setStyle("-fx-background-color: mistyrose");
		rootPane.setCenter(playground);
		
		
		StackPane titlePane = new StackPane();
		Label title = new Label("Bouncer");
		titlePane.getChildren().add(title);
		titlePane.setStyle("-fx-background-color: lime");
		rootPane.setTop(titlePane);
		
		VBox controls = new VBox();
		controls.setStyle("-fx-border-color: black");
		controls.setStyle("-fx-background-color: thistle");
     	controls.setPrefWidth(200);
		
		
		Button addCrate = new Button("+");
		
		addCrate.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent ae){
				numCrates++;
				crates.add(new Crate("Crate "+numCrates, playground, 5, 6));
				playground.getChildren().add(crates.get(crates.size()-1).getSprite());
				crates.get(crates.size()-1).play();
				controls.getChildren().add(new CrateControl(controls, crates, playground, crates.get(crates.size()-1)).getPane());
			}
			
		});
		
		
		controls.getChildren().addAll(addCrate);
		
		rootPane.setRight(controls);
		
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000/60), ae ->{
			for(Crate spriteA : crates){
				for(Crate spriteB : crates){
					if(spriteA != spriteB){
						Pane spritea = spriteA.getSprite();
						Pane spriteb = spriteB.getSprite();
						if(spritea.getBoundsInParent().intersects(spriteb.getBoundsInParent())){
							spriteA.collide(spriteB);
							spriteB.collide(spriteA);
							break;
						}
					}
				}	
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