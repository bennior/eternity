package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputs implements KeyListener {
	
	public static boolean pressed;
	
	public static String key = "";
	public static int keyCode;
	public static char keyChar;
	
	@Override
	public void keyPressed(KeyEvent e) {
		pressed = true;
		
		keyCode = e.getKeyCode();
		keyChar = e.getKeyChar();
		key = KeyEvent.getKeyText(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		
	}

}
