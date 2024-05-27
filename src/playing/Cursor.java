package playing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import blocks.Block;
import game.world.Camera;
import game.world.Player;
import generation.TileRGB;
import help.Help;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.GameLoop;
import main.Panel;
import options.keybinds.OKeyBinds;

public class Cursor {
	
	public void drawCursor(Graphics g, int offsetx, int offsety, int xPos, int yPos, int dxPos, int dyPos) {
		
		int x = (int) ((xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
		int y = (int) ((yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		if(MouseInputs.dragged) {		
			x = (int) ((dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
			y = (int) ((dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		}
		
		if((Math.abs(rightX - x) <= 1 || Math.abs(leftX - x) <= 1) && (Math.abs(bottomY - y) <= 1 || Math.abs(topY - y) <= 1)) {
			if(!(leftX - x == 1 && topY - y == 1) && !(rightX - x == -1 && topY - y == 1) && !(leftX - x == 1 && bottomY - y == -1) && !(rightX - x == -1 && bottomY - y == -1)) {
				g.setColor(Color.WHITE);
				g.drawRect(x*30 + offsetx, y*30 + offsety, 30, 30);
			}
		}
		
		
	}
	
	public int getMouseTilePosition(BufferedImage world, int offsetx, int offsety, int xPos, int yPos, int dxPos, int dyPos, int tx, int ty) {
		
		int x = (int) ((xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
		int y = (int) ((yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		
		if(MouseInputs.dragged) {		
			x = (int) ((dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
			y = (int) ((dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		}		
		return world.getRGB(x + tx, y + ty);
	}
	
	public int getCursorX(int offsetx, int offsety, int xPos, int yPos, int dxPos, int dyPos) {
		int output = 2000;
		
		int x = (int) ((xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
		int y = (int) ((yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		if(MouseInputs.dragged) {		
			x = (int) ((dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
			y = (int) ((dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		}
		
		if((Math.abs(rightX - x) <= 1 || Math.abs(leftX - x) <= 1) && (Math.abs(bottomY - y) <= 1 || Math.abs(topY - y) <= 1)) {
			if(!(leftX - x == 1 && topY - y == 1) && !(rightX - x == -1 && topY - y == 1) && !(leftX - x == 1 && bottomY - y == -1) && !(rightX - x == -1 && bottomY - y == -1)) {
				output = x;
			}
		}
		return output;
	}
	
	public int getCursorY(int offsetx, int offsety, int xPos, int yPos, int dxPos, int dyPos) {
		int output = 2000;
		
		int x = (int) ((xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
		int y = (int) ((yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		if(MouseInputs.dragged) {		
			x = (int) ((dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
			y = (int) ((dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		}
		
		if((Math.abs(rightX - x) <= 1 || Math.abs(leftX - x) <= 1) && (Math.abs(bottomY - y) <= 1 || Math.abs(topY - y) <= 1)) {
			if(!(leftX - x == 1 && topY - y == 1) && !(rightX - x == -1 && topY - y == 1) && !(leftX - x == 1 && bottomY - y == -1) && !(rightX - x == -1 && bottomY - y == -1)) {
				output = y;
			}
		}
		return output;
	}
}
