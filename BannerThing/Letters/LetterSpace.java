package Letters;

public class LetterSpace extends Letter{

	public LetterSpace() {
		super(" ", new String[5]);
	}

	@Override
	public void buildLetter() {
		lines[1] = "    ";
		lines[2] = "    ";
		lines[0] = "    ";
		lines[3] = "    ";
		lines[4] = "    ";
	}

}
