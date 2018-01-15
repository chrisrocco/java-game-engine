package entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import engine.Engine;
import level.Level;

public abstract class Entity {
	
	public int x, y;
	protected Level level;

	
	public boolean removed = false;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public abstract void update();
	
	public void render(Graphics2D g2d){
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
}