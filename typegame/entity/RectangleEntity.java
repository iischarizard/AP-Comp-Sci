package entity;

public class RectangleEntity extends Entity{

	public RectangleEntity(float x_, float y_, float width_, float height_, String color) {
		super(x_, y_, width_, height_, color, 0);
	}

	@Override
	public boolean loop() {
		// TODO Auto-generated method stub
		return false;
	}

}
