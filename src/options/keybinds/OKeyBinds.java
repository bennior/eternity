package options.keybinds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import game.gameoptions.GOSelectionButton;
import help.Help;
import inputs.MouseInputs;
import options.GameFiles;
import options.GameOptions;

public class OKeyBinds {

	public static OKeyBindsSettings up, left, right, down, destroy, place, inventory;
	private static OKeyBindsSettings enter_exit, load, escape_back; //enter/exit = SPACE; load = ENTER; escape/back = ESCAPE;
	private OKeyBindsButton Oback;
	private GOSelectionButton GOback;
	public static String[] keybinds = {"up", "left", "right", "down", "inventory", "break", "place"};
	
	public OKeyBinds() {
		//Scroll function
		up = new OKeyBindsSettings(20, 115, "UP", 27f, "W");
		left = new OKeyBindsSettings(20, 158, "LEFT", 27f, "A");
		right = new OKeyBindsSettings(20, 201, "RIGHT", 27f, "D");
		down = new OKeyBindsSettings(20, 244, "DOWN", 27f, "S");
		inventory = new OKeyBindsSettings(20, 287, "INVENTORY", 27f, "E");
		destroy = new OKeyBindsSettings(20, 330, "BREAK", 27f, "LEFT BUTTON");
		place = new OKeyBindsSettings(20, 373, "PLACE", 27f, "RIGHT BUTTON");
		//hidden/already binded keys
		enter_exit = new OKeyBindsSettings(0, 0, "ENTER/EXIT", 0f, KeyEvent.getKeyText(KeyEvent.VK_SPACE).toUpperCase());
		load = new OKeyBindsSettings(0, 0, "LOAD", 0f, KeyEvent.getKeyText(KeyEvent.VK_ENTER).toUpperCase());
		escape_back = new OKeyBindsSettings(0, 0, "ESCAPE/BACK", 0f, KeyEvent.getKeyText(KeyEvent.VK_ESCAPE).toUpperCase());

		Oback = new OKeyBindsButton(450, 30f, "RETURN");
		GOback = new GOSelectionButton(450, 30f, "RETURN");
		initSettings();
	}
	
	public void draw(Graphics g) {
		Help.drawString(g, 110, 40f, "KEYBINDS", new Color(255, 255, 255)); //need to add a scroll function
		
		up.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		left.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		right.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		down.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		place.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		destroy.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		inventory.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		Oback.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		
		drawlisteningForBind(g);
	}
	
	public void OUpdate() {
		up.update(MouseInputs.cxPos, MouseInputs.cyPos);
		left.update(MouseInputs.cxPos, MouseInputs.cyPos);
		right.update(MouseInputs.cxPos, MouseInputs.cyPos);
		down.update(MouseInputs.cxPos, MouseInputs.cyPos);
		destroy.update(MouseInputs.cxPos, MouseInputs.cyPos);
		place.update(MouseInputs.cxPos, MouseInputs.cyPos);
		inventory.update(MouseInputs.cxPos, MouseInputs.cyPos);		
		Oback.update(MouseInputs.cxPos, MouseInputs.cyPos, GameOptions.SELECTION);
		
	}
	
	public void GOUpdate() {
		up.update(MouseInputs.cxPos, MouseInputs.cyPos);
		left.update(MouseInputs.cxPos, MouseInputs.cyPos);
		right.update(MouseInputs.cxPos, MouseInputs.cyPos);
		down.update(MouseInputs.cxPos, MouseInputs.cyPos);
		destroy.update(MouseInputs.cxPos, MouseInputs.cyPos);
		place.update(MouseInputs.cxPos, MouseInputs.cyPos);
		inventory.update(MouseInputs.cxPos, MouseInputs.cyPos);		
		GOback.save(MouseInputs.cxPos, MouseInputs.cyPos, GameOptions.SELECTION);	
	}
	
	private void drawlisteningForBind(Graphics g) {
		up.drawListeningForBind(g);
		left.drawListeningForBind(g);
		right.drawListeningForBind(g);
		down.drawListeningForBind(g);
		place.drawListeningForBind(g);
		destroy.drawListeningForBind(g);
		inventory.drawListeningForBind(g);
	}
	
	private void initSettings() {
		try {
			up.setKey(GameFiles.initOptions(9, keybinds[0]));
			left.setKey(GameFiles.initOptions(10, keybinds[1]));
			right.setKey(GameFiles.initOptions(11, keybinds[2]));
			down.setKey(GameFiles.initOptions(12, keybinds[3]));
			inventory.setKey(GameFiles.initOptions(13, keybinds[4]));
			destroy.setKey(GameFiles.initOptions(14, keybinds[5]));
			place.setKey(GameFiles.initOptions(15, keybinds[6]));
			
			//hidden keys -> takes standart key if empty
			enter_exit.setKey("");
			load.setKey("");
			escape_back.setKey("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkIfDuplicate(String key) {
		String[] keys = {up.getKey(), left.getKey(), right.getKey(), down.getKey(), inventory.getKey(), destroy.getKey(), place.getKey(), enter_exit.getKey(), load.getKey(), escape_back.getKey()}; 
		boolean checkIfDuplicate = false;
		int index = 0;
				
		for(int i = 0; i < keys.length; i++) {
				if(keys[i].equals(key)) {
					index++;
					if(index > 1) {
						checkIfDuplicate = true;
						index = 0;
					}
			}
		}
		return checkIfDuplicate;
	}
}
