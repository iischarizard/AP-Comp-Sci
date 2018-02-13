
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class TitlePane extends Pane{
	
	
	public TitlePane(TypingGame game){
		setStyle("-fx-background-color: AntiqueWhite");
		Label title = new Label(Constants.TITLE);
		title.setLayoutX(Constants.WIDTH/2-20);
		title.setLayoutY(10);
		
		Button start = new Button("Start");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae ->{game.switchToGame();});
		
		getChildren().addAll(start, title);
		
	}
	

}
