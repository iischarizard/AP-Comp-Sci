
import java.util.ArrayList;


import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PlayPane extends Pane {
	
	private ArrayList<Word> words;
	
	public PlayPane(TypingGame game){
		//setStyle("-fx-background-color: red");
		words = new ArrayList<Word>();
		words.add(new Word("stop"));
		words.add(new Word("testerino\"\""));
		words.add(new Word("top"));
		words.add(new Word("hello??"));
		
		Button start = new Button("Start");
		start.setLayoutX(Constants.WIDTH/2 - start.getWidth()/2);
		start.setLayoutY(Constants.HEIGHT/2 - start.getHeight()/2);
		start.setOnAction(ae ->{game.startGameLoop(); getChildren().remove(start);
		getChildren().addAll(words);});
		
		getChildren().add(start);
		
	}
	
	public void checkHead(String key){
		for(Word word : words){
			if(!word.checkHead(key)){
				getChildren().remove(word);
				words.remove(word);
				if(words.size() != 0)
					checkHead(key);
				break;
			}
		}
	}

	public void loop(){
		for(Word word : words){
			word.fall();
		}
	}

}
