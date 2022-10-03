
package help;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Image {
	
	public static final String MENU_TILEMAP = "menu_tilemap.png";
	public static final String OPTIONS_TILEMAP = "options_tilemap.png";
	public static final String TILESET = "tileset.png";
	public static final String CURSOR = "cursor.png";

	public static BufferedImage getImage(String fileName) {
		BufferedImage img = null;
		InputStream is = Image.class.getResourceAsStream("/images/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
}
