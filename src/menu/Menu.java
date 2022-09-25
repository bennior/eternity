package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import generation.AutoTiling;
import help.Help;
import help.Image;
import inputs.MouseInputs;
import main.GameStates;
import main.Panel;

import static main.Panel.*;

public class Menu {
	
	private MenuButton play, profil, options, quit;
	private BufferedImage background;
	private BufferedImage[][] bimages;
		
	public Menu() {
		play = new MenuButton(250, 30f, "PLAY");
		profil = new MenuButton(300, 30f, "PROFIL");
		options = new MenuButton(350, 30f, "OPTIONS");
		quit = new MenuButton(400, 30f, "QUIT");
		background = Image.getImage(Image.MENU_TILEMAP);
		bimages = AutoTiling.autotile(background);
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < background.getWidth(); i++) {
			for(int j = 0; j < background.getHeight(); j++) {
				g.drawImage(bimages[i][j],(int) (30 * i * Panel.GAME_SCALE_WIDTH), (int) (30 * j * Panel.GAME_SCALE_HEIGHT), (int) (30 * Panel.GAME_SCALE_WIDTH), (int) (30 * Panel.GAME_SCALE_HEIGHT), null);
			}
		}
		
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, (int) (GAME_WIDTH * Panel.GAME_SCALE_WIDTH),(int) (GAME_HEIGHT * Panel.GAME_SCALE_HEIGHT));
		
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
