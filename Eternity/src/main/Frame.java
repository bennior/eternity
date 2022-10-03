package main;

import javax.swing.JFrame;
import static main.Panel.GAME_WIDTH;
import static main.Panel.GAME_HEIGHT;
import static main.Panel.TILES_IN_WIDTH;
import static main.Panel.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import static main.Panel.DEFAULT_TILE_SIZE;
import static main.Panel.TILES_IN_HEIGHT;


public class Frame extends JFrame{
	
	public Frame(Panel panel) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Eternity");
		add(panel);
	    getContentPane().setBackground(Color.BLACK);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);	
	}
	
	public void fullscreen() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	public void custom(Dimension res) {
		setSize(res.width, res.height);
		setLocationRelativeTo(null);
	}
	
	public void updateScale() {
		TILE_WIDTH = getWidth() / TILES_IN_WIDTH;
		TILE_HEIGHT = getHeight() / TILES_IN_HEIGHT;
		GAME_SCALE_WIDTH = TILE_WIDTH / DEFAULT_TILE_SIZE;
		GAME_SCALE_HEIGHT = TILE_HEIGHT / DEFAULT_TILE_SIZE;
		
	}
	
	public Dimension getSize() {
		return new Dimension(getWidth(), getHeight());
	}
}
