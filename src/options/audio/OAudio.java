package options.audio;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import game.gameoptions.GOSelection;
import game.gameoptions.GOSelectionButton;
import help.FileHelp;
import help.Help;
import inputs.MouseInputs;
import options.GameFiles;
import options.GameOptions;

public class OAudio {

	private OAudioButton Oback;
	private GOSelectionButton GOback;
	public static OAudioSlider music, sounds, ambient, creatures;
	public static String[] audios = {"music", "sounds", "ambient", "creatures"};
	
	public OAudio() {
		music = new OAudioSlider(175, 200, 275, 25, "MUSIC", 25f);
		sounds = new OAudioSlider(175, 320, 275, 25, "SOUNDS", 25f);
		ambient = new OAudioSlider(510, 200, 275, 25, "AMBIENT", 25f);
		creatures = new OAudioSlider(510, 320, 275, 25, "CREATURES", 25f);
		Oback = new OAudioButton(450, 30f, "RETURN");
		GOback = new GOSelectionButton(450, 30f, "RETURN");
		
		initSettings();
	}
	
	public void draw(Graphics g) {
		Help.drawString(g, 110, 40f, "AUDIO", new Color(255, 255, 255));

		music.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		sounds.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		ambient.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		creatures.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		Oback.draw(g, MouseInputs.xPos, MouseInputs.yPos);
	}
	
	public void OUpdate() {
		music.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		sounds.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		ambient.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		creatures.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		Oback.update(MouseInputs.cxPos, MouseInputs.cyPos, GameOptions.SELECTION);
		
	}
	
	public void GOUpdate() {
		music.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		sounds.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		ambient.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		creatures.update(MouseInputs.pxPos, MouseInputs.pyPos, MouseInputs.dxPos);
		GOback.save(MouseInputs.cxPos, MouseInputs.cyPos, GOSelection.OPTIONS);
	}
	
	private void initSettings() {
		try {
			music.setPos(GameFiles.initOptions(5, audios[0]));
			sounds.setPos(GameFiles.initOptions(6, audios[1]));
			ambient.setPos(GameFiles.initOptions(7, audios[2]));
			creatures.setPos(GameFiles.initOptions(8, audios[3]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
