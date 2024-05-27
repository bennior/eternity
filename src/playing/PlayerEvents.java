package playing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import blocks.Block;
import blocks.BrownMushroom;
import blocks.Bush1;
import blocks.Bush2;
import blocks.CaveStone;
import blocks.Grass;
import blocks.OrangeMushroom;
import blocks.Pine;
import blocks.SmallStone1;
import blocks.SmallStone2;
import blocks.Stone;
import blocks.Tree;
import game.world.Camera;
import game.world.Player;
import generation.TileRGB;
import help.Help;
import inputs.KeyInputs;
import inputs.MouseInputs;
import main.GameLoop;
import main.Panel;
import options.keybinds.OKeyBinds;

public class PlayerEvents {
	
	private ArrayList<Block> blocks;
	private Stone stone;
	private CaveStone caveStone;
	private Grass grass;
	private SmallStone1 smallStone1;
	private SmallStone2 smallStone2;
	private Bush1 bush1;
	private Bush2 bush2;
	private BrownMushroom brownMushroom;
	private OrangeMushroom orangeMushroom;
	private Tree tree;
	private Pine pine;
	
	private int displayNameCount;
	
	public PlayerEvents() {
		blocks = new ArrayList<>();
		stone = new Stone();
		caveStone = new CaveStone();
		grass = new Grass();
		smallStone1 = new SmallStone1();
		smallStone2 = new SmallStone2();
		bush1 = new Bush1();
		bush2 = new Bush2();
		brownMushroom = new BrownMushroom();
		orangeMushroom = new OrangeMushroom();
		tree = new Tree();
		pine = new Pine();
		
		blocks.add(stone);
		blocks.add(caveStone);
		blocks.add(grass);
		blocks.add(smallStone1);
		blocks.add(smallStone2);
		blocks.add(bush1);
		blocks.add(bush2);
		blocks.add(brownMushroom);
		blocks.add(orangeMushroom);
		blocks.add(tree);
		blocks.add(pine);
	}
	
	public void enterCave(int tile) {
		if(tile == TileRGB.hole && KeyInputs.keyCode == KeyEvent.VK_SPACE && KeyInputs.pressed) { //SPACE fixed key for entering caves
			Camera.CURRENTWORLD = Camera.CAVE;
		}
	}
	
	public void breakTile(Cursor cursor, BufferedImage world, BufferedImage cave, BufferedImage vegetation, int offsetx, int offsety, int tx, int ty) {
		
		int x = (int) ((MouseInputs.xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
		int y = (int) ((MouseInputs.yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		if(MouseInputs.dragged) {		
			x = (int) ((MouseInputs.dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
			y = (int) ((MouseInputs.dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		}
		
		if((Math.abs(rightX - x) <= 1 || Math.abs(leftX - x) <= 1) && (Math.abs(bottomY - y) <= 1 || Math.abs(topY - y) <= 1)) {
			if(!(leftX - x == 1 && topY - y == 1) && !(rightX - x == -1 && topY - y == 1) && !(leftX - x == 1 && bottomY - y == -1) && !(rightX - x == -1 && bottomY - y == -1)) {
				if((MouseInputs.mouseButton == Help.StringToMouseButton(OKeyBinds.destroy.getKey()) && MouseInputs.pressed)) {
					for(Block block : blocks) {
						if(cursor.getMouseTilePosition(world, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) == block.getTileID() || 
						   cursor.getMouseTilePosition(cave, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) ==  block.getTileID() ||
						   cursor.getMouseTilePosition(vegetation, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) ==  block.getTileID()) {
							
							Graphics2D g2D = null;
							
							switch(block.getTag()) {
							case "OVERWORLD": 
								g2D = world.createGraphics();	
//								System.out.println(block.getName());
							break;
							case "CAVE": 
								g2D = cave.createGraphics();
								System.out.println(block.getName());

							break;
							case "VEGETATION":
								g2D = vegetation.createGraphics();
							break;
							}
							
							g2D.setPaint(block.getColor());
							g2D.drawRect(tx + x + 1, ty + y + 1, 0, 0);
							g2D.dispose();
							
							Camera.rearrange = true;
							System.out.println("worked");

						}
					}
					MouseInputs.pressed = false;
				}
			}
		}
	}
	
	public void drawNames(Cursor cursor, BufferedImage world, BufferedImage cave, BufferedImage vegetation, int offsetx, int offsety, int tx, int ty) {
		int x = (int) ((MouseInputs.xPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
		int y = (int) ((MouseInputs.yPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		
		int topY = (Player.playery - offsety + 15) / 30;
		int bottomY = (Player.playery - offsety + 30) / 30;
		int leftX = (Player.playerx - offsetx + 15) / 30;
		int rightX = (Player.playerx - offsetx + 30) / 30;
		
		if(MouseInputs.dragged) {		
			x = (int) ((MouseInputs.dxPos / Panel.GAME_SCALE_WIDTH - offsetx) / 30);
			y = (int) ((MouseInputs.dyPos / Panel.GAME_SCALE_HEIGHT - offsety) / 30);
		}
		
		if((Math.abs(rightX - x) <= 1 || Math.abs(leftX - x) <= 1) && (Math.abs(bottomY - y) <= 1 || Math.abs(topY - y) <= 1)) {
			if(!(leftX - x == 1 && topY - y == 1) && !(rightX - x == -1 && topY - y == 1) && !(leftX - x == 1 && bottomY - y == -1) && !(rightX - x == -1 && bottomY - y == -1)) {
					for(Block block : blocks) {
						if(cursor.getMouseTilePosition(world, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) == block.getTileID() || 
						   cursor.getMouseTilePosition(cave, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) ==  block.getTileID() ||
						   cursor.getMouseTilePosition(vegetation, offsetx, offsety, MouseInputs.xPos, MouseInputs.yPos, MouseInputs.dxPos, MouseInputs.dyPos, tx, ty) ==  block.getTileID()) {
							

								if(!MouseInputs.moved && !MouseInputs.dragged && !MouseInputs.pressed) {
									displayNameCount++;
									if(displayNameCount > GameLoop.UPS_SET * 3) {
//										Help.drawString(g, MouseInputs.xPos, MouseInputs.yPos, 15, block.getName(), Color.WHITE);
//										System.out.println(block.getName());
									}
								}else {
									displayNameCount = 0;
								}
					}
				}
			}
		}
	}
}
