package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	final int SIZE;
	public int[] pixels;
	
	public static SpriteSheet sheet = new SpriteSheet("/sheet.png", 256);
	public static SpriteSheet zombies = new SpriteSheet("/zombiesheet.png", 384);
	
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		this.pixels = new int[SIZE * SIZE];
		load();
	}
	
	public void load(){
		try {
			BufferedImage img = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
