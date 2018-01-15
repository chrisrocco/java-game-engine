package engine;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(Canvas canvas){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(canvas);
		pack(); // Size the screen to fit the Canvas
		setLocationRelativeTo(null); // Center on screen
		setVisible(true); // Show
	}
}
