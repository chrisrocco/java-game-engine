package entities;

import java.util.ArrayList;

import graphics.Sprite;

public class Zombie extends Mob{
	
	
	
	public Zombie(int x, int y) {
		super(x, y, Sprite.zombie_d);
		this.SPEED = 2;
	}

	@Override
	public void update() {
		
	}

}
