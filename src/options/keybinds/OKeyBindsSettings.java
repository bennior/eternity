package options.keybinds;

import static main.Panel.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import help.Help;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.Panel;

public class OKeyBindsSettings {
	
	private int x, y, tick = 0;
	private String key, keybind, standartBind;
	private float fontSize;
	
	private boolean listeningForBind;
	
	public OKeyBindsSettings(int x, int y, String keybind, float fontSize, String standartBind) {
		this.x = x;
		this.y = y;
		this.keybind = keybind;
		this.fontSize = fontSize;
		this.standartBind = standartBind;
	}
	
	public void draw(Graphics g, int xPos, int yPos) {
		
		int width = GAME_WIDTH - 2 * x;
		int width1 = Help.getFontWidth(key, fontSize);
		int width2 = Help.getFontWidth(key, fontSize + 1);
		int height = (int) fontSize;
		
		if(OKeyBinds.checkIfDuplicate(key)) {
			if((xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) || listeningForBind) {
				Help.drawString(g, x, y, fontSize + 1, keybind, new Color(220,20,60));
				Help.drawString(g, GAME_WIDTH - (25 + width2), y, fontSize + 1, key, new Color(220,20,60));
			}else {
				Help.drawString(g, x, y, fontSize, keybind, new Color(220,20,60));
				Help.drawString(g, GAME_WIDTH - (25 + width1), y, fontSize, key, new Color(220,20,60));
			}
		}else {
			if((xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) || listeningForBind) {
				Help.drawString(g, x, y, fontSize + 1, keybind, new Color(255, 255, 255));
				Help.drawString(g, GAME_WIDTH - (25 + width2), y, fontSize + 1, key, new Color(255, 255, 255));
			}else {
				Help.drawString(g, x, y, fontSize, keybind, new Color(169,169,169));
				Help.drawString(g, GAME_WIDTH - (25 + width1), y, fontSize, key, new Color(169,169,169));
			}
		}
	}
	
	public void update(int cxPos, int cyPos) {
		
//		int width = GAME_WIDTH - 2 * x;
//		int height = (int) fontSize;
//		
//		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
//			listeningForBind();
//		}
	}
	
	public void drawListeningForBind(Graphics g) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		if(listeningForBind) {
			Help.messageBox(g2D, "PRESS THE", "DESIRED KEY");
		}
	}
	
	public void setKey(String key) {
		if(key.isEmpty()) {
			this.key = standartBind.toUpperCase();
		}else {
			this.key = key.toUpperCase();
		}
	}
	
	public String getKey() {
		return key;
	}
	
	private void listeningForBind() {
		listeningForBind = true;
		
		if(listeningForBind) {
			MouseInputs.stopListening = true;
			
			if(tick < 1) {
				KeyInputs.pressed = false;
				MouseInputs.clicked = false;
				tick++;
			}
			
			if(KeyInputs.pressed) {
					key = KeyInputs.key.toUpperCase();
					
					listeningForBind = false;
					MouseInputs.stopListening = false;
					Help.resetKey();
					Help.resetClickPos();
					tick = 0;
				}
			else if(MouseInputs.clicked) {
					key = listenForMouseButton();
	
					listeningForBind = false;
					MouseInputs.stopListening = false;
					Help.resetKey();
					Help.resetClickPos();
					tick = 0;
				}
			}
	}
	
	private String listenForMouseButton() {
		String key = switch(MouseInputs.mouseButton) {
		case 1 -> "LEFT BUTTON";
		case 2 -> "MIDDLE BUTTON";
		case 3 -> "RIGHT BUTTON";
		default -> "BUTTON " + MouseInputs.mouseButton;
		};
		return key;
	}
	
	public boolean getListeningForBind() {
		return listeningForBind;
	}
}
