package game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import generation.AutoTiling;
import generation.WorldGeneration;
import help.Encryption;
import help.FileHelp;

public class World {
	
	private String seed;
	private WorldGeneration generation;
	private BufferedImage world;
	private BufferedImage[][] tiles;
	private Camera camera;
	private Player player;
	
	public World() {
		generation = new WorldGeneration();
		world = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
	}

	public void initWorld(String name) {
		//initialize world
		try {
			seed = FileHelp.readFile("worlds/" + name + "/seed", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		world = Encryption.decode(seed);
		tiles = AutoTiling.autotile(world);
		camera = new Camera(tiles, 500, 500);
		player = new Player(0, 0);
	}
	
	public void createWorld(String name) {
		createFolder(name);
		generateWorld(name);
		createCamera();
	}
	
	public void draw(Graphics g) {
		camera.draw(g);
		player.draw(g);
	}
	
	public void update() {
		camera.update();
//		player.update();
	}
	
	private void createCamera() {
		camera = new Camera(tiles, 500, 500);
		player = new Player(0, 0);
	}
	
	private void createFolder(String name) {
		File worldsFolder = new File("worlds/" + name);
		if(!worldsFolder.exists()) {
			worldsFolder.mkdir();
		}
	}
	
	private void generateWorld(String name) {
		world = generation.createWorld();
		tiles = AutoTiling.autotile(world);
		seed = Encryption.encode(world);
		try {
			FileHelp.createFile("worlds/" + name + "/seed");
			FileHelp.writeFile("worlds/" + name + "/seed", seed);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
