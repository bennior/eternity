package options;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import generation.AutoTiling;
import help.Image;
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
	
	private BufferedImage background;
	private BufferedImage[][] bimages;
	
	public GameOptions() {
		selection = new OSelection();
		graphics = new OGraphics();
		audio = new OAudio();
		keybinds = new OKeyBinds();
		
		background = Image.getImage(Image.OPTIONS_TILEMAP);
		bimages = AutoTiling.autotile(background);
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < background.getWidth(); i++) {
			for(int j = 0; j < background.getHeight(); j++) {
				g.drawImage(bimages[i][j],(int) (30 * i * Panel.GAME_SCALE_WIDTH), (int) (30 * j * Panel.GAME_SCALE_HEIGHT), (int) (30 * Panel.GAME_SCALE_WIDTH), (int) (30 * Panel.GAME_SCALE_HEIGHT), null);
			}
		}
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, (int) (GAME_WIDTH * Panel.GAME_SCALE_WIDTH), (int) (GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT));
		
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
			break;
		case GRAPHICS: graphics.update();
			break;
		case AUDIO: audio.update();
			break;
		case KEYBINDS: keybinds.update();
			break;
		}
	}
 }
