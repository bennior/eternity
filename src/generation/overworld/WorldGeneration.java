package generation.overworld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import generation.Noise;

public class WorldGeneration {

	private Noise noise;
	public int worldWidth, worldHeight;
	private BufferedImage world, veg;

	public WorldGeneration(int worldWidth, int worldHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
	}
	
	public BufferedImage createWorld() {
		noise = new Noise(worldWidth, worldHeight, 25);
		
		world = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_ARGB);
		veg = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g1 = world.createGraphics();
		Graphics2D g2 = veg.createGraphics();
		
		for(int j = 0; j < world.getHeight(); j++) {
			for(int i = 0; i < world.getWidth(); i++) {
				
				float noiseValue = noise.getNoiseValue(i, j);
				
				if(noiseValue < 0.4) { //stone
					g1.setPaint(new Color(220,220,220));
					g1.fillRect(i, j, 1, 1);
					
				}else if(noiseValue > 0.4f && noiseValue < 0.6f) { //grass
					g1.setPaint(new Color(0,128,0));
					g1.fillRect(i, j, 1, 1);
					if(noiseValue > 0.46f && noiseValue < 0.54f) { //grass sprite (vegetation)
						g2.setPaint(Color.RED);
						g2.fillRect(i, j, 1, 1);
					}
					if((noiseValue > 0.4f && noiseValue < 0.46f) || (noiseValue > 0.54f && noiseValue < 0.57)) { //grass (so it is not too close to beaches) (vegetation)
						g2.setPaint(Color.CYAN);
						g2.fillRect(i, j, 1, 1);
					}		 
				}else if(noiseValue > 0.6f && noiseValue < 0.65f) { //sand
					g1.setPaint(new Color(240,230,140));
					g1.fillRect(i, j, 1, 1);
					
				}else if(noiseValue > 0.65f) { //water
					g1.setPaint(Color.BLUE);
					g1.fillRect(i, j, 1, 1);
				}
		        
			}
		}
		
		g1.dispose();
		g2.dispose();
		
		return world;
	}	
	
	public BufferedImage getVegetation() {
		return veg;
	}
}
