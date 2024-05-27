package main;

import java.awt.Color;

import help.Help;
import options.graphics.OGraphics;

public class GameLoop implements Runnable {

	private Frame frame;
	private Panel panel;
	private Thread gameThread;
	public final static int FPS_SET = 60;
	public final static int UPS_SET = 60;
	
	public static boolean hasFocus;

	public GameLoop() {
		panel = new Panel();
		frame = new Frame(panel);
		panel.requestFocus();
		startGameLoop();
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void render() {
		panel.repaint();
	}
	
	private void update() {
		//update
		changeFrameSize();
		changePanelSize();
		updateScale();
		panel.update();
		
		hasFocus = panel.hasFocus();
	}
	
	private void updateScale() {
		frame.updateScale();
	}
	
	private void changePanelSize() {
		panel.changePanelSize(frame.getSize());
	}
	
	private void changeFrameSize() {
		if(OGraphics.res.getOption().equals("FULLSCREEN")) {
			frame.fullscreen();
		}else {
			frame.custom(Help.resolutionToDimension(OGraphics.res.getOption()));
		}
	}
	
	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}
	}
}

