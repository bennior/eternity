package lighting;

import java.awt.Graphics;

import game.world.Camera;
import game.world.Player;


public class DayLightCicle {
	
	private int alpha;
	private float alphaPerSeconds, a;
	private int index;
	private LightMap lightMap;

	public DayLightCicle(int alpha, float seconds, Player player) {
		this.alpha = alpha;
		alphaPerSeconds = 1 / seconds;
		lightMap = new LightMap(player);
	}
	
	public void draw(Graphics g) {
		if(Camera.CURRENTWORLD == Camera.CAVE) {
			lightMap.draw(g);
		}
	}
	
	public void update() {
//		if(index == UPS_SET) {
//			index = 0;
//			if(a + alphaPerSeconds < alpha && a + alphaPerSeconds > 0) {
//				a += alphaPerSeconds;
//			}else {
//				alphaPerSeconds *= -1;
//			}
//		}
//		index++;
		if(Camera.CURRENTWORLD == Camera.CAVE) {
			lightMap.update((int) 255);
		}
	}
}
