package game.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import help.Help;
import inputs.KeyInputs;
import main.Panel;
import options.keybinds.OKeyBinds;

import static main.Panel.TILES_IN_WIDTH;
import static main.Panel.TILES_IN_HEIGHT;

public class Camera {
	
	private BufferedImage[][] tiles, chunk;
	int tx, ty;
	int offsetx = 0, offsety = 0;
	int speed = 4;
	
	public Camera(BufferedImage[][] tiles, int tx, int ty) {
		this.tiles = tiles;
		this.tx = tx;
		this.ty = ty;
		chunk = new BufferedImage[TILES_IN_WIDTH + 2][TILES_IN_HEIGHT + 2];
		chunk();
	}

	public void draw(Graphics g) {
		for(int i = 0; i < chunk.length; i++) {
			for(int j = 0; j < chunk[i].length; j++) {
				g.drawImage(chunk[i][j], (int) (Panel.TILE_WIDTH * (i - 1) + offsetx), (int) (Panel.TILE_HEIGHT * (j - 1) + offsety), (int) Panel.TILE_WIDTH, (int) Panel.TILE_HEIGHT, null);
			}
		}
	}
	
	public void update() {
		
		if(KeyInputs.key.equals(OKeyBinds.up.getKey())) { //UP
			if(offsety + speed * Panel.GAME_SCALE_HEIGHT <= Panel.TILE_HEIGHT) {
				offsety += speed * Panel.GAME_SCALE_HEIGHT;
			}else {
				ty--;
				chunk();
				offsety -= Panel.TILE_HEIGHT;
				offsety += speed * Panel.GAME_SCALE_HEIGHT;
			}
			Help.resetKey();
		}
		if(KeyInputs.key.equals(OKeyBinds.down.getKey())) { //DOWN
			if(offsety - speed * Panel.GAME_SCALE_HEIGHT >= -Panel.TILE_HEIGHT) {
				offsety -= speed * Panel.GAME_SCALE_HEIGHT;
			}else {
				ty++;
				chunk();
				offsety += Panel.TILE_HEIGHT;
				offsety -= speed * Panel.GAME_SCALE_HEIGHT;
			}
			Help.resetKey();
		}
		if(KeyInputs.key.equals(OKeyBinds.left.getKey())) { //LEFT
			if(offsetx + speed * Panel.GAME_SCALE_WIDTH <= Panel.TILE_WIDTH) {
				offsetx += speed * Panel.GAME_SCALE_WIDTH;
			}else {
				tx--;
				chunk();
				offsetx -= Panel.TILE_HEIGHT;
				offsetx += speed * Panel.GAME_SCALE_WIDTH;
			}
			Help.resetKey();
		}
		if(KeyInputs.key.equals(OKeyBinds.right.getKey())) { //RIGHT
			if(offsetx - speed * Panel.GAME_SCALE_WIDTH >= -Panel.TILE_WIDTH) {
				offsetx -= speed * Panel.GAME_SCALE_WIDTH;
			}else {
				tx++;
				chunk();
				offsetx += Panel.TILE_HEIGHT;
				offsetx -= speed * Panel.GAME_SCALE_WIDTH;
			}
			Help.resetKey();
		}
		
	}
	
	private void chunk() {
		for(int i = 0; i < chunk.length; i++) {
			for(int j = 0; j < chunk[i].length; j++) {
				chunk[i][j] = tiles[tx + i - 1][ty + j - 1];
			}
		}
	}
}
