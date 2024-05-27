package playing;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.world.Player;
import help.Image;
import inputs.KeyInputs;
import main.GameLoop;
import options.graphics.OGraphics;

public class BendingGrass {
	
	private int[][] allTiles;
	private ArrayList<Rectangle2D.Float> timers;
	private int resetGrassTimeInSeconds = 2;
	private BufferedImage tileset = Image.getImage(Image.VEGETATION_SPRITES);
	private BufferedImage vegMap;
	
	public BendingGrass(BufferedImage vegMap) {
		this.vegMap = vegMap;
		allTiles = new int[1000][1000];
		timers = new ArrayList<Rectangle2D.Float>();
	}
	
	public void update(int tx, int ty, int offsetx, int offsety, BufferedImage[][] vegTiles) {
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		
		
		if(OGraphics.grass.getOption().equals("BENDING")) {
			bendingGrass(topY, bottomY, leftX, rightX, tx, ty, vegTiles);
			timer(vegTiles);
		}else if(OGraphics.grass.getOption().equals("IDLE")) {
			for(int i = 0; i < timers.size(); i++) {
				resetGrassTile(i, vegTiles);
			}
		}
	}

	private void bendingGrass(int topY, int botY, int leftX, int rightX, int tx, int ty, BufferedImage[][] vegTiles) {
		//HORIZONTAL
		//BOTH or ONE(TOP or BOTTOM) direction (left or right)
			if(KeyInputs.RIGHT) {
				checkIfGrassSprite(tx + leftX + 1, ty + topY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
				checkIfGrassSprite(tx + leftX + 1, ty + botY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
			}else if(KeyInputs.LEFT) {
				checkIfGrassSprite(tx + rightX + 1, ty + topY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
				checkIfGrassSprite(tx + rightX + 1, ty + botY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
			}
		
		//VERTICAL
		//BOTH or ONE(LEFT or RIGHT) direction (down or up)
		if(leftX != rightX) {
			if(KeyInputs.DOWN) {
				checkIfGrassSprite(tx + rightX + 1, ty + topY + 1, tileset.getSubimage(30, 30, 30, 30), vegTiles);
				checkIfGrassSprite(tx + leftX + 1, ty + topY + 1, tileset.getSubimage(0, 30, 30, 30), vegTiles);
			}else if(KeyInputs.UP) {
				checkIfGrassSprite(tx + rightX + 1, ty + botY + 1, tileset.getSubimage(30, 30, 30, 30), vegTiles);
				checkIfGrassSprite(tx + leftX + 1, ty + botY + 1, tileset.getSubimage(0, 30, 30, 30), vegTiles);
			}
		}else {
			if(KeyInputs.DOWN) {
				checkIfGrassSprite(tx + rightX + 1, ty + topY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
				checkIfGrassSprite(tx + leftX + 1, ty + topY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
			}else if(KeyInputs.UP) {
				checkIfGrassSprite(tx + rightX + 1, ty + botY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
				checkIfGrassSprite(tx + leftX + 1, ty + botY + 1, tileset.getSubimage(30, 0, 30, 30), vegTiles);
			}
		}
	}
	
	private void checkIfGrassSprite(int x, int y, BufferedImage desiredSprite, BufferedImage[][] vegTiles) {
		
		if(vegMap.getRGB(x, y) == -65536 && allTiles[x][y] == 0) {
			vegTiles[x][y] = desiredSprite;
			timers.add(new Rectangle2D.Float(x, y, 0, 0)); //x and y of rectangle stands for position in array
			allTiles[x][y] = 1;
		}
	}
	
	private void timer(BufferedImage[][] vegTiles) {
		for(int i = 0; i < timers.size(); i++) {
			allTiles[(int) timers.get(i).x][(int) timers.get(i).y] += 1;
			
			if(allTiles[(int) timers.get(i).x][(int) timers.get(i).y] > GameLoop.UPS_SET * resetGrassTimeInSeconds) {
				resetGrassTile(i, vegTiles);
			}
		}
	}
	
	private void resetGrassTile(int i, BufferedImage[][] vegTiles) {
		allTiles[(int) timers.get(i).x][(int) timers.get(i).y] = 0;
		
		if(vegMap.getRGB((int) timers.get(i).x, (int) timers.get(i).y) == -65536) {
			vegTiles[(int) timers.get(i).x][(int) timers.get(i).y] = tileset.getSubimage(0, 0, 30, 30);	
		}		
		
		timers.remove(timers.get(i));
		timers.trimToSize();
	}
}
