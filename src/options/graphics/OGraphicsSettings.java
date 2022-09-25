package options.graphics;

import static main.Panel.GAME_WIDTH;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import help.Help;
import main.Panel;

public class OGraphicsSettings {

	private int y, middle, i = 0;
	private float fontSize;
	private String title;
	private LinkedList<String> texts;
	
	public OGraphicsSettings(int y, float fontSize, String title) {
		this.y = y;
		this.fontSize = fontSize;
		this.title = title;
		texts = new LinkedList<String>();
		
		middle = (GAME_WIDTH - Help.getFontWidth(" - ", fontSize)) / 2;
	}
	
	public void draw(Graphics g, int xPos, int yPos) {
		
		int x = middle - Help.getFontWidth(title, fontSize);
		int x1 = middle - Help.getFontWidth(title, fontSize + 1);
		int width = Help.getFontWidth(title + " - " + texts.get(i), fontSize);
		int height = (int) fontSize;
		
		if(xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos <= y * Panel.GAME_SCALE_HEIGHT && yPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			Help.drawString(g, x1, y, fontSize + 1, title + " - " + texts.get(i), new Color(255, 255, 255));
		}else {
			Help.drawString(g, x, y, fontSize, title + " - " + texts.get(i), new Color(169,169,169));
		}
	}
	
	public void update(int cxPos, int cyPos) {
				
		int x = middle - Help.getFontWidth(title, fontSize);
		int width = Help.getFontWidth(title + " - " + texts.get(i), fontSize);
		int height = (int) fontSize;
		
		if(cxPos >= x * Panel.GAME_SCALE_WIDTH && cxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && cyPos <= y * Panel.GAME_SCALE_HEIGHT && cyPos >= (y - height) * Panel.GAME_SCALE_HEIGHT) {
			Help.resetClickPos();
			i++;
			
			if(i >= texts.size()) {
				i = 0;
			}
		}
	}
	
	public void setOption(String option) {
		for(int i = 0; i < texts.size(); i++) {
			if(option.equals(texts.get(i))) {
				this.i = i;
			}
		}
	}
	
	public String getOption() {
		return texts.get(i);
	}
	
	public void addComponent(String text) {
		texts.add(text);
	}
	
}
 