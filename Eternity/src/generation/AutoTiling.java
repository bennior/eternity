package generation;

import java.awt.image.BufferedImage;

import help.Image;

public class AutoTiling {
	
	private static BufferedImage tileset = Image.getImage(Image.TILESET);
	
	private final static int stone = -2302756;
	private final static int grass = -16744448;
	private final static int sand = -989556;
	private final static int water = -16776961;
	
	public static BufferedImage[][] autotile(BufferedImage tilemap) {
		
		BufferedImage[][] images = new BufferedImage[tilemap.getWidth()][tilemap.getHeight()];
		
		for(int x = 0; x < tilemap.getWidth(); x++) {
			for(int y = 0; y < tilemap.getHeight(); y++) {
				
				switch(tilemap.getRGB(x, y)) {
				case stone: images[x][y] = selectTile(tilemap, x, y, grass, 0);
					break;
				case grass: images[x][y] = selectTile(tilemap, x, y, sand, 90);
					break;
				case sand: images[x][y] = selectTile(tilemap, x, y, water, 180);
					break;
				case water: images[x][y] = tileset.getSubimage(330, 90, 30, 30);
					break;
				}
			}
		}
		return images;
	}
	
	private static String checkNeighbours(BufferedImage tilemap, int x, int y, int tile) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = x - 1; i < x + 2; i++) {
			for(int j = y - 1; j < y + 2; j++) {
				
				if(i < 0 || j < 0 || i >= tilemap.getWidth() || j >= tilemap.getHeight()){
	                sb.append(0);  
	            }
				
				else if(tilemap.getRGB(i, j) == tile) {
					sb.append(1);
				}else {
					sb.append(0);
				}
			}
		}
		return sb.toString();
	}
	
	private static BufferedImage selectTile(BufferedImage tilemap, int x, int y, int tile, int tilePos) {
		
		BufferedImage img = switch(checkNeighbours(tilemap, x, y, tile)) {
		case "000000001" -> tileset.getSubimage(0 + tilePos, 0, 30, 30); //first nine options
		case "000001000", "000001001", "001001001", "001001000" -> tileset.getSubimage(30 + tilePos, 0, 30, 30);
		case "001000000" -> tileset.getSubimage(60 + tilePos, 0, 30, 30);
		case "000000010", "000000011", "000000111", "000000110" -> tileset.getSubimage(0 + tilePos, 30, 30, 30);
		case "010000000", "110000000", "011000000", "111000000" -> tileset.getSubimage(60 + tilePos, 30, 30, 30);
		case "000000100" -> tileset.getSubimage(0 + tilePos, 60, 30, 30);
		case "000100000", "000100100", "100100100", "100100000" -> tileset.getSubimage(30 + tilePos, 60, 30, 30);
		case "100000000" -> tileset.getSubimage(60 + tilePos, 60, 30, 30);
		
		case "111100100", "110100000", "111100000", "110100100" -> tileset.getSubimage(0 + tilePos, 90, 30, 30); //second four options ...
		case "111001001", "011001001", "011001000", "111001000" -> tileset.getSubimage(0 + tilePos, 120, 30, 30);
		case "100100111", "000100110", "100100110", "000100111" -> tileset.getSubimage(30 + tilePos, 90, 30, 30);
		case "001001111", "001001011", "000001011", "000001111" -> tileset.getSubimage(30 + tilePos, 120, 30, 30);
		
		case "000000000" -> tileset.getSubimage(60 + tilePos, 90, 30, 30); //single tile		
		
		default -> tileset.getSubimage(60 + tilePos, 90, 30, 30);
		};
		
		return img;
	}
}
