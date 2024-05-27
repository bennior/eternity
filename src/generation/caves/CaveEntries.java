package generation.caves;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import generation.TileRGB;

public class CaveEntries {

	private int n = 5; //neighbours
	private int h = 1; //hole size
	private int r = 20; //radius
	
	public BufferedImage createCaveEntries(BufferedImage world, BufferedImage cave) {
		
		Graphics2D g = world.createGraphics();
		
		findPossibleHoles(g, world);
		colorAllHoles(g, world, cave);
		colorAllPossibleHoles(g, world);
		
		g.dispose();
		
		return world;
	}
	
	private void colorAllPossibleHoles(Graphics g, BufferedImage world) {
		for(int i = 0; i < world.getWidth(); i++) {
			for(int j = 0; j < world.getHeight(); j++) {
				if(world.getRGB(i, j) == TileRGB.possibleHole) {
					g.setColor(new Color(220,220,220));
					g.fillRect(i, j, 1, 1);
				}
			}
		}
	}
	
	private void colorAllHoles(Graphics g, BufferedImage world, BufferedImage cave) {
		for(int i = n; i < world.getWidth() - n; i++) {
			for(int j = n; j < world.getHeight() - n; j++) {
				if(world.getRGB(i, j) == TileRGB.possibleHole && countHoleNeighbours(world, i, j) == 9 && !checkForOtherHoles(g, world, i, j) && checkForSpaceInCave(cave, i, j) == 9) { //attention fixed size of holes
					for(int a = i - h; a < i + h*2; a++) {
						for(int b = j - h; b < j + h*2; b++) {
							g.setColor(Color.RED);
							g.fillRect(a, b, 1, 1);
						}
					}
				}
			}
		}
	}
	
	private void findPossibleHoles(Graphics g, BufferedImage world) {
		for(int i = n; i < world.getWidth() - n; i++) {
			for(int j = n; j < world.getHeight() - n; j++) {
				if(world.getRGB(i, j) == TileRGB.stone && countStoneNeighbours(world, i, j) == 4*n*n) {
					g.setColor(Color.BLACK);
					g.fillRect(i, j, 1, 1);
				}
			}
		}
	}
	
	//methods for counting/checking on neighbours/other holes
	
	private int checkForSpaceInCave(BufferedImage cave, int x, int y) {
		int count = 0;
		for(int i = x - h; i < x + h*2; i++) {
			for(int j = y - h; j < y + h*2; j++) {
				if(cave.getRGB(i, j) == TileRGB.air) {
					count++;
				}
			}
		}
	return count;
	}
	
	private boolean checkForOtherHoles(Graphics g, BufferedImage world, int x, int y) {
		int count = 0;
		for(int i = x - r; i < x + r; i++) {
			for(int j = y - r; j < y + r; j++) {
				if(i > 0 && j > 0 && i < world.getWidth() && j < world.getHeight()) {
					if(world.getRGB(i, j) == TileRGB.hole) {
						count++;
					}
				}
			}
		}
		if(count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	private int countHoleNeighbours(BufferedImage world, int x, int y) {
		int count = 0;
		for(int i = x - h; i < x + h*2; i++) {
			for(int j = y - h; j < y + h*2; j++) {
				if(world.getRGB(i, j) == TileRGB.possibleHole) {
					count++;
				}
			}
		}
	return count;
	}
	
	private int countStoneNeighbours(BufferedImage world, int x, int y) {
		int count = 0;
			for(int i = x - n; i < x + n; i++) {
				for(int j = y - n; j < y + n; j++) {
					if(world.getRGB(i, j) == TileRGB.stone || world.getRGB(i, j) == TileRGB.possibleHole) {
						count++;
					}
				}
			}
		return count;
	}
}
