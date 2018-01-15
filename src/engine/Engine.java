package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import entities.Player;
import input.Keyboard;
import level.Level;
import level.ZombieLevel;

public class Engine extends Canvas implements Runnable{
	
	/* DISPLAY */
	int WIDTH = 800;
	int HEIGHT = WIDTH / 16 * 9; // 16 by 9 aspect ratio
	int scale = 1;
	//BufferedImage backBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	//int[] pixels = ((DataBufferInt) backBuffer.getRaster().getDataBuffer()).getData();
	Screen screen;
	Frame frame;
	
	/* INPUT */
	Keyboard keyboard = new Keyboard();
	
	/* EXECUTION */
	Thread thread;
	boolean running;
	
	/* PLAYER */
	public static Player player;
	Level level;
	
	
	
	// Constructor
	public Engine(){
		this.setPreferredSize(new Dimension(WIDTH * scale, HEIGHT * scale));
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = new Frame(this);
		
		
		
		this.addKeyListener(keyboard);
		player = new Player(WIDTH/2, HEIGHT/2, keyboard);
		this.level = new ZombieLevel(WIDTH, HEIGHT);
	}
	
	
	
	
	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		double delta = 0;
		long last = System.nanoTime();
		final double NS = 1000000000 / 60;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(running){
			long now = System.nanoTime();
			delta += (now - last) / NS;
			last = now;
			if(delta >= 1){
				update(); updates++;
				
				delta--;
			}
			render();
			frames++;
			
			// Display system performance
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle("2D Game Engine | " + updates + "ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		this.stop();
	}

	private void update() {
		keyboard.update();
		player.update(); System.out.println(player.x + ", " + player.y);
		level.update();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		//screen.render();
		if(player.isAlive()) player.render(screen);
		level.render(screen);
		
		/*
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		*/
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(screen.backBuffer, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void displayStats(Graphics2D g2d) {
		/* DISPLAY STATS - TROUBLESHOOTING */
		g2d.setColor(Color.black);
		g2d.drawString("Player Pos: " + player.x + ", " + player.y, 20, 20);
		g2d.drawString("Player X Vel: " + player.dx, 20, 40);
		g2d.drawString("Player Y Vel: " + player.dy, 20, 60);
		g2d.drawString("Player Angle: " + Math.toDegrees(player.angle), 20, 80);
		if (player.boosting) {
			g2d.setColor(Color.BLUE);
			g2d.drawString("BOOST", 20, 100);
			g2d.setColor(Color.black);
		}
	}
	
	public void start(){
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public void stop(){
		try{
			running = false;
			thread.join();
		} catch ( Exception e ){
			e.printStackTrace();
		}
	}

}
