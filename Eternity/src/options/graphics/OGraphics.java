package options.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import help.Help;
import inputs.MouseInputs;
import options.GameFiles;
import options.GameOptions;

public class OGraphics {
	
	private OGraphicsButton back;
	public static OGraphicsSettings lighting, shadows, grass, animations, res;
	public static String[] graphics = {"lighting", "animations", "shadows", "res", "grass"};
	
	public OGraphics() {
		//Scrollfunction
		lighting = new OGraphicsSettings(190, 27f, "LIGHTING");
			lighting.addComponent("DITHER");
			lighting.addComponent("SMOOTH");
		animations = new OGraphicsSettings(233, 27f, "ANIMATIONS");
			animations.addComponent("ON");
			animations.addComponent("OFF");
		shadows = new OGraphicsSettings(276, 27f, "SHADOWS");
			shadows.addComponent("ON");
			shadows.addComponent("OFF");
		res = new OGraphicsSettings(319, 27f, "RES");
			res.addComponent("FULLSCREEN");
			res.addComponent("960x540");
			res.addComponent("1024x768");
			res.addComponent("1280x720");
			res.addComponent("1280x800");
			res.addComponent("1280x1024");
			res.addComponent("1366x768");
			res.addComponent("1440x900");
			res.addComponent("1600x900");
			res.addComponent("1920x1080");
		grass = new OGraphicsSettings(362, 27f, "GRASS");
			grass.addComponent("BENDING");
			grass.addComponent("IDLE");
			
		back = new OGraphicsButton(450, 30f, "RETURN");
		initSettings();
	}
	
	public void draw(Graphics g) {
		Help.drawString(g, 110, 40f, "GRAPHICS", new Color(255, 255, 255));

		lighting.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		animations.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		shadows.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		grass.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		res.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		back.draw(g, MouseInputs.xPos, MouseInputs.yPos);
		}
	
	public void update() {
		lighting.update(MouseInputs.cxPos, MouseInputs.cyPos);
		animations.update(MouseInputs.cxPos, MouseInputs.cyPos);
		shadows.update(MouseInputs.cxPos, MouseInputs.cyPos);
		grass.update(MouseInputs.cxPos, MouseInputs.cyPos);
		res.update(MouseInputs.cxPos, MouseInputs.cyPos);
		back.update(MouseInputs.cxPos, MouseInputs.cyPos, GameOptions.SELECTION);
	}
	
	private void initSettings() {
		try {
			lighting.setOption(GameFiles.initOptions(0, graphics[0]));
			animations.setOption(GameFiles.initOptions(1, graphics[1]));
			shadows.setOption(GameFiles.initOptions(2, graphics[2]));
			res.setOption(GameFiles.initOptions(3, graphics[3]));
			grass.setOption(GameFiles.initOptions(4, graphics[4]));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
