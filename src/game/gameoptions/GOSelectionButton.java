package game.gameoptions;

import static main.Panel.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;

import game.world.World;
import help.Help;
import main.GameStates;
import main.Panel;
import options.GameFiles;


public class GOSelectionButton {
	
	private int y;
	private float fontSize;
	private String text;
	
	public GOSelectionButton(int y, float fontSize, String text) {
		this.y = y;
		this.fontSize = fontSize;
		this.text = text;
	}
	
	public void draw(Graphics g, int xPos, int yPos) {
		
		int x = (GAME_WIDTH - Help.getFontWidth(text, fontSize)) / 2;
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		
		if(xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, y, fontSize + 1, text, new Color(255, 255, 255));
		}else {
			Help.drawString(g, y, fontSize, text, new Color(169,169,169));
		}
	}
	
	public void update(int cxPos, int cyPos, int state) {
		
		int x = (GAME_WIDTH - Help.getFontWidth(text, fontSize)) / 2;
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			GOSelection.GAMEOPTIONSSTATE = state;
			Help.resetClickPos();
		}
	}
	
	public void save(int cxPos, int cyPos, int state) {
		
		int x = (GAME_WIDTH - Help.getFontWidth(text, fontSize)) / 2;
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			GOSelection.GAMEOPTIONSSTATE = state;
			Help.resetClickPos();
			GameFiles.saveOptions();
		}
	}
	
	public void unpause(int cxPos, int cyPos) {
		int x = (GAME_WIDTH - Help.getFontWidth(text, fontSize)) / 2;
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			World.WORLDSTATE = World.UNPAUSED;
			Help.resetClickPos();
		}
	}
	
	public void menu(int cxPos, int cyPos, World world) {
		int x = (GAME_WIDTH - Help.getFontWidth(text, fontSize)) / 2;
		int width = Help.getFontWidth(text, fontSize);
		int height = (int) fontSize;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			GameStates.currentGameState = GameStates.MENU;
			Help.resetClickPos();
			//save world files
			world.saveWorld();
		}
	}
}
