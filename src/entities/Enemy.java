package entities;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import graphics.Sprite;

public class Enemy extends Mob{

	public Enemy(int x, int y){
		super(x, y, Sprite.pelican);
		this.SPEED = 2;
	}
	
	@Override
	public void update(){
		
	}
}
