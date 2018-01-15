package graphics;

import java.awt.image.BufferedImage;

public class Sprite {
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	public BufferedImage img;
	private SpriteSheet sheet;
	
	public static Sprite banshee = new Sprite(64, 1, 0, SpriteSheet.sheet);
	public static Sprite pelican = new Sprite(128, 1, 1, SpriteSheet.sheet);
	public static Sprite car = new Sprite(64, 0, 0, SpriteSheet.sheet);
	
	
	public static Sprite zombie_d = new Sprite(32, 0, 0, SpriteSheet.zombies); // 32 is the sprite size
	public static Sprite zombie_l = new Sprite(32, 0, 1, SpriteSheet.zombies);
	public static Sprite zombie_r = new Sprite(32, 0, 2, SpriteSheet.zombies);
	public static Sprite zombie_u = new Sprite(32, 0, 3, SpriteSheet.zombies);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];;
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}
	
	public void load(){
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
			}
		}
		img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, SIZE, SIZE, pixels, 0, SIZE);
	}
}
