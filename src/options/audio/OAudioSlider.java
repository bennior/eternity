package options.audio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import help.Help;
import inputs.MouseInputs;
import main.Panel;

public class OAudioSlider {
	
	private int x, y, width, height;
	private float pos, fontSize;
	private String text;
	
	public OAudioSlider(int x, int y, int width, int height, String text, float fontSize) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.fontSize = fontSize;
	}
	
	public void draw(Graphics g, int xPos, int yPos) {
		
		Graphics2D g2D = (Graphics2D) g;
		
		if(xPos >= x * Panel.GAME_SCALE_WIDTH && xPos <= (x + width) * Panel.GAME_SCALE_WIDTH && yPos >= y * Panel.GAME_SCALE_HEIGHT && yPos <= (y + height) * Panel.GAME_SCALE_HEIGHT) {
			int sx = x + (width - Help.getFontWidth(text, fontSize + 1)) / 2;
			int sy = y - Help.getFontHeight(text, fontSize + 1) / 2;
			
			Help.drawString(g, sx, sy, fontSize + 1, text, new Color(255, 255, 255));
			
			g2D.setColor(new Color(169, 169, 169));
			g2D.fillRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) ((width) * Panel.GAME_SCALE_WIDTH), (int) ((height) * Panel.GAME_SCALE_HEIGHT));
			g2D.setColor(new Color(255, 255, 255));
			g2D.fillRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) ((width * pos) * Panel.GAME_SCALE_WIDTH), (int) ((height) * Panel.GAME_SCALE_HEIGHT));
			g2D.setStroke(new BasicStroke(2 * Panel.GAME_SCALE_WIDTH + 1));
			g2D.drawRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) ((width) * Panel.GAME_SCALE_WIDTH), (int) ((height) * Panel.GAME_SCALE_HEIGHT));
		}else {
			int sx = x + (width - Help.getFontWidth(text, fontSize)) / 2;
			int sy = y - Help.getFontHeight(text, fontSize) / 2;
			
			Help.drawString(g, sx, sy, fontSize, text, new Color(169,169,169));
			
			g2D.setColor(new Color(169, 169, 169));
			g2D.fillRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
			g2D.setColor(new Color(255, 255, 255));
			g2D.fillRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) ((width * pos) * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
			g2D.setColor(new Color(110, 110, 110));
			g2D.setStroke(new BasicStroke(2 * Panel.GAME_SCALE_WIDTH));
			g2D.drawRect((int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT), (int) (width * Panel.GAME_SCALE_WIDTH), (int) (height * Panel.GAME_SCALE_HEIGHT));
		}
	}
	
	public void update(int pxPos, int pyPos, int dxPos) {
		if(MouseInputs.draged) {
			drag(pxPos, pyPos, dxPos);
		}
		if(MouseInputs.pressed) {
			press(pxPos, pyPos);
		}
	}
	
	public void setPos(String pos) {
		try {
			if(Float.valueOf(pos) >= 0.f && Float.valueOf(pos) <= 1.f) {
				this.pos = Float.valueOf(pos);
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			this.pos = 0.5f;
		}
	}
	
	public float getPos() {
		return pos;
	}
	
	private void press(int pxPos, int pyPos) {
		
		if(pxPos >= x * Panel.GAME_SCALE_WIDTH && pxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && pyPos >= y * Panel.GAME_SCALE_HEIGHT && pyPos <= (y + height) * Panel.GAME_SCALE_HEIGHT)  {
			pos = (pxPos - x * Panel.GAME_SCALE_WIDTH) / (width * Panel.GAME_SCALE_WIDTH);
		}
	}
	
	private void drag(int pxPos, int pyPos, int dxPos) {
		
		if(pxPos >= x * Panel.GAME_SCALE_WIDTH && pxPos <= (x + width) * Panel.GAME_SCALE_WIDTH && pyPos >= y * Panel.GAME_SCALE_HEIGHT && pyPos <= (y + height) * Panel.GAME_SCALE_HEIGHT)  {
			pos = (dxPos - x * Panel.GAME_SCALE_WIDTH) / (width * Panel.GAME_SCALE_WIDTH);
			
			if(dxPos < x * Panel.GAME_SCALE_WIDTH) {
				pos = 0.0f;
			}else if(dxPos > (x + width) * Panel.GAME_SCALE_WIDTH) {
				pos = 1.0f;
			}
		}
	}
	
}
