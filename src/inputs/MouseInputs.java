package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import options.keybinds.OKeyBinds;



public class MouseInputs implements MouseMotionListener, MouseListener {
		
	public static int xPos, yPos;	
	public static int cxPos, cyPos;
	public static int dxPos, dyPos;
	public static int pxPos, pyPos;
	public static int mouseButton;

	public static boolean draged, pressed, clicked, stopListening;
			
//	private BufferedImage bimg = Image.getImage(Image.CURSOR); 
//	private Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(bimg, new Point(0, 0), "cursor");
	
	@Override
	public void mouseDragged(MouseEvent e) {
		dxPos = e.getX();
		dyPos = e.getY();
		
		draged = true;
		pressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
			xPos = e.getX();
			yPos = e.getY();
//		e.getComponent().setCursor(cursor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
//		if(OKeyBinds.errorForDuplicates) {
//			clickSomewhereElse = true;
//		}else {
//			clickSomewhereElse = false;
//		}
		
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
		draged = false;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
}
