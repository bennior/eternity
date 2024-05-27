//Method 2

package lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import game.world.Player;
import main.Panel;

import static main.Panel.GAME_HEIGHT;
import static main.Panel.GAME_WIDTH;

public class LightMap {
	
	private BufferedImage lightmap;
	private PlayerSource playerSource;
	private static ArrayList<LightSource> lights;
	public static boolean typeChange = false;
	
	public LightMap(Player player) {
		playerSource = new PlayerSource(125, 5, player);
		lights = new ArrayList<>();
		lights.add(playerSource);
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;		
		g2D.drawImage(lightmap, 0, 0, null);
	}

	public void update(int alpha) {
		
		lightmap = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2D = (Graphics2D) lightmap.getGraphics();
		
		g2D.setColor(new Color(0, 0, 0, alpha));
		g2D.fillRect(0, 0, lightmap.getWidth(), lightmap.getHeight());
		g2D.setComposite(AlphaComposite.DstOut);
		
		for(LightSource source : lights) {
			source.draw(g2D);
		}
		
		g2D.dispose();
		
	}
	
	
//	public static void addTorch(int x, int y, int radius, int luminosity) {
////		lights.add(new Torch(x - radius, y - radius, radius, luminosity));
//	}
}
