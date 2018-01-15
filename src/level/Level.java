package level;

import java.util.ArrayList;

import engine.Screen;
import entities.Mob;

public class Level {
	
	int width, height;
	int[] tiles;
	public ArrayList<Mob> mobs = new ArrayList<Mob>();
	
	public Level(int width, int height){
		this.height = height;
		this.width = width;
		tiles = new int[width * height];
		generateLevel();
	}

	private void generateLevel() {
	}
	
	public void loadLevel(String path){
		
	}
	
	public void render(Screen screen){
		screen.renderLevel(this);
	}
	
	public void update(){
		for(Mob m: mobs){
			m.update();
		}
	}
	
	public void time(){
		
	}

}
