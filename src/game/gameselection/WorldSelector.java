package game.gameselection;

import java.awt.Color;
import java.awt.Graphics;

import game.world.World;

import static main.Panel.GAME_HEIGHT;
import static main.Panel.GAME_WIDTH;
import help.Help;
import inputs.MouseInputs;
import main.GameStates;
import main.Panel;

public class WorldSelector {
	
	public String worldsName;
	private int x, y;
	private float fontSize;
	private TextField textField;
	private World world;
	
	private boolean delete, changing, change;
		
	public WorldSelector(String worldsName, int x, int y, float fontSize, World world) {
		this.worldsName = worldsName;
		this.x = x;
		this.y = y + 60;
		this.fontSize = fontSize;
		this.world = world;
		textField = new TextField(280, 200, 400, 40, 24f, "CHANGE WORLDNAME");
	}
	
	public void draw(Graphics g, int xPos, int yPos, int cxPos, int cyPos) {
		int width = Help.getFontWidth(worldsName, fontSize);
		int height = (int) fontSize;;
		
		int x1 = x - 20 - Help.getFontWidth("PLAY", fontSize - 6);
		int x2 = x + width + 40;
		int x3 = x2 + 20 + Help.getFontWidth("EDIT", fontSize - 6);
		
		if((xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT && !GameSelection.changing && !WorldCreator.creating) || 
		  (cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT && !GameSelection.changing && !WorldCreator.creating)) {
			Help.drawString(g, x, y, fontSize + 1, worldsName, new Color(255,255,255));
			Help.drawString(g, x1, y - 1, fontSize - 6, "PLAY", new Color(169,169,169));
			Help.drawString(g, x2, y - 1, fontSize - 6, "EDIT", new Color(169,169,169));
			Help.drawString(g, x3, y - 1, fontSize - 6, "DELETE", new Color(169,169,169));
			hover(g, xPos, yPos, x1, x2, x3);
		}else {
			Help.drawString(g, x, y, fontSize, worldsName, new Color(220,220,220));
		}
	}
	
	public void update(int cxPos, int cyPos) {
		int width = Help.getFontWidth(worldsName, fontSize);

		int x1 = x - 20 - Help.getFontWidth("PLAY", fontSize - 6);
		int x2 = x + width + 40;
		int x3 = x2 + 20 + Help.getFontWidth("EDIT", fontSize - 6);
		
		if(cxPos >= x1 * Panel.GAME_SCALE_WIDTH && cxPos <= (x1 + Help.getFontWidth("PLAY", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			//initalise world with data of folder
			world.initWorld(worldsName);
			GameStates.currentGameState = GameStates.GAME;
		}
		if(cxPos >= x2 * Panel.GAME_SCALE_WIDTH && cxPos <= (x2 + Help.getFontWidth("EDIT", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			changing = true;
			textField.clearTextField();
		}
		if(cxPos >= x3 * Panel.GAME_SCALE_WIDTH && cxPos <= (x3 + Help.getFontWidth("DELETE", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			delete = true;
		}
		
		if(changing) {
			changing(cxPos, cyPos);
		}
	}
	
	private void hover(Graphics g, int xPos, int yPos, int x1, int x2, int x3) {
		if(xPos >= x1 * Panel.GAME_SCALE_WIDTH && xPos <= (x1 + Help.getFontWidth("PLAY", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, x1, y - 1, fontSize - 6, "PLAY", new Color(255,255,255));
		}
		if(xPos >= x2 * Panel.GAME_SCALE_WIDTH && xPos <= (x2 + Help.getFontWidth("EDIT", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, x2, y - 1, fontSize - 6, "EDIT", new Color(255,255,255));
		}
		if(xPos >= x3 * Panel.GAME_SCALE_WIDTH && xPos <= (x3 + Help.getFontWidth("DELETE", fontSize - 6)) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - fontSize - 6) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, x3, y - 1, fontSize - 6, "DELETE", new Color(255,255,255));
		}
	}
	
	public void changing(Graphics g, int xPos, int yPos, int cxPos, int cyPos) {
		int sx = 280, sy = textField.y + textField.height + (int) fontSize + 4, swidth = Help.getFontWidth("LOAD", fontSize), sheight = (int) fontSize;
		int bx = 280, by = textField.y + textField.height + (int) fontSize + (8 + sheight), bwidth = Help.getFontWidth("BACK", fontSize), bheight = (int) fontSize;
		
		g.setColor(new Color(0, 0, 0, 175));
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		textField.draw(g, cxPos, cyPos);	
		if(xPos >= sx * Panel.GAME_SCALE_WIDTH && xPos <= (sx + swidth) * Panel.GAME_SCALE_WIDTH && yPos <= sy * Panel.GAME_SCALE_HEIGHT && yPos >= (sy - sheight) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, sx, sy, fontSize, "SAVE", new Color(255, 255, 255));
		}else {
			Help.drawString(g, sx, sy, fontSize, "SAVE", new Color(220,220,220));
		}
		
		if(xPos >= bx * Panel.GAME_SCALE_WIDTH && xPos <= (bx + bwidth) * Panel.GAME_SCALE_WIDTH && yPos <= by * Panel.GAME_SCALE_HEIGHT && yPos >= (by - bheight) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, bx, by, fontSize, "BACK", new Color(255, 255, 255));
		}else {
			Help.drawString(g, bx, by, fontSize, "BACK", new Color(220,220,220));
		}
	}
	
	private void changing(int cxPos, int cyPos) {
		int sx = 280, sy = textField.y + textField.height + (int) fontSize + 4, swidth = Help.getFontWidth("LOAD", fontSize), sheight = (int) fontSize;
		int bx = 280, by = textField.y + textField.height + (int) fontSize + (8 + sheight), bwidth = Help.getFontWidth("BACK", fontSize), bheight = (int) fontSize;
		
		textField.update(cxPos, cyPos);
		if(cxPos >= sx * Panel.GAME_SCALE_WIDTH && cxPos <= (sx + swidth) * Panel.GAME_SCALE_WIDTH && cyPos <= sy * Panel.GAME_SCALE_HEIGHT && cyPos >= (sy - sheight) * Panel.GAME_SCALE_HEIGHT) {
			if(!GameSelection.ifWorldExists(textField.getInput())) {
				change = true;
				changing = false;
				GameSelection.changing = false;
			}
		}	
		if(cxPos >= bx * Panel.GAME_SCALE_WIDTH && cxPos <= (bx + bwidth) * Panel.GAME_SCALE_WIDTH && cyPos <= by * Panel.GAME_SCALE_HEIGHT && cyPos >= (by - bheight) * Panel.GAME_SCALE_HEIGHT) {
			changing = false;
			GameSelection.changing = false;
		}
	}
	
	public boolean delete() {
		return delete;
	}
	
	public boolean change() {
		return change;
	}
	
	public void setChange(boolean change) {
		this.change = change;
	}
	
	public void setWorldName() {
		worldsName = textField.getInput();
	}
	
	public String getWorldName() {
		return textField.getInput();
	}
	
	public boolean getChanging() {
		return changing;
	}
}
