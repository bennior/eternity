package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import generation.AutoTiling;
import generation.overworld.Vegetation;
import help.Help;
import help.Image;
import inputs.MouseInputs;
import main.GameStates;
import main.Panel;

import static main.Panel.*;

public class Menu {
	
	private MenuButton play, profil, options, quit;
	private BufferedImage vegetation, world;
	private BufferedImage[][] bwimages, bvimages;
		
	public Menu() {
		play = new MenuButton(250, 30f, "PLAY");
		profil = new MenuButton(300, 30f, "PROFIL");
		options = new MenuButton(350, 30f, "OPTIONS");
		quit = new MenuButton(400, 30f, "QUIT");
		world = Image.getImage(Image.MENU_WORLD_TILEMAP);
		vegetation = Image.getImage(Image.MENU_VEGETATION_TILEMAP);
		bwimages = AutoTiling.autotile(world);
		bvimages = Vegetation.vegTiles(vegetation);
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < world.getWidth(); i++) {
			for(int j = 0; j < world.getHeight(); j++) {
				g.drawImage(bwimages[i][j], 30 * i, 30 * j, null);
				if(vegetation.getRGB(i, j) == -256) {
					g.drawImage(bvimages[i][j], 30 * i, 30 * j - 9, null); 
				}else if(vegetation.getRGB(i, j) == -65281){
					g.drawImage(bvimages[i][j], 30 * i, 30 * j - 14, null);
				}else {
					g.drawImage(bvimages[i][j], 30 * i, 30 * j, null);
				}
			}
		}
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		Help.drawString(g, 110, 45f, "ETERNITY", new Color(255, 255, 255));
	
		play.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		profil.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		options.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		quit.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		
	}
	
	public void update() {
		play.update(MouseInputs.cxPos, MouseInputs.cyPos, GameStates.PLAY);
		profil.update(MouseInputs.cxPos, MouseInputs.cyPos, GameStates.PROFIL);
		options.update(MouseInputs.cxPos, MouseInputs.cyPos, GameStates.OPTIONS);
		quit.dispose(MouseInputs.cxPos, MouseInputs.cyPos);
	}
}
