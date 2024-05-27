package game.world;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import help.Image;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.Panel;
import options.keybinds.OKeyBinds;
import playing.Cursor;
import playing.PlayerEvents;

import static main.Panel.GAME_WIDTH;
import static main.Panel.GAME_HEIGHT;

public class Player {
	
	public int width = 45, height = 45, x = (GAME_WIDTH - width) / 2, y = (GAME_HEIGHT - height) / 2; //457 + 22.5 //247 + 22.5 -> 480 270
	public final int DEFAULT_X = x, DEFAULT_Y = y;
	private BufferedImage player;
	private int count, index, dir;
	private int chunkW, chunkH, tilesW, tilesH;
	public static int playerx, playery;	
	
	private PlayerEvents events;

	public Player(int chunkW, int chunkH, int tilesW, int tilesH) {
		events = new PlayerEvents();
		player = Image.getImage(Image.PLAYER);	
		this.chunkW = chunkW;
		this.chunkH = chunkH;
		this.tilesW = tilesW;
		this.tilesH = tilesH;
	}
	
	public void draw(Graphics g) {		
		if(KeyInputs.key.equals(OKeyBinds.up.getKey())) { //UP
			g.drawImage(player.getSubimage(45, 45 * count, 45, 45), x, y, null);
			dir = 45;
		}else if(KeyInputs.key.equals(OKeyBinds.down.getKey())) { //DOWN
			g.drawImage(player.getSubimage(0, 45 * count, 45, 45), x, y, null);
			dir = 0;
		}else if(KeyInputs.key.equals(OKeyBinds.left.getKey())) { //LEFT
			g.drawImage(player.getSubimage(90, 45 * count, 45, 45), x, y, null);
			dir = 90;
		}else if(KeyInputs.key.equals(OKeyBinds.right.getKey())) { //RIGHT
			g.drawImage(player.getSubimage(135, 45 * count, 45, 45), x, y, null);
			dir = 135;
		}else {
			if(MouseInputs.yPos < diagonal1(MouseInputs.xPos) && MouseInputs.yPos > diagonal2(MouseInputs.xPos)) {
				g.drawImage(player.getSubimage(90, 45, 45, 45), x, y, null);
			}else if(MouseInputs.yPos > diagonal1(MouseInputs.xPos) && MouseInputs.yPos > diagonal2(MouseInputs.xPos)) {
				g.drawImage(player.getSubimage(0, 0, 45, 45), x, y, null);
			}else if(MouseInputs.yPos > diagonal1(MouseInputs.xPos) && MouseInputs.yPos < diagonal2(MouseInputs.xPos)) {
				g.drawImage(player.getSubimage(135, 45, 45, 45), x, y, null);
			}else if(MouseInputs.yPos < diagonal1(MouseInputs.xPos) && MouseInputs.yPos < diagonal2(MouseInputs.xPos)) {
				g.drawImage(player.getSubimage(45, 0, 45, 45), x, y, null);
			}
		}
		
//		events.drawNames(g, cursor, world, cave, vegetation, offsetx, offsety, tx, ty);
	}
	
	public void update(int tx, int ty, int offsetx, int offsety, int speed, int tilePosition, Cursor cursor, BufferedImage world, BufferedImage vegetation, BufferedImage cave) {
		
		if(KeyInputs.key.equals(OKeyBinds.up.getKey())) { //UP
			if(y <= DEFAULT_Y) {
				if(ty - 1 <= 0 && y - speed > 0) {
					y -= speed;
				}
			}else {
				y -= speed;
			}
			animation();
		}
		if(KeyInputs.key.equals(OKeyBinds.down.getKey())) { //DOWN
			if(y >= DEFAULT_Y) {
				if(ty + chunkH + 1 > tilesH && y + height + speed < GAME_HEIGHT) {
					y += speed;
				}
			}else {
				y += speed;
			}
			animation();
		}
		if(KeyInputs.key.equals(OKeyBinds.left.getKey())) { //LEFT
			if(x <= DEFAULT_X) {
				if(tx - 1 <= 0 && x - speed > 0) {
					x -= speed;
				}
			}else {
				x -= speed;
			}
			animation();
		}
		if(KeyInputs.key.equals(OKeyBinds.right.getKey())) { //RIGHT
			if(x >= DEFAULT_X) {
				if(tx + chunkW + 1 > tilesW && x + width + speed < GAME_WIDTH) {
						x += speed;
					}
				}else {
					x += speed;
			}
			animation();
		}
		
		playerx = x;
		playery = y;	
		
		//player events
		events.enterCave(tilePosition);
		events.breakTile(cursor, world, cave, vegetation, offsetx, offsety, tx, ty);
		events.drawNames(cursor, world, cave, vegetation, offsetx, offsety, tx, ty);
	}
	
	private float diagonal1(int x) {
		float k = (Panel.GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT) / (Panel.GAME_WIDTH * Panel.GAME_SCALE_WIDTH);
		float d = (Panel.GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT);	
				
		return -k*x + d;
	}
	
	private float diagonal2(int x) {
		float k = (Panel.GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT) / (Panel.GAME_WIDTH * Panel.GAME_SCALE_WIDTH);
		
		return k*x;
	}
	
	private void animation() {
		index++;
		if(index == 15) {
			count++;
		}else if(index >= 30) {
			count = 0;
			index = 0;
		}
	}
}
