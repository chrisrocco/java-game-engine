package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity{
	
	
	public double dx, dy;
	public int width, height;
	public Sprite sprite;
	public double angle = 0;
		protected double SPEED = 3;
			public boolean moving = false;
	
	public Mob(int x, int y, Sprite sprite) {
		super(x, y);
		this.dx = 0;
		this.dy = 0;
		this.sprite = sprite;
		width = sprite.img.getWidth(null);
		height = sprite.img.getHeight(null);
	}

	public void move(){
		x += dx;
		y += dy;
	}
	
	public void render(Screen screen){
		screen.renderMob(this);
	}
	
	public void goTo(int targetX, int targetY){
		double centerY = y + (height / 2);
		double centerX = x + (width / 2);
		
		if(!(Math.abs(targetX - centerX) > SPEED || Math.abs(targetY - centerY) > SPEED)){
			dx = 0;
			dy = 0;
			return;
		}

		setAngle((int)targetX, (int)targetY, centerX, centerY);

		dx = Math.cos(angle) * SPEED;
		dy = Math.sin(angle) * SPEED;
	}
	
	public void setAngle(double targetX, double targetY, double startX, double startY){
		angle = Math.atan2(targetY - startY, targetX - startX);
	    if(angle < 0) angle += Math.toRadians(360);
	}
	
	private Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public boolean collides(Mob m){
		return(getBounds().intersects(m.getBounds()));
	}

}
