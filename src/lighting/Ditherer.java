package lighting;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.*;
public class Ditherer {
	
    public int width;
    public int height;
    public BufferedImage inputImage;
    
    BufferedImage outputImage;
   
    Color[] pallete;
   
    Pixel[][] pixelError;
    
    public Ditherer(Color[] colorArray){
       pallete = colorArray;
    }
    
    public BufferedImage dither(BufferedImage image, int pixelSize){
        
        inputImage = image;
//       outputImage = image;
        outputImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        width = image.getWidth() / pixelSize;
        height = image.getHeight() / pixelSize;
        
        pixelError = new Pixel[width][height];
        for (int x = 0; x < pixelError.length; x++){
            for (int y = 0; y < pixelError[0].length; y++){
                
                
                pixelError[x][y] = new Pixel(0);
                
            }
        }
       
        applyAlgorithm(pixelSize);
        return outputImage;
    }

    public void applyAlgorithm(int pixelSize) {
        
        int x = 0;
        int y = 0;
        while(x < width){
        	
            y = 0;
            
            while(y < height){
                
                Pixel preErrorMatch = new Pixel(inputImage.getRGB(x * pixelSize, y * pixelSize), "");
                
                Pixel adjustedMatchPixel = new Pixel(preErrorMatch.a);
                
        
                adjustedMatchPixel.addPixel(pixelError[x][y]);
                
                
                
                Pixel palletePixel = closestPixelFromPallete(adjustedMatchPixel);
               
               
                
                Pixel curErrorPixel = createErrorPixel(adjustedMatchPixel, palletePixel);
                
                //System.out.println("match:"+adjustedMatchPixel.dataString()+ " + error:"+ curErrorPixel.dataString()+ " should = " + palletePixel.dataString());
                

                floydSteinberg(x, y, curErrorPixel);

                setAlpha(outputImage, x, y, (int) (palletePixel.a), pixelSize);
                y++;
            }
            x++;
            //System.out.println("Dithering progress: "+x+"/"+width);
        }
           
    }

    public void floydSteinberg(int x, int y, Pixel errorPixel){
       

        //System.out.println(x+" "+y);
       
        float a = (float)1/16;
        float b = (float)3/16;
        float c = (float)5/16;
        float d = (float)7/16;
        //System.out.println(d);
        if(x < width-1){
            //System.out.println("1");
            pixelError[x+1][y].applyError(d, errorPixel);
        }
        if(x > 0 && y < height-1){
            //System.out.println("2");
            pixelError[x-1][y+1].applyError(b, errorPixel);
        }
        if( y < height - 1){
            //System.out.println("3");
            pixelError[x][y+1].applyError(c, errorPixel);
        }
        if ( x < width - 1 && y < height - 1){
            //System.out.println("4");
            pixelError[x+1][y+1].applyError(a, errorPixel);
        }
        
    }

    public Pixel createErrorPixel(Pixel pixelBefore, Pixel pixelAfter){
    	
        float aDiff = Math.abs(pixelAfter.a - pixelBefore.a);
        if (pixelAfter.a > pixelBefore.a){
            aDiff = -aDiff;
        }
        
        return new Pixel(aDiff);
    }

    

    public Pixel closestPixelFromPallete(Pixel match){
        //System.out.println("curError in closestPixel: "+curError);
        Pixel result = new Pixel(pallete[0]);
//        System.out.println(match.a);
        
        for(int i = 1; i < pallete.length; i++){
            Pixel checkPixel = new Pixel(pallete[i]);
            //System.out.println("checking "+checkPixel.dataString());
            if (Math.abs(match.diffFrom(checkPixel)) < Math.abs(match.diffFrom(result))){
                //System.out.println("RESULT CHANGED to "+checkPixel.dataString()+ "from "+ result.dataString());
                result = checkPixel;
                
            }
        }
        
        //System.out.println("converted "+match.dataString()+ " => " + result.dataString());
        return result;
    }
    
    public void setAlpha(BufferedImage img, int x, int y, int alpha, int pixelSize) {
    	
    	Graphics2D g2D = (Graphics2D) img.getGraphics();
    	
    	g2D.setColor(new Color(0, 0, 0, alpha));
    	g2D.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
    	g2D.dispose();
    }
    
}


