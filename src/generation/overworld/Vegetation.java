package generation.overworld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import generation.TileRGB;
import help.Image;

public class Vegetation {
	
	private static BufferedImage tileset = Image.getImage(Image.VEGETATION_SPRITES);
	
//	private final static int grass = -65536; //grass tile rgb
//	private final static int stone1 = -16776961;
//	private final static int stone2 = -16711936;
//	private final static int bush1 = -16777216;
//	private final static int bush2 = -1;
//	private final static int brown = -14336;
//	private final static int orange = -20561;
//	private final static int tree = -65281;
//	private final static int pine = -256;

	public BufferedImage createVegetation(BufferedImage vegetation) {
		
		Graphics2D g2D = vegetation.createGraphics();
		
		for(int i = 0; i < vegetation.getWidth(); i++) {
			for(int j = 0; j < vegetation.getHeight(); j++) {
				if(vegetation.getRGB(i, j) == Color.CYAN.getRGB() || vegetation.getRGB(i, j) == Color.RED.getRGB()) {
					double r = Math.random();
					
					if(r >= 0 && r < 0.02) {
						//stone 1
						g2D.setPaint(Color.BLUE);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.02 && r < 0.04) {
						//stone 2
						g2D.setPaint(Color.GREEN);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.04 && r < 0.06) {
						//bush 1
						g2D.setPaint(Color.BLACK);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.06 && r < 0.08) {
						//bush 2
						g2D.setPaint(Color.WHITE);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.08 && r < 0.10) {
						//brown mushroom
						g2D.setPaint(Color.ORANGE);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.10 && r < 0.12) {
						//orange mushroom
						g2D.setPaint(Color.PINK);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.12 && r < 0.18) {
						//tree
						g2D.setPaint(Color.MAGENTA);
						g2D.fillRect(i, j, 1, 1);
					}else if(r >= 0.18 && r < 0.24) {
						//pine
						g2D.setPaint(Color.YELLOW);
						g2D.fillRect(i, j, 1, 1);
					}
				}
			}
		}
		g2D.dispose();
		
		return vegetation;
	}
	
	public static BufferedImage[][] vegTiles(BufferedImage vegmap) {
		
		BufferedImage[][] images = new BufferedImage[vegmap.getWidth()][vegmap.getHeight()];
		
		for(int x = 0; x < vegmap.getWidth(); x++) {
			for(int y = 0; y < vegmap.getHeight(); y++) {
				
				switch(vegmap.getRGB(x, y)) {
				case TileRGB.grass_sprite: images[x][y] = tileset.getSubimage(0, 0, 30, 30);
					break;
				case TileRGB.stone1: images[x][y] = tileset.getSubimage(90, 30, 30, 30);
					break;
				case TileRGB.stone2: images[x][y] = tileset.getSubimage(90, 0, 30, 30);
					break;
				case TileRGB.bush1: images[x][y] = tileset.getSubimage(60, 30, 30, 30);
					break;
				case TileRGB.bush2: images[x][y] = tileset.getSubimage(60, 0, 30, 30);
					break;
				case TileRGB.brown: images[x][y] = tileset.getSubimage(120, 0, 30, 30);
					break;
				case TileRGB.orange: images[x][y] = tileset.getSubimage(120, 30, 30, 30);
					break;
				case TileRGB.tree: images[x][y] = tileset.getSubimage(180, 16, 30, 44);
					break;
				case TileRGB.pine: images[x][y] = tileset.getSubimage(150, 21, 30, 39);
					break;
				}
			}
		}
		return images;
	}
}
