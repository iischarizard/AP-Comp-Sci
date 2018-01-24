import javafx.application.Application;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import java.util.Random;

public class JavaFxTest extends Application{
	
	public static void main(String[] args){
		launch(args);
	}
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	private Rectangle testerino;
	private Circle testerino2;
	private Random r;
	private Timeline tl;
	private BorderPane basePane;
	private Pane contentPane;
	private HBox buttonPane;
	private VBox leftPane;
		
	private void loop(){
	
		
		testerino.setLayoutY(testerino.getLayoutY()+20);
		if(testerino.getLayoutY()>720){
			testerino.setLayoutY(0);
			testerino.setLayoutX(r.nextInt(1200));
			if(testerino.getLayoutX()>1250)
				testerino.setLayoutX(0);
		}
		testerino.setRotate(testerino.getRotate()+5);
		
		testerino2.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
		if(testerino2.getRadius()>100){
			testerino2.setRadius(20);
			testerino2.setLayoutX(r.nextInt((int)(contentPane.getWidth()-200)));
			testerino2.setLayoutY(r.nextInt((int)(contentPane.getHeight()-200)));
		}else
			testerino2.setRadius(testerino2.getRadius()+2);	
	}
	
	@Override
	public void start(Stage primaryStage){
		r = new Random();
		primaryStage.setTitle("test");
		
		testerino = new Rectangle(0, 0, 100, 100);
		testerino.setFill(Color.BLUE);
		testerino.setStroke(Color.RED);
		testerino.setStrokeWidth(3);
		testerino.setArcWidth(50);
		testerino.setArcHeight(50);
		
		testerino2 = new Circle(300, 200, 20);
		testerino2.setFill(Color.SALMON);
		testerino2.setStroke(Color.BLACK);
		testerino2.setStrokeWidth(3);
		
		tl = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> loop()));
		tl.setCycleCount(Animation.INDEFINITE);
				
		Button start = new Button("Start");
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				tl.play();
			}
		});
		
		Button stop = new Button("Stop");
		stop.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				tl.stop();
			}
		});
		
		
		basePane = new BorderPane();
		contentPane = new Pane();
		buttonPane = new HBox();
		leftPane = new VBox();
		
		basePane.setStyle("-fx-background-color: salmon");
		contentPane.setStyle("-fx-background-color: salmon");
		buttonPane.setStyle("-fx-background-color: salmon");
		
		contentPane.getChildren().addAll(testerino, testerino2);
		buttonPane.getChildren().addAll(start, stop);
		
		basePane.setTop(buttonPane);
		basePane.setCenter(contentPane);
		
		Scene scene = new Scene(basePane, WIDTH, HEIGHT);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}

}