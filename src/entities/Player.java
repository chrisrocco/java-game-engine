package entities;


import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import engine.Engine;
import graphics.Sprite;
import input.Keyboard;
import input.Mouse;
import sound.Audio;

public class Player extends Mob {
	
	
	
	/* CONSTANTS */
	private double ACCELERATION = 0.25;
	private final double INERTIA = 0.90;
	private double FIRE_RATE = 150;
	
	/* INSTANCE */
	public boolean boosting = false;
	
	/* INPUT */
	Keyboard input;

	
	public Player(int x, int y, Keyboard input) {
		super(x, y, Sprite.car);
		this.input = input;
		this.SPEED = 8;
	}
	
	public void update() {
		// Move
		if(input.up) dy = adjust(dy, -ACCELERATION);
		if(input.down) dy = adjust(dy, ACCELERATION);
		if(input.left) dx = adjust(dx, -ACCELERATION);
		if(input.right) dx = adjust(dx, ACCELERATION);
		if(!input.noKeys()) setAngle(dx, dy, 0, 0);
		
		// Out of Bounds
		/*
		if(x < 0-width) x = bounds.x;
		if(x > bounds.x) x = 0-width;
		if(y < 0-height) y = (bounds.y);
		if(y > bounds.y) y = 0-height;
		*/
		
		// Nothing pressed
		if(!input.up && !input.down){
			if(Math.abs(dy) < 0.5){
				dy = 0;
			} else {
				dy *= INERTIA;
			}
		}
		if(!input.left && !input.right){
			if(Math.abs(dx) < 0.5){
				dx = 0;
			} else {
				dx *= INERTIA;
			}
		}
		
		// Other Clicks
		if(input.keys[KeyEvent.VK_SPACE]) fireMissile();
		
		move();
	}

	private double adjust(double vel, double amount){
		if(Math.abs(vel) <= SPEED){
			vel += amount;
		}
		return vel;
	}
	/*
	public void render(Graphics2D g2d){
		
		AffineTransform backup = g2d.getTransform();
		AffineTransform trans = new AffineTransform();
		trans.rotate( angle, x+(width/2), y+(height/2) );

		g2d.transform( trans );
			g2d.drawImage( image, x, y, null );
		g2d.setTransform( backup );
	
		g2d.dispose();
	}
	*/

	long lastShot = 0;
	private void fireMissile() {
		if(System.currentTimeMillis() - lastShot > FIRE_RATE){
			
			
			Audio.playSound("laser.wav");
			
			lastShot = System.currentTimeMillis();
		}
		
	}
	private void cleanup(){/*
		for(Projectile p: projectiles){
			if(p.x < 0 || p.x > bounds.x || p.y < 0 || p.y > bounds.y){
				p.removed = false;
			}
		}
		
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).removed == false) projectiles.remove(i);
		}
	*/}

	private void speedBoost(int seconds){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				if(!boosting){
					try {
						boosting = true;
						ACCELERATION = .80;
						SPEED = 14;
						System.out.println("BOOSTER ON");
						Thread.sleep(seconds * 1000);
						ACCELERATION = .25;
						SPEED = 7;
						System.out.println("BOOSTER OFF");
						boosting = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
	}
}