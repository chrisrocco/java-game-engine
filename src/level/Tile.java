package level;

import engine.Screen;
import graphics.Sprite;

public class Tile {
	public int x, y;
	public Sprite sprite;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(Screen screen){
		
	}
	
	public boolean solid(){
		return false;
	}
}
