package entity;

public class Dude extends Entity{

	private float vx, vy;
	
	public Dude(float x_, float y_) {
		super(x_, y_, 150, 200, "assets/Brandon.png", "assets/Brandon2.png");
	}

	public void loop(){
		setLayoutX(getLayoutX()+vx);
		setLayoutY(getLayoutY()+vy);
	}
	
	public void setVX(float vx_){vx = vx_;}
	public void setVY(float vy_){vy = vy_;}
	
}
