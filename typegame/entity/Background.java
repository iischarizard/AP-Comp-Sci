package entity;

public class Background extends Entity {

	public Background() {
		super(0, 0, 1300, 740, "assets/background.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean loop() {
		return true;

	}

}
