package game.world;

import java.awt.Color;
import java.awt.Graphics;

import help.Help;
import inputs.KeyInputs;
import main.Panel;
import options.keybinds.OKeyBinds;

public class Player {
	
	public int x = 465, y = 255, width = 30, height = 30;
	public int offsetX, offsetY;

	public Player(int offsetX, int offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillRect((int) ((x - width / 2) * Panel.GAME_SCALE_WIDTH), (int) ((y - height / 2) * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
	}
	
	public void update() {
		
//		if(KeyInputs.key.equals(OKeyBinds.up.getKey())) {
//			if(offsetY - 5 > -maxOffset) {
//				y -= 5;
//				offsetY -= 5;
//				Help.resetKey();
//			}
//		}
//		if(KeyInputs.key.equals(OKeyBinds.down.getKey())) {
//			if(offsetY + 5 < maxOffset) {
//				y += 5;
//				offsetY += 5;
//				Help.resetKey();
//			}
//		}	
//		if(KeyInputs.key.equals(OKeyBinds.left.getKey())) {
//			if(offsetX - 5 > -maxOffset) {
//				x -= 5;
//				offsetX -= 5;
//				Help.resetKey();
//			}
//		}
//		if(KeyInputs.key.equals(OKeyBinds.right.getKey())) {
//			if(offsetX + 5 < maxOffset) {
//				x += 5;
//				offsetX += 5;
//				Help.resetKey();
//			}
//		}
	}
}
