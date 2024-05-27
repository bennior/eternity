//Method 2

package lighting;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.world.Player;
import inputs.MouseInputs;
import main.Panel;
import options.graphics.OGraphics;

public class PlayerSource extends LightSource{
	
	private int radius, luminosity;
	private BufferedImage light;
	private Ditherer ditherer;
	private Color[] colors = {new Color(0, 0, 0, 0), new Color(0, 0, 0, 200)};
	private Player player;

	public PlayerSource(int radius, int luminosity, Player player) {
		
		this.radius = radius;
		this.luminosity = luminosity;
		this.player = player;
		
		ditherer = new Ditherer(colors);
		
		makeLightSource();
		checkType();
	}

	@Override
	public void makeLightSource() {
		
		light = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2D = (Graphics2D) light.getGraphics();
		
		g2D.setColor(new Color(0,0,0, luminosity));
		
		for(int i = 0; i < radius; i++) {
			g2D.fillOval(radius - i, radius - i, i * 2, i * 2);
		}
		
		g2D.dispose();
	}

	@Override
	public void draw(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;
		
		g2D.drawImage(light, (int) (player.x + 22.5 - radius), (int) (player.y + 22.5 - radius), radius * 2, radius * 2, null);
		
		if(LightMap.typeChange) {
			checkType();
			LightMap.typeChange = !LightMap.typeChange;
		}
		
	}
	
	public void checkType() {
		switch(OGraphics.lighting.getOption()) {
		case "SMOOTH" -> makeLightSource(); //temporary
		case "DITHER" -> light = ditherer.dither(light, 2);
		}
	}
	
	
	
}