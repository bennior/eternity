package game.gameselection;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import game.world.World;

import static main.Panel.GAME_HEIGHT;
import static main.Panel.GAME_WIDTH;

import help.Help;
import inputs.MouseInputs;
import main.GameStates;
import main.Panel;

public class WorldCreator {
	
	private int y;
	private float fontSize;
	private TextField textField;
	public static boolean creating;
	private World world;
	
	public WorldCreator(int y, float fontSize, World world) {
		this.y = y;
		this.fontSize = fontSize;
		textField = new TextField(280, 200, 400, 40, 24f, "WORLDNAME");
		this.world = world;
	}
	
	public void draw(Graphics g, int xPos, int yPos) {
		
		String text = "CREATE NEW WORLD";
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		int x = (GAME_WIDTH - width) / 2;
		
		if(xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, y, fontSize + 1, text, new Color(255, 255, 255));
		}else {
			Help.drawString(g, y, fontSize, text, new Color(169,169,169));
		}
		
		if(creating) {
			creating(g, x, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.cxPos, MouseInputs.cyPos);
		}
	}
	
	public void update(int cxPos, int cyPos) {
		
		String text = "CREATE NEW WORLD";
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		int x = (GAME_WIDTH - width) / 2;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			Help.resetClickPos();
			creating = true;
			textField.clearTextField();
		}
		
		if(creating) {
			creating(x, MouseInputs.cxPos, MouseInputs.cyPos);
		}
	}
	
	
	//CREATING SCREEN
	private void creating(Graphics g, int x, int xPos, int yPos, int cxPos, int cyPos) {
		int lx = x, ly = textField.y + textField.height + (int) fontSize + 4, lwidth = Help.getFontWidth("LOAD", fontSize), lheight = (int) fontSize;
		int bx = x, by = textField.y + textField.height + (int) fontSize + (8 + lheight), bwidth = Help.getFontWidth("BACK", fontSize), bheight = (int) fontSize;
		
		g.setColor(new Color(0, 0, 0, 175));
		g.fillRect(0, 0, (int) (GAME_WIDTH * Panel.GAME_SCALE_WIDTH), (int) (GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT));
		
		textField.draw(g, cxPos, cyPos);	
		if(xPos >= lx * Panel.GAME_SCALE_WIDTH && xPos <= (lx + lwidth) * Panel.GAME_SCALE_WIDTH && yPos <= ly * Panel.GAME_SCALE_HEIGHT && yPos >= (ly - lheight) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, lx, ly, fontSize, "LOAD", new Color(255, 255, 255));
		}else {
			Help.drawString(g, lx, ly, fontSize, "LOAD", new Color(220,220,220));
		}
		
		if(xPos >= bx * Panel.GAME_SCALE_WIDTH && xPos <= (bx + bwidth) * Panel.GAME_SCALE_WIDTH && yPos <= by * Panel.GAME_SCALE_HEIGHT && yPos >= (by - bheight) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, bx, by, fontSize, "BACK", new Color(255, 255, 255));
		}else {
			Help.drawString(g, bx, by, fontSize, "BACK", new Color(220,220,220));
		}
	}
	
	private void creating(int x, int cxPos, int cyPos) {
		int px = x, py = textField.y + textField.height + (int) fontSize + 4, pwidth = Help.getFontWidth("LOAD", fontSize), pheight = (int) fontSize;
		int bx = x, by = textField.y + textField.height + (int) fontSize + (8 + pheight), bwidth = Help.getFontWidth("BACK", fontSize), bheight = (int) fontSize;
		
		textField.update(cxPos, cyPos);
		if(cxPos >= px * Panel.GAME_SCALE_WIDTH && cxPos <= (px + pwidth) * Panel.GAME_SCALE_WIDTH && cyPos <= py * Panel.GAME_SCALE_HEIGHT && cyPos >= (py - pheight) * Panel.GAME_SCALE_HEIGHT) {
			if(!GameSelection.ifWorldExists(textField.getInput())) {
				//set up new world
				creating = false;
				world.createWorld(textField.getInput());
				GameStates.currentGameState = GameStates.GAME;
			}
		}	
		if(cxPos >= bx * Panel.GAME_SCALE_WIDTH && cxPos <= (bx + bwidth) * Panel.GAME_SCALE_WIDTH && cyPos <= by * Panel.GAME_SCALE_HEIGHT && cyPos >= (by - bheight) * Panel.GAME_SCALE_HEIGHT) {
			creating = false;
		}
	}
}
