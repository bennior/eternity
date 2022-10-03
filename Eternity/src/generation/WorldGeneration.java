package generation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WorldGeneration {

	private Noise noise;
	
	public WorldGeneration() {
		noise = new Noise(1000, 1000, 25);
	}
	
	public BufferedImage createWorld() {
		BufferedImage world = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2D = world.createGraphics();
		
		for(int j = 0; j < world.getHeight(); j++) {
			for(int i = 0; i < world.getWidth(); i++) {
				
				float noiseValue = noise.getNoiseValue(i, j);
				
				if(noiseValue < 0.35) { //stone
					g2D.setPaint(new Color(220,220,220));
					g2D.fillRect(i, j, 1, 1);
					
				}else if(noiseValue > 0.35f && noiseValue < 0.6f) { //grass
					g2D.setPaint(new Color(0,128,0));
					g2D.fillRect(i, j, 1, 1);
					
				}else if(noiseValue > 0.6f && noiseValue < 0.65f) { //sand
					g2D.setPaint(new Color(240,230,140));
					g2D.fillRect(i, j, 1, 1);
					
				}else if(noiseValue > 0.65f) { //water
					g2D.setPaint(Color.BLUE);
					g2D.fillRect(i, j, 1, 1);
				}
		        
			}//for trees, flowers and grass look at every grass tile and make different probability to generate
		}
		g2D.dispose();
		
		return world;
	}
}
