

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TypingGame {
	
	private Stage primaryStage;
	private RootPane rootPane;
	private TitlePane titlePane;	
	private PlayPane playPane;
	private Timeline gameLoop;
	private boolean running;
	
	public TypingGame(Stage primaryStage){
		this.primaryStage = primaryStage;

		primaryStage.setTitle(Constants.TITLE);
		
		gameLoop = new Timeline(new KeyFrame(Duration.millis(1000/60), ae -> loop()));
		gameLoop.setCycleCount(Animation.INDEFINITE);
		
		running = false;
		
		
		initPanes();
		
		setUpStartingPanes();
		
		finishSetup();
		
		
		
		
	}

	private void initPanes(){
		titlePane = new TitlePane(this);
		rootPane = new RootPane();
		playPane = new PlayPane(this);
		
	}
	

	private void setUpStartingPanes(){
		rootPane.setCenter(titlePane);
	}
	
	
	private void finishSetup(){

		Scene scene = new Scene(rootPane, Constants.WIDTH, Constants.HEIGHT);
		scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			if(running){
				//System.out.println(key.getText());
				if(key.getCode() != KeyCode.SHIFT)
				if(key.isShiftDown()){
					String text = key.getText();
					switch(key.getText()){
					case ",":
						text = "<";
						break;
					case ".":
						text = ">";
						break;
					case "/":
						text = "?";
						break;
					case ";":
						text = ":";
						break;
					case "'":
						text = "\"";
						break;
					case "[":
						text = "{";
						break;
					case "]":
						text = "}";
						break;
					case "`":
						text = "~";
						break;
					case "1":
						text = "!";
						break;
					case "2":
						text = "@";
						break;
					case "3":
						text = "#";
						break;
					case "4":
						text = "$";
						break;
					case "5":
						text = "%";
						break;
					case "6":
						text = "^";
						break;
					case "7":
						text = "&";
						break;
					case "8":
						text = "*";
						break;
					case "9":
						text = "(";
						break;
					case "0":
						text = ")";
						break;
					case "-":
						text = "_";
						break;
					case "=":
						text = "+";
						break;
					default:
						text = text.toUpperCase();
							
					}
					playPane.checkHead(text);
					
				}else
					playPane.checkHead(key.getText());
					
			}
			
					
		});
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	private void loop(){
		playPane.loop();
	}
	
	public void switchToGame(){
		rootPane.setCenter(playPane);
	}
	
	public void startGameLoop(){
		running = true;
		gameLoop.play();
	}
	public void stopGameLoop(){
		running = false;
		gameLoop.stop();
	}
	
	
}
