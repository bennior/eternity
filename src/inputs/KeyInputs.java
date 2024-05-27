package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import options.keybinds.OKeyBinds;

public class KeyInputs implements KeyListener {
	
	//only for options
	public static boolean pressed;
	public static String key = "";
	public static int keyCode;
	public static char keyChar;
	//game mechanics
	public static boolean UP, LEFT, RIGHT, DOWN, BREAK;
	
	@Override
	public void keyPressed(KeyEvent e) {
		pressed = true;
		
		keyCode = e.getKeyCode();
		keyChar = e.getKeyChar();
		key = KeyEvent.getKeyText(e.getKeyCode()).toUpperCase();
				
		enable(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed = false;
		
		disable(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		
	}
	
	private void enable(KeyEvent e) {
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.up.getKey())) {
			UP = true;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.left.getKey())) {
			LEFT = true;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.right.getKey())) {
			RIGHT = true;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.down.getKey())) {
			DOWN = true;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.destroy.getKey())) {
			BREAK = true;
		}
	}
	
	private void disable(KeyEvent e) {
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.up.getKey())) {
			UP = false;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.left.getKey())) {
			LEFT = false;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.right.getKey())) {
			RIGHT = false;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.down.getKey())) {
			DOWN = false;
		}
		if(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase().equals(OKeyBinds.destroy.getKey())) {
			BREAK = false;
		}
	}
}
