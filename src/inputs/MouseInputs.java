package inputs;

import java.awt.Cursor;
//import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import help.Image;
import options.keybinds.OKeyBinds;



public class MouseInputs implements MouseMotionListener, MouseListener {
		
	public static int xPos, yPos;	
	public static int cxPos, cyPos;
	public static int dxPos, dyPos;
	public static int pxPos, pyPos;
	public static int mouseButton;

	public static boolean dragged, pressed, clicked, stopListening, moved;
			
//	private BufferedImage bimg = Image.getImage(Image.CURSOR); 
//	private Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(bimg, new Point(16, 16), "cursor");
	
	@Override
	public void mouseDragged(MouseEvent e) {
		dxPos = e.getX();
		dyPos = e.getY();
		
		dragged = true;
		pressed = false;
		moved = false;
		clicked = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
			xPos = e.getX();
			yPos = e.getY();
//			e.getComponent().setCursor(cursor);
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			moved = true;
			dragged = false;
			pressed = false;
			clicked = false;
	}	

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
		
		mouseButton = e.getButton();
		
		if(!stopListening) {
			if(e.getButton() == 1) {
				cxPos = e.getX();
				cyPos = e.getY();
			}
		}		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pxPos = e.getX();
		pyPos = e.getY();
		
		pressed = true;
		dragged = false;
		moved = false;
		clicked = false;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
