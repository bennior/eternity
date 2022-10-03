package game.gameselection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import help.Help;
import inputs.KeyInputs;
import main.Panel;

public class TextField {
	
	public int x, y, width, height, tick = 0;
	private float fontSize;
	private String input = "", title;
	
	public TextField(int x, int y, int width, int height, float fontSize, String title) {
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		this.fontSize = fontSize;
		this.title = title;
	}
	
	public void draw(Graphics g, int cxPos, int cyPos) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(new Color(60,60,60));
		g2D.fillRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
		Help.drawString(g2D, x, y - 4, fontSize - 2, title, new Color(255,255,255));
		
		if(GameSelection.ifWorldExists(input)) {
			Help.drawString(g2D, x + 6, y + height - 8, fontSize, input, new Color(220,20,60));
		}else {
			Help.drawString(g2D, x + 6, y + height - 8, fontSize, input, new Color(255,255,255));
		}
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos >= y * Panel.GAME_SCALE_HEIGHT && cyPos <= (y + height) * Panel.GAME_SCALE_HEIGHT) {
			g2D.setColor(new Color(230,230,230));
			g2D.setStroke(new BasicStroke(3 * Panel.GAME_SCALE_WIDTH));
			g2D.drawRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
		}else {
			g2D.setColor(new Color(180,180,180));
			g2D.setStroke(new BasicStroke(3 * Panel.GAME_SCALE_WIDTH));
			g2D.drawRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
		}
	}
	
	public void update(int cxPos, int cyPos) {
		 
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos >= y * Panel.GAME_SCALE_HEIGHT && cyPos <= (y + height) * Panel.GAME_SCALE_HEIGHT) {
			addChar();
		}else {
			Help.resetClickPos();
			tick = 0;
		}	
	}
	
	private void addChar() {
		if(tick < 1) {
			KeyInputs.pressed = false;
			tick++;
		}
		if(KeyInputs.pressed) {
			if(KeyInputs.keyCode == KeyEvent.VK_BACK_SPACE) {
				if (input != null && input.length() > 0) {
			        input = input.substring(0, input.length() - 1);
			    }
			}else if(KeyInputs.keyChar != KeyEvent.CHAR_UNDEFINED && input.length() <= 15) {
				input += Character.toUpperCase(KeyInputs.keyChar);
			}
			tick = 0;
		}
	}
	
	public void clearTextField() {
		input = "";
	}
	
	public String getInput() {
		return input;
	}
	
}
