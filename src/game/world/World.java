package game.world;

import static main.Panel.GAME_HEIGHT;
import static main.Panel.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import game.gameoptions.GOSelection;
import generation.AutoTiling;
import generation.caves.CaveEntries;
import generation.caves.CaveGeneration;
import generation.overworld.Vegetation;
import generation.overworld.WorldGeneration;
import help.Encryption;
import help.FileHelp;
import help.Help;
import inputs.KeyInputs;
import lighting.DayLightCicle;
import main.GameLoop;
import playing.Cursor;

public class World {
	
	private String name, seed, vegSeed, caveSeed;
	private WorldGeneration generation;
	private CaveGeneration caveGeneration;
	private CaveEntries entries;
	private BufferedImage world, vegetation, cave;
	private BufferedImage[][] tiles, vegTiles, caveTiles;
	private Camera camera;
	private Player player;
	private GOSelection goSelection;
	private DayLightCicle cicle;
	private Vegetation veg;
	private Cursor cursor;
	
	public final static int UNPAUSED = 1;
	public final static int PAUSED = 2;
	public static int WORLDSTATE;
		
	public World() {
		veg = new Vegetation();
		goSelection = new GOSelection();
		generation = new WorldGeneration(1000, 1000);
		caveGeneration = new CaveGeneration(1000, 1000);
		entries = new CaveEntries();
		cursor = new Cursor();
	}
	
	public void draw(Graphics g) {
		switch(WORLDSTATE) {
		case UNPAUSED:
			camera.draw(g);
			player.draw(g);
			cicle.draw(g);
//			cursor.drawCursor(g, camera.offsetx, camera.offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos);
			break;
		case PAUSED:
			camera.draw(g);
			player.draw(g);
			cicle.draw(g);
			
			g.setColor(new Color(0, 0, 0, 200));
			g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
			
			goSelection.draw(g);
			break;
		}
	}
	
	public void update() {		
		if(WORLDSTATE == UNPAUSED) {
			if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE || !GameLoop.hasFocus) { //escape
				Help.resetClickPos();
				WORLDSTATE = PAUSED;
				Help.resetKeyCode();
			}
		}
		
		switch(WORLDSTATE) {
		case UNPAUSED:
			camera.update(player.x, player.y, player.DEFAULT_X, player.DEFAULT_Y, world, cave);
			player.update(camera.tx, camera.ty, camera.offsetx, camera.offsety, camera.speed, camera.getPlayerTilePosition(world), cursor, world, vegetation, cave);
			cicle.update();
			break;
		case PAUSED:
			goSelection.update(this);
			break;
		}
	}

	public void initWorld(String name) {
		this.name = name;
		//initialize world
		try {
			seed = FileHelp.readFile("worlds/" + name + "/seed", 0);
			vegSeed = FileHelp.readFile("worlds/" + name + "/vegetation", 0);
			caveSeed = FileHelp.readFile("worlds/" + name + "/cave", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//decoding
		world = Encryption.decode(seed);
		vegetation = Encryption.decode(vegSeed);
		cave = Encryption.decode(caveSeed);
		//autotile
		tiles = AutoTiling.autotile(world);
		vegTiles = Vegetation.vegTiles(vegetation);
		caveTiles = AutoTiling.autotileCave(cave);
		//other
		camera = new Camera(tiles, vegTiles, caveTiles, vegetation, 500, 500, 0, 0, cursor);
		player = new Player(camera.chunk.length, camera.chunk[0].length, camera.tiles.length, camera.tiles[0].length);
		cicle = new DayLightCicle(200, 0.5f, player);
		Help.resetKey();
		WORLDSTATE = UNPAUSED;
		Camera.CURRENTWORLD = Camera.OVERWORLD; //depens where game ended
		
	}
	
	public void createWorld(String name) {
		createFolder(name);
		generateWorld(name);
		Help.resetKey();
		WORLDSTATE = UNPAUSED;
		Camera.CURRENTWORLD = Camera.OVERWORLD;
	}
	
	private void createFolder(String name) {
		File worldsFolder = new File("worlds/" + name);
		if(!worldsFolder.exists()) {
			worldsFolder.mkdir();
		}
	}
	
	private void generateWorld(String name) {	
		this.name = name;
		//generation
		world = generation.createWorld();	
		cave = caveGeneration.createCave();
		world = entries.createCaveEntries(world, cave);
		vegetation = veg.createVegetation(generation.getVegetation());
		//autotiling
		tiles = AutoTiling.autotile(world);
		vegTiles = Vegetation.vegTiles(vegetation);
		caveTiles = AutoTiling.autotileCave(cave);
		//encoding
		seed = Encryption.encode(world);
		vegSeed = Encryption.encode(vegetation);
		caveSeed = Encryption.encode(cave);
		//other
		camera = new Camera(tiles, vegTiles, caveTiles, vegetation, 500, 500, 0, 0, cursor); //create camera
		player = new Player(camera.chunk.length, camera.chunk[0].length, camera.tiles.length, camera.tiles[0].length); //create player
		cicle = new DayLightCicle(200, 0.5f, player);
		try {
			FileHelp.createFile("worlds/" + name + "/seed");
			FileHelp.createFile("worlds/" + name + "/daylight");
			FileHelp.createFile("worlds/" + name + "/vegetation");
			FileHelp.createFile("worlds/" + name + "/cave");
			
			FileHelp.writeFile("worlds/" + name + "/seed", seed);
			FileHelp.writeFile("worlds/" + name + "/vegetation", vegSeed);
			FileHelp.writeFile("worlds/" + name + "/cave", caveSeed);
			
//			File file = new File("worlds/" + name + "/vegetation.png");
//			ImageIO.write(vegetation, "png", file);
//			
//			File file2 = new File("worlds/" + name + "/world.png");
//			ImageIO.write(world, "png", file2);
	
//			File file3 = new File("worlds/" + name + "/cave.png");
//			ImageIO.write(cave, "png", file3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveWorld() { 
		
		seed = Encryption.encode(world);
		vegSeed = Encryption.encode(vegetation);
		caveSeed = Encryption.encode(cave);
				
		try {
			FileHelp.clearFile("worlds/" + name + "/seed");
			FileHelp.clearFile("worlds/" + name + "/vegetation");
			FileHelp.clearFile("worlds/" + name + "/cave");
			
			FileHelp.writeFile("worlds/" + name + "/seed", seed);
			FileHelp.writeFile("worlds/" + name + "/vegetation", vegSeed);
			FileHelp.writeFile("worlds/" + name + "/cave", caveSeed);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
