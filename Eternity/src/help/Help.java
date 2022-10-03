package help;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import inputs.KeyInputs;
import inputs.MouseInputs;
import main.Panel;

import static main.Panel.*;

public class Help {
	
	private static Font f = initFont();
	
	public static Font initFont(float size) {
		Font font = f.deriveFont(size);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(font);
		return font;
	}
	
	public static int getFontWidth(String text, float size) {
		AffineTransform af = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(af, true, true);     
		Font font = initFont(size);
		
		return (int)(font.getStringBounds(text, frc).getWidth());
	}
	
	public static int getFontHeight(String text, float size) {
		AffineTransform af = new AffineTransform();     
		FontRenderContext frc = new FontRenderContext(af, true, true);     
		Font font = initFont(size);
		
		return (int)(font.getStringBounds(text, frc).getHeight());
	}
	
	public static void drawString(Graphics g, int y, float size, String text, Color color) {
		
		Font font = initFont(size * Panel.GAME_SCALE_HEIGHT);
		int x = (GAME_WIDTH - getFontWidth(text, size)) / 2;
				
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, (int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT));
		
	}
	
	public static void drawString(Graphics g, int x, int y, float size, String text, Color color) {
		
		Font font = initFont(size * Panel.GAME_SCALE_HEIGHT);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, (int) (x * Panel.GAME_SCALE_WIDTH), (int) (y * Panel.GAME_SCALE_HEIGHT));
	}
	
	public static void resetClickPos() {
		MouseInputs.cxPos = 0;
		MouseInputs.cyPos = 0;
	}
	
	public static void resetPos() {
		MouseInputs.xPos = 0;
		MouseInputs.yPos = 0;
	}
	
	public static void resetKey() {
		KeyInputs.key = "";
	}
	
	private static Font initFont() {
		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, Help.class.getResourceAsStream("/fonts/prstartk.ttf"));
			return font;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void messageBox(Graphics g, String text1, String text2) {
		
		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(new Color(255, 255, 255));
		g2D.fillRect((int) (Panel.GAME_SCALE_WIDTH * 303), (int) (Panel.GAME_SCALE_HEIGHT * 192), (int) (354 * Panel.GAME_SCALE_WIDTH), (int) (121 * Panel.GAME_SCALE_HEIGHT));
		g2D.setStroke(new BasicStroke(4 * Panel.GAME_SCALE_WIDTH));
		g2D.drawLine((int) (Panel.GAME_SCALE_WIDTH * 305), (int) (Panel.GAME_SCALE_HEIGHT * 190), (int) (Panel.GAME_SCALE_WIDTH * 655), (int) (Panel.GAME_SCALE_HEIGHT * 190));
		g2D.drawLine((int) (Panel.GAME_SCALE_WIDTH * 305), (int) (Panel.GAME_SCALE_HEIGHT * 315), (int) (Panel.GAME_SCALE_WIDTH * 655), (int) (Panel.GAME_SCALE_HEIGHT * 315));
		g2D.drawLine((int) (Panel.GAME_SCALE_WIDTH * 301), (int) (Panel.GAME_SCALE_HEIGHT * 194), (int) (Panel.GAME_SCALE_WIDTH * 301), (int) (Panel.GAME_SCALE_HEIGHT * 311));
		g2D.drawLine((int) (Panel.GAME_SCALE_WIDTH * 659), (int) (Panel.GAME_SCALE_HEIGHT * 194), (int) (Panel.GAME_SCALE_WIDTH * 659), (int) (Panel.GAME_SCALE_HEIGHT * 311));
		g2D.setColor(new Color(0, 0, 150));
		g2D.fillRect((int) (Panel.GAME_SCALE_WIDTH * 307), (int) (Panel.GAME_SCALE_HEIGHT * 192), (int) (346 * Panel.GAME_SCALE_WIDTH), (int) (121 * Panel.GAME_SCALE_HEIGHT));
		g2D.fillRect((int) (Panel.GAME_SCALE_WIDTH * 303), (int) (Panel.GAME_SCALE_HEIGHT * 196), (int) (354 * Panel.GAME_SCALE_WIDTH), (int) (113 * Panel.GAME_SCALE_HEIGHT));
		Help.drawString(g2D, 345, 238, 30f, text1, new Color(204, 204, 0));
		Help.drawString(g2D, 315, 287, 30f, text2, new Color(204, 204, 0));
	}
	
	public static Dimension resolutionToDimension(String res) {
		Dimension dimension = new Dimension(960, 540);
		
		switch(res.length()) {
		case 7: dimension.setSize(Integer.valueOf(res.substring(0, 3)), Integer.valueOf(res.substring(4, 7)));
			break;
		case 8: dimension.setSize(Integer.valueOf(res.substring(0, 4)), Integer.valueOf(res.substring(5, 8)));
			break;
		case 9: dimension.setSize(Integer.valueOf(res.substring(0, 4)), Integer.valueOf(res.substring(5, 9)));
			break;
		}
		return dimension;
	}
}
