package lighting;

import java.awt.Graphics;

public abstract class LightSource {
	
	public abstract void makeLightSource();
	
	public abstract void draw(Graphics g);
	
	public abstract void checkType();
}
