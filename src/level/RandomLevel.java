package level;

import java.util.Random;

public class RandomLevel extends Level{

	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	public void generateLevel(){
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = new Random().nextInt(4);
			}
		}
	}

}
