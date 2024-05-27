package game.gameselection;

import static main.Panel.GAME_HEIGHT;
import static main.Panel.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import game.world.World;
import generation.AutoTiling;
import generation.overworld.Vegetation;
import help.Help;
import help.Image;
import inputs.MouseInputs;
import main.GameStates;
import main.Panel;
import menu.MenuButton;

public class GameSelection {
	
	private BufferedImage vegetation, worldimage;
	private BufferedImage[][] bwimages, bvimages;
	private ArrayList<WorldSelector> worlds;
	private ArrayList<String> names;
	private WorldCreator creator;
	private MenuButton back;
	public static boolean changing;
	private World world;

	public GameSelection(World world) {
		createFolder();
		
		creator = new WorldCreator(410, 25f, world);
		back = new MenuButton(480, 30f, "RETURN");
		worldimage = Image.getImage(Image.MENU_WORLD_TILEMAP);
		vegetation = Image.getImage(Image.MENU_VEGETATION_TILEMAP);
		bwimages = AutoTiling.autotile(worldimage);
		bvimages = Vegetation.vegTiles(vegetation);
		worlds = new ArrayList<WorldSelector>();
		names = new ArrayList<String>();
		this.world = world;
		
		initWorldNames();
		initWorldSelectors();
	}

	public void draw(Graphics g) {
				
		for(int i = 0; i < worldimage.getWidth(); i++) {
			for(int j = 0; j < worldimage.getHeight(); j++) {
				g.drawImage(bwimages[i][j], 30 * i, 30 * j, null);
				if(vegetation.getRGB(i, j) == -256) {
					g.drawImage(bvimages[i][j], 30 * i, 30 * j - 9, null); 
				}else if(vegetation.getRGB(i, j) == -65281){
					g.drawImage(bvimages[i][j], 30 * i, 30 * j - 14, null);
				}else {
					g.drawImage(bvimages[i][j], 30 * i, 30 * j, null);
				}
			}
		}
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		for(WorldSelector world : worlds) {
			world.draw(g, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.cxPos, MouseInputs.cyPos);
		}
		
		back.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		creator.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		
		for(WorldSelector world : worlds) {
			if(world.getChanging()) {
				world.changing(g, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.cxPos, MouseInputs.cyPos);
			}
		}
	}
	
	
	public void update() {
		
		
		for(WorldSelector selector : worlds) {
			selector.update(MouseInputs.cxPos, MouseInputs.cyPos);			
		}
		creator.update(MouseInputs.cxPos, MouseInputs.cyPos);
		back.update(MouseInputs.cxPos, MouseInputs.cyPos, GameStates.MENU);
		
		updateChanging();
		updateWorldNames();
		updateWorldSelectors();
		
		change();
		delete();
	}
	
	private void createFolder() {
		File worldsFolder = new File("worlds");
		if(!worldsFolder.exists()) {
			worldsFolder.mkdir();
		}
	}
	
	private void delete() {
		for(int i = 0; i < worlds.size(); i++) {
			if(worlds.get(i).delete()) {
				File delWorld = new File("worlds/" + worlds.get(i).worldsName);
				deleteFolder(delWorld);
				worlds.remove(worlds.get(i));
				names.remove(names.get(i));
			}
		}
		
		
	}
	
	private void change() {
		for(int i = 0; i < worlds.size(); i++) {
			if(worlds.get(i).change()) {
				File changeWorld = new File("worlds/" + worlds.get(i).worldsName);
				File newWorld = new File("worlds/" + worlds.get(i).getWorldName());
				if(changeWorld.renameTo(newWorld)) {
					names.set(i, worlds.get(i).getWorldName());
					worlds.get(i).setWorldName();
				}
				worlds.get(i).setChange(false);
			}
		}
			
	}
	
	private void updateChanging() {
		for(WorldSelector world : worlds) {
			if(world.getChanging()) {
				changing = true;
			}
		}
	}
	
	private void initWorldSelectors() {
		for(int i = 0; i < names.size(); i++) {
				worlds.add(new WorldSelector(names.get(i), 275, i * 40, 24f, world));
		}
	}
	
	private void updateWorldSelectors() {
		if(names.size() > worlds.size()) {
			worlds.add(new WorldSelector(names.get(names.size() - 1), 275, (names.size() - 1) * 40, 24f, world));
		}
	}
	
	private void initWorldNames() {
		File worldsFolder = new File("worlds");
		for(int i = 0; i < worldsFolder.list().length; i++) {
			names.add(worldsFolder.list()[i]);
		}
	}
	
	private void updateWorldNames() {
		File worldsFolder = new File("worlds");
		for(int i = 0; i < worldsFolder.list().length; i++) {
			if(worldsFolder.list().length > names.size()) {
				if(!names.contains(worldsFolder.list()[i])) {
					names.add(worldsFolder.list()[i]);
				}
			}
		}
	}
	
	public static boolean ifWorldExists(String worldName) {
		File worldsFolder = new File("worlds");
		for(int i = 0; i < worldsFolder.list().length; i++) {
			if(worldName.equals(worldsFolder.list()[i])) {
				return true;
			}
		}
		return false;
	}
	
	private void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
}
