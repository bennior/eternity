package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import game.gameselection.GameSelection;
import game.world.World;
import inputs.KeyInputs;
import inputs.MouseInputs;
import menu.Menu;
import options.GameOptions;

public class Panel extends JPanel{
	
	public static final int GAME_WIDTH = 960;
	public static final int GAME_HEIGHT = 540;
	public static final int DEFAULT_TILE_SIZE = 30;
	public static final int TILES_IN_WIDTH = GAME_WIDTH / DEFAULT_TILE_SIZE; //32
	public static final int TILES_IN_HEIGHT = GAME_HEIGHT / DEFAULT_TILE_SIZE; //18
	public static float TILE_WIDTH = GAME_WIDTH / TILES_IN_WIDTH;
	public static float TILE_HEIGHT = GAME_HEIGHT / TILES_IN_HEIGHT;
	public static float GAME_SCALE_WIDTH = TILE_WIDTH / DEFAULT_TILE_SIZE;
	public static float GAME_SCALE_HEIGHT = TILE_HEIGHT / DEFAULT_TILE_SIZE;
	
//	private Color grass = new Color(0,128,0);
//	private Color sand = new Color(240,230,140);
//	private Color water = new Color(0,0,255);
//	private Color stone = new Color(220, 220, 220);
	
	private MouseInputs mouseInputs;
	private KeyInputs keyInputs;
	private Menu menu;
	private GameOptions options;
	private GameSelection play;
	private World world;
	
	public Panel() {
		init();
		requestFocus();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(new Color(0, 0, 0));
		//Rendering		
		switch(GameStates.currentGameState) {
		case GAME: world.draw(g);
			break;
		case MENU: menu.draw(g);
			break;
		case OPTIONS: options.draw(g);
			break;
		case PLAY: play.draw(g);
			break;
		case PROFIL:
			break;		
		}
	}
	
	public void update() {		
		switch(GameStates.currentGameState) {
		case GAME: world.update();
			break;
		case MENU: menu.update();
			break;
		case OPTIONS: options.update();
			break;
		case PLAY: play.update();
			break;
		case PROFIL:
			break;		
		}
	}
	
	public void changePanelSize(Dimension dimension) {
		setPreferredSize(dimension);
	}
	
	private void init() {
		//initialise			
		mouseInputs = new MouseInputs();
		keyInputs = new KeyInputs();
		menu = new Menu();
		options = new GameOptions();
		world = new World();
		play = new GameSelection(world);
		
		addMouseMotionListener(mouseInputs);
		addMouseListener(mouseInputs);
		addKeyListener(keyInputs);
	}
}
