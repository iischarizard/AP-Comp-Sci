import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Exception;

import java.util.Random;

public class Test extends JFrame implements Runnable{

  private JPanel panel;
  private Dimension dim;
  public static final int WIDTH = 1280, HEIGHT = 720;
  
  private Thread thread;
  private boolean running;
  
  private int x, y, speedX, speedY;
  private int count, clearCount;
  
  private Random r;
  
  public Test(){
	x = WIDTH/2;
    y = HEIGHT/2;
	count = 0;
	clearCount = 0;
    
    r = new Random();
	speedX = r.nextInt(15);
	speedY = r.nextInt(15);
	//speedY = speedX;
    
    panel = new JPanel();
    panel.setBackground(Color.white);
	  
    dim = new Dimension(WIDTH, HEIGHT);
	setTitle("what am i doing");
    setMinimumSize(dim);
    setMaximumSize(dim);
    setResizable(false);
    setLocationRelativeTo(null);
	setBackground(Color.white);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(panel);
  
    thread = new Thread(this);
    thread.start();
    running = true;
    
    setVisible(true);
  
  }
  
  
  public void paint(Graphics g){
  	Graphics2D g2d = (Graphics2D) g;
	
    clearCount++;
	if(clearCount<10000)
		g2d.setColor(changeColor());
	else if(clearCount<20000){
		g2d.setColor(Color.white);
	}else{
		x = WIDTH/2;
		y = WIDTH/2;
		speedX = r.nextInt(15);
		speedY = r.nextInt(15);
		clearCount = 0;
	}
    g2d.drawLine(x, y, (WIDTH/2)+speedX, (HEIGHT/2)+speedY);
	
    g2d.dispose();
  
  }
  
  public void update(){
  	x += speedX;
	y += speedY;
	if(x>WIDTH||x<0)
		speedX*=-1;
	if(y>HEIGHT||y<0)
		speedY*=-1;
	
  }
  
  private Color changeColor(){
	  Color color = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
	  return color;
	  
  }
  
  public static void main(String[] args){
  	new Test();
	
  }
  
  
  public void run(){
    while(running){
    	update();
      	repaint();
      try{
      	Thread.sleep(1);
      }catch(Exception e){
      	e.printStackTrace();
      }
    }
  
  }
}