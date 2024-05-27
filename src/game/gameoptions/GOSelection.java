package game.gameoptions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.world.World;
import help.Help;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.GameStates;
import menu.MenuButton;
import options.GameFiles;
import options.audio.OAudio;
import options.graphics.OGraphics;
import options.keybinds.OKeyBinds;
import options.selection.OSelectionButton;

import static options.GameOptions.*;

public class GOSelection {
	
	private GOSelectionButton unpause, options, menu, graphics, audio, keybinds, back;
	private OGraphics goGraphics;
	private OAudio goAudio;
	private OKeyBinds goKeyBinds;
	private GameFiles gameFiles;
	
	
	public static final int SELECTION = 0;
	public static final int OPTIONS = 1;
	public static final int GRAPHICS = 2;
	public static final int AUDIO = 3;
	public static final int KEYBINDS = 4;
	public static int GAMEOPTIONSSTATE = SELECTION;
	
	public GOSelection() {		
		gameFiles = new GameFiles();
		gameFiles.createOptions();
		
		unpause = new GOSelectionButton(235, 30f, "RESUME");
		options = new GOSelectionButton(285, 30f, "OPTIONS");
		menu = new GOSelectionButton(335, 30f, "MAIN MENU");
		
		graphics = new GOSelectionButton(235, 30f, "GRAPHICS");
		audio = new GOSelectionButton(285, 30f, "AUDIO");
		keybinds = new GOSelectionButton(335, 30f, "KEYBINDS");
		back = new GOSelectionButton(450, 30f, "RETURN");
		
		goGraphics = new OGraphics();
		goAudio = new OAudio();
		goKeyBinds = new OKeyBinds();
	}
	
	public void draw(Graphics g) {
				
		switch(GAMEOPTIONSSTATE) {
		case SELECTION:
			Help.drawString(g, 110, 40f, "PAUSED", new Color(255, 255, 255));
			unpause.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			options.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			menu.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			break;
		case OPTIONS:
			Help.drawString(g, 110, 40f, "OPTIONS", new Color(255, 255, 255));
			graphics.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			audio.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			keybinds.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			back.draw(g, MouseInputs.xPos, MouseInputs.yPos);
			break;
		case GRAPHICS:
			goGraphics.draw(g);
			break;
		case AUDIO:
			goAudio.draw(g);
			break;
		case KEYBINDS:
			goKeyBinds.draw(g);
			break;
		}
	}
	
	public void update(World world) {
		
		switch(GAMEOPTIONSSTATE) {
		case SELECTION:
			unpause.unpause(MouseInputs.cxPos, MouseInputs.cyPos);
			options.update(MouseInputs.cxPos, MouseInputs.cyPos, OPTIONS);
			menu.menu(MouseInputs.cxPos, MouseInputs.cyPos, world);
			escToGame();
			break;
		case OPTIONS:
			graphics.update(MouseInputs.cxPos, MouseInputs.cyPos, GRAPHICS);
			audio.update(MouseInputs.cxPos, MouseInputs.cyPos, AUDIO);
			keybinds.update(MouseInputs.cxPos, MouseInputs.cyPos, KEYBINDS);
			back.update(MouseInputs.cxPos, MouseInputs.cyPos, SELECTION);
			escToSelection();
			break;
		case GRAPHICS:
			goGraphics.GOUpdate();
			escToOptions();
			break;
		case AUDIO:
			goAudio.GOUpdate();
			escToOptions();
			break;
		case KEYBINDS:
			goKeyBinds.GOUpdate();
			escToOptions();
			break;
		}
	}
	
	private void escToGame() {
			if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE) {
				World.WORLDSTATE = World.UNPAUSED;
				Help.resetKeyCode();
			}
	}
	
	private void escToSelection() {
		if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE) {
			GAMEOPTIONSSTATE = SELECTION;
			Help.resetKeyCode();
		}
	}
	
	private void escToOptions() {
		if(KeyInputs.keyCode == KeyEvent.VK_ESCAPE) {
			GAMEOPTIONSSTATE = OPTIONS;
			GameFiles.saveOptions();
			Help.resetKeyCode();
		}
	}
}
