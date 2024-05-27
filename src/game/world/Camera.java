package game.world;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import generation.AutoTiling;
import help.Help;
import help.Image;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.GameLoop;
import main.Panel;
import options.GameOptions;
import options.graphics.OGraphics;
import options.keybinds.OKeyBinds;
import playing.BendingGrass;
import playing.Cursor;
import playing.PlayerEvents;

import static main.Panel.TILES_IN_WIDTH;
import static main.Panel.TILES_IN_HEIGHT;

public class Camera {
	
	public BufferedImage[][] tiles, vegTiles, caveTiles, chunk, veg;
	private BufferedImage vegMap;
	private BendingGrass bending;
	private Cursor cursor;
	
	public int tx, ty, speed = 3, offsetx, offsety;
	public static boolean rearrange = false;
	
	public static int CURRENTWORLD;
	public static final int OVERWORLD = 0;
	public static final int CAVE = 1;
	
	public Camera(BufferedImage[][] tiles, BufferedImage[][] vegTiles, BufferedImage[][] caveTiles, BufferedImage vegMap, int tx, int ty, int offsetx, int offsety, Cursor cursor) {
		this.tiles = tiles;
		this.vegTiles = vegTiles;
		this.caveTiles = caveTiles;
		this.vegMap = vegMap;
		this.tx = tx;
		this.ty = ty;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.cursor = cursor;
		bending = new BendingGrass(vegMap);
		chunk = new BufferedImage[TILES_IN_WIDTH + 2][TILES_IN_HEIGHT + 2];
		veg = new BufferedImage[TILES_IN_WIDTH + 2][TILES_IN_HEIGHT + 2];
	}
		
	public void draw(Graphics g) {	
		
		int mx = (int) ((MouseInputs.xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
		int my = (int) ((MouseInputs.yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		Graphics2D g2D = (Graphics2D) g;
		
		for(int i = 0; i < chunk.length; i++) {
			for(int j = 0; j < chunk[i].length; j++) {
				int x = 30 * (i - 1);
				int y = 30 * (j - 1);
				
				if(CURRENTWORLD == OVERWORLD) {
						//draw tiles
						g2D.drawImage(chunk[i][j], x + offsetx, y + offsety, 30, 30, null);		
						//draw vegetation
						if(cursor.getCursorX(offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos) + 1 == i && cursor.getCursorY(offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos) + 1 == j) {
							
							g2D.setComposite(AlphaComposite.SrcOver.derive(0.3f));

							if(vegMap.getRGB(tx + i, ty + j) == -256) {
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety - 9, null); 
							}else if(vegMap.getRGB(tx + i, ty + j) == -65281){
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety - 14, null);
							}else {
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety, null);
							}

							g2D.setComposite(AlphaComposite.SrcOver.derive(1f));
						}else {
							if(vegMap.getRGB(tx + i, ty + j) == -256) {
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety - 9, null); 
							}else if(vegMap.getRGB(tx + i, ty + j) == -65281){
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety - 14, null);
							}else {
								g2D.drawImage(veg[i][j], x + offsetx, y + offsety, null);
							}
						}
							
//						g.drawRect(x + offsetx, y + offsety, 30, 30);
				}else if(CURRENTWORLD == CAVE) {
						g2D.drawImage(chunk[i][j], x + offsetx, y + offsety, 30, 30, null);	
//						g.drawRect(x + offsetx, y + offsety, 30, 30);
				}
				
				//only when rescaled
				if(offsetx > 30) {
					offsetx -= 30;
				}
				if(offsety > 30) {
					offsety -= 30;
				}
			}
		}	
	}
	
	public void update(int playerX, int playerY, int defaultX, int defaultY, BufferedImage world, BufferedImage cave) {			
		if(KeyInputs.key.equals(OKeyBinds.up.getKey()) && playerY == defaultY) { //UP
			if(offsety + speed <= 30) {                            //not sure
				offsety += speed;
			}else {
				if(ty - 1 >= 0) {
					ty--;
					offsety -= 30;
					offsety += speed;
				}
			}
		}
		
		if(KeyInputs.key.equals(OKeyBinds.down.getKey()) && playerY == defaultY) { //DOWN
			if(offsety - speed >= -30) {
				offsety -= speed;
			}else {
				if(ty + chunk[0].length < tiles[0].length) {
					ty++;
					offsety += 30;
					offsety -= speed;
				}
			}
		}
		if(KeyInputs.key.equals(OKeyBinds.left.getKey()) && playerX == defaultX) { //LEFT
			if(offsetx + speed <= 30) {
				offsetx += speed;
			}else {
				if(tx - 1 >= 0) {
					tx--;
					offsetx -= 30;
					offsetx += speed;
				}
			}
		}
		if(KeyInputs.key.equals(OKeyBinds.right.getKey()) && playerX == defaultX) { //RIGHT
			if(offsetx - speed >= -30) {
				offsetx -= speed;
			}else {
				if(tx + chunk.length < tiles.length) {
					tx++;
					offsetx += 30;
					offsetx -= speed;
				}
			}
		}

		if(!KeyInputs.UP && !KeyInputs.LEFT && !KeyInputs.RIGHT && !KeyInputs.DOWN && !KeyInputs.BREAK) {
			Help.resetKey();
		}	
				
		if(CURRENTWORLD == OVERWORLD) {
			rearrangeWorld(world);
			chunk(tiles);
			veg();
		}else if(CURRENTWORLD == CAVE) {
			rearrangeCave(cave);
			chunk(caveTiles);
		}
		
		bending.update(tx, ty, offsetx, offsety, vegTiles);
	}
	
	public int getPlayerTilePosition(BufferedImage map) {
		int x = ((int) (Player.playerx + 22.5) - offsetx) / 30 + 1;
		int y = ((int) (Player.playery + 22.5) - offsety) / 30 + 1;
		
		return map.getRGB(x + tx, y + ty);
	}
	
	private void rearrangeWorld(BufferedImage map) {
				
		int x = (int) ((MouseInputs.xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
		int y = (int) ((MouseInputs.yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		
		if(MouseInputs.dragged) {		
			x = (int) ((MouseInputs.dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
			y = (int) ((MouseInputs.dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		}
		
		if(rearrange) {
			vegTiles[tx + x][ty + y] = null;

			BufferedImage subImage = map.getSubimage(tx + x - 2, ty + y - 2, 5, 5);
			
			for(int i = 0; i < AutoTiling.autotile(subImage).length; i++) {
				for(int j = 0; j < AutoTiling.autotile(subImage)[0].length; j++) {
					if(i > 0 && j > 0 && i < AutoTiling.autotile(subImage).length - 1 && j < AutoTiling.autotile(subImage)[0].length - 1) {
						tiles[tx + x - 2 + i][ty + y - 2 + j] = AutoTiling.autotile(subImage)[i][j];
					}
				}
			}
			
			rearrange = false;
			}
		}
		
	private void rearrangeCave(BufferedImage map) {
		
		int x = (int) ((MouseInputs.xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
		int y = (int) ((MouseInputs.yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		
		if(MouseInputs.dragged) {		
			x = (int) ((MouseInputs.dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30 + 1);
			y = (int) ((MouseInputs.dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30 + 1);
		}
		
		if(rearrange) {
			BufferedImage subImage = map.getSubimage(tx + x - 2, ty + y - 2, 5, 5);
			
			for(int i = 0; i < AutoTiling.autotileCave(subImage).length; i++) {
				for(int j = 0; j < AutoTiling.autotileCave(subImage)[0].length; j++) {
					if(i > 0 && j > 0 && i < AutoTiling.autotileCave(subImage).length - 1 && j < AutoTiling.autotileCave(subImage)[0].length - 1) {
						caveTiles[tx + x - 2 + i][ty + y - 2 + j] = AutoTiling.autotileCave(subImage)[i][j];
					}
				}
			}
			
			rearrange = false;
			}
		}
	
	private void chunk(BufferedImage[][] tiles) {
		for(int i = 0; i < chunk.length; i++) {
			for(int j = 0; j < chunk[0].length; j++) {
				chunk[i][j] = tiles[tx + i][ty + j]; //16 - 17 //9 - 10
			}
		}
	}
	
	private void veg() {
		for(int i = 0; i < veg.length; i++) {
			for(int j = 0; j < veg[0].length; j++) {
				veg[i][j] = vegTiles[tx + i][ty + j];
			}
		}
	}
}
