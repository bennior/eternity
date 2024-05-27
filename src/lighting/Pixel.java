package lighting;

import java.awt.Color;
public class Pixel {
	
    float a;
   
    public Pixel(int alpha){
       a = alpha;
    }
    
    public Pixel(float alpha){
    	a = alpha;
    }
    public Pixel(Color color){
    	
    	a = color.getAlpha();
    }

    public Pixel(int rgb, String var) {
        
        Color color = new Color(rgb);
        a = (rgb>>24) & 0xff;
    }

    public Color getColor(){
        
        return new Color(clamp255(Math.round(a)));
       
    }
    public int clamp255(int num){
       
        return  Math.max(0, Math.min(255, num));
    }

    
    
    
    public float diffFrom(Pixel otherPixel){

    	float alpha = otherPixel.a - a;

        return (alpha * alpha);
       
    }

    
    public void addPixel(Pixel otherPixel){
        
        a += otherPixel.a;
    }

    public void applyError(float fraction, Pixel errorPixel){ 
        
        a += errorPixel.a*(fraction);
       
    }
    
}
