package level;

import java.util.ArrayList;

import engine.Engine;
import engine.Screen;
import entities.Mob;
import entities.Player;
import entities.Zombie;

public class ZombieLevel extends Level {
	
	Player player;
	
	public ZombieLevel(int width, int height) {
		super(width, height);
		player = Engine.player;
		
		mobs.add(new Zombie(20, 20));
	}
	
	public void update(){
		for(Mob m: mobs){
			m.goTo(player.x, player.y);
			m.move();
		}
	}

}
