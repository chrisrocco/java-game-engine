package engine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import entities.Mob;
import level.Level;

public class Screen {
	

	/* DISPLAY */
	int width, height;
	//public int[] pixels;
	//int[] tiles = new int[64 * 64];
	BufferedImage backBuffer;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		//pixels = new int[width * height];
		backBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	public void render() {
		/*
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = 0xffffff;
			}
		}
		*/
	}
	
	public void renderMob(Mob mob){
		if(mob.x+mob.width < 0 || mob.x > width || mob.y+mob.height < 0 || mob.y > height) return;
		
		Graphics2D g2d = backBuffer.createGraphics();
		
		
		AffineTransform backup = g2d.getTransform();
		AffineTransform trans = new AffineTransform();
		int centerX = mob.x + (mob.width/2);
		int centerY = mob.y + (mob.height/2);
		trans.rotate( mob.angle, centerX, centerY );

		g2d.transform( trans );
			g2d.drawImage(mob.sprite.img, mob.x, mob.y, null);
		g2d.setTransform( backup );
	
		g2d.dispose();
	}
	
	public void renderLevel(Level level){
		for(Mob m: level.mobs){
			renderMob(m);
		}
	}

	public void clear() {
		/*
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x90D4D1;
		}
		*/
		
		Graphics2D g2d = backBuffer.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, height);
		g2d.dispose();
		
	}

}
