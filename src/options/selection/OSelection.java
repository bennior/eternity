package options.selection;

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


import static options.GameOptions.*;

public class OSelection {
	
	private OSelectionButton graphics, audio, keybinds;
	private GameFiles gameFiles;
	private MenuButton back;
	
	public OSelection() {
		gameFiles = new GameFiles();
		gameFiles.createOptions();
		
		graphics = new OSelectionButton(235, 30f, "GRAPHICS");
		audio = new OSelectionButton(285, 30f, "AUDIO");
		keybinds = new OSelectionButton(335, 30f, "KEYBINDS");
		back = new MenuButton(450, 30f, "RETURN");
	}
	
	public void draw(Graphics g) {
		
		Help.drawString(g, 110, 40f, "OPTIONS", new Color(255, 255, 255));
		
		graphics.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		audio.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		keybinds.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		back.draw(g, MouseInputs.xPos, MouseInputs.yPos);
	}
	
	public void update() {
		graphics.update(MouseInputs.cxPos, MouseInputs.cyPos, GRAPHICS);
		audio.update(MouseInputs.cxPos, MouseInputs.cyPos, AUDIO);
		keybinds.update(MouseInputs.cxPos, MouseInputs.cyPos, KEYBINDS);
		back.update(MouseInputs.cxPos, MouseInputs.cyPos, GameStates.MENU);
	}	
}
