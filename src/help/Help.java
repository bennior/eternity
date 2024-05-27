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
		
		Font font = initFont(size);
		int x = (GAME_WIDTH - getFontWidth(text, size)) / 2;
				
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
		
	}
	
	public static void drawString(Graphics g, int x, int y, float size, String text, Color color) {
		
		Font font = initFont(size);
		
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
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
	
	public static void resetKeyCode() {
		KeyInputs.keyCode = 0;
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
		g2D.fillRect(303, 192, 354, 121);
		g2D.setStroke(new BasicStroke(4));
		g2D.drawLine(305, 190, 655, 190);
		g2D.drawLine(305, 315, 655, 315);
		g2D.drawLine(301, 194, 301, 311);
		g2D.drawLine(659, 194, 659, 311);
		g2D.setColor(new Color(0, 0, 150));
		g2D.fillRect(307, 192, 346, 121);
		g2D.fillRect(303, 196, 354, 113);
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
	
	public static int StringToMouseButton(String input) {
		int button;
		
		try {
			button = switch(input) {
			case "LEFT BUTTON" -> 1;
			case "MIDDLE BUTTON" -> 2;
			case "RIGHT BUTTON" -> 3;
			default -> Integer.parseInt(input.substring(7));
			};
		}
		catch(StringIndexOutOfBoundsException | NumberFormatException e) {
			button = 0;
		}
		return button;
	}

}
