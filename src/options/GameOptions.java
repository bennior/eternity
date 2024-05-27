package options;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import game.world.World;
import generation.AutoTiling;
import generation.overworld.Vegetation;
import help.Help;
import help.Image;
import inputs.KeyInputs;
import main.GameStates;
import main.Panel;
import options.audio.OAudio;
import options.graphics.OGraphics;
import options.keybinds.OKeyBinds;
import options.selection.OSelection;

import static main.Panel.*;

public class GameOptions {
	
	public final static int SELECTION = 1;
	public final static int GRAPHICS = 2;
	public final static int AUDIO = 3;
	public final static int KEYBINDS = 4;
	
	public static int OPTIONSSTATE = SELECTION;
	
	private OSelection selection;
	private OGraphics graphics;
	private OAudio audio;
	private OKeyBinds keybinds;
	
	private BufferedImage vegetation, world;
	private BufferedImage[][] bwimages, bvimages;
	
	public GameOptions() {
		selection = new OSelection();
		graphics = new OGraphics();
		audio = new OAudio();
		keybinds = new OKeyBinds();
		
		world = Image.getImage(Image.MENU_WORLD_TILEMAP);
		vegetation = Image.getImage(Image.MENU_VEGETATION_TILEMAP);
		bwimages = AutoTiling.autotile(world);
		bvimages = Vegetation.vegTiles(vegetation);
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < world.getWidth(); i++) {
			for(int j = 0; j < world.getHeight(); j++) {
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
		
		switch(OPTIONSSTATE) {
		case SELECTION: selection.draw(g);
			break;
		case GRAPHICS: graphics.draw(g);
			break;
		case AUDIO: audio.draw(g);
			break;
		case KEYBINDS: keybinds.draw(g);
			break;
		}
	}
	
	public void update() {
		switch(OPTIONSSTATE) {
		case SELECTION: selection.update();
			       		escToMenu();
			break;
		case GRAPHICS: graphics.OUpdate();
					   escToSelection();
			break;
		case AUDIO: audio.OUpdate();
				    escToSelection();
			break;
		case KEYBINDS: keybinds.OUpdate();
					   escToSelection();
			break;
		}
	}
	
	private void escToMenu() {
		if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE) {
			GameStates.currentGameState = GameStates.MENU;
			Help.resetKeyCode();
		}
	}

	private void escToSelection() {
		if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE) {
			OPTIONSSTATE = SELECTION;
			Help.resetKeyCode();
			GameFiles.saveOptions();
		}
	}
 }
