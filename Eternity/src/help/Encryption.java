package help;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class Encryption {
	
	public static String encode(BufferedImage image) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		byte[] data = baos.toByteArray();
		
        String encodedString = Base64.getEncoder().encodeToString(data);
        
        return encodedString;
	}
	
	public static BufferedImage decode(String encodedString) {
				
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		ByteArrayInputStream bais = new ByteArrayInputStream(decodedBytes);
		
		try {
			return ImageIO.read(bais);
		} catch (IOException e) {
			throw new RuntimeException(e);	
		}
	}
}
