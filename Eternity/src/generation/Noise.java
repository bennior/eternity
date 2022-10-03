package generation;

import java.util.Random;

public class Noise {

    static double [][][] gradVectors;
    static double [][][] dotProducts;
    static double [][] points;
    private static int width, height, frequency; //division of width and height by frequency -> must be natural number


    public Noise(int width, int height, int frequency) {
    	Noise.width = width;
    	Noise.height = height;
    	Noise.frequency = frequency;
    	generatePerlin();
    }
    
    public float getNoiseValue(int x, int y) {
    	
    	return clamp((float)((points[x][y]+1)/2));
    }
    
    public static void generatePerlin()
    {
        Random randNum = new Random();
        gradVectors = new double[2][(width/frequency)+1][(height/frequency)+1];
        dotProducts = new double[4][width][height];
        points = new double[width][height];

        for (int i = 0; i < (height / frequency) + 1; i++) // create gradient vectors
        {
            for (int j = 0; j < (width / frequency) + 1; j++)
            {
                double angle = randNum.nextFloat() * 2 * Math.PI;
                gradVectors[0][j][i] = Math.cos(angle);
                gradVectors[1][j][i] = Math.sin(angle);
            }
        }
        for (int i = 0; i < height; i++) // calculate dot products
        {
            for (int j = 0; j < width; j++)
            {
                double x = ((double)j)/frequency;
                double y = ((double)i)/frequency;
                int x0 = (int)Math.floor(x);
                int x1 = x0 + 1;
                int y0 = (int)Math.floor(y);
                int y1 = y0 + 1;

                dotProducts[0][j][i] = gradVectors[0][x0][y0]*(x - x0) + gradVectors[1][x0][y0]*(y - y0); // top left
                dotProducts[1][j][i] = gradVectors[0][x1][y0]*(x - x1) + gradVectors[1][x1][y0]*(y - y0); // top right
                dotProducts[2][j][i] = gradVectors[0][x0][y1]*(x - x0) + gradVectors[1][x0][y1]*(y - y1); // bottom left
                dotProducts[3][j][i] = gradVectors[0][x1][y1]*(x - x1) + gradVectors[1][x1][y1]*(y - y1); // bottom right
            }
        }
        for (int i = 0; i < height; i++) // interpolation
        {
            for (int j = 0; j < width; j++)
            {
                double x = ((double)j)/frequency;
                double y = ((double)i)/frequency;
                int x0 = (int)Math.floor(x);
                int y0 = (int)Math.floor(y);

                points[j][i] = interpolate(dotProducts[0][j][i],dotProducts[1][j][i],dotProducts[2][j][i],dotProducts[3][j][i], fade(x - x0), fade(y - y0));
            }
        }
    }
    public static double interpolate(double a, double b, double c, double d, double wx, double wy)
    {
        return linearInterpolate(linearInterpolate(a, b, wx), linearInterpolate(c, d, wx), wy);
    }
    
    public static double linearInterpolate(double a, double b, double w)
    {
        return (b - a) * w + a;
    }
    
    public static double fade(double t){ // this function is from here: https://rtouti.github.io/graphics/perlin-noise-algorithm
        return 6*t*t*t*t*t - 15*t*t*t*t + 10*t*t*t;
    }
    public static float clamp(float a) // clamps a value passed to it to a float between 0 and 1. Used to sanitize the point data for the colour data
    {
        if (a < 0) return 0.0f;
        else if (a > 1) return 1.0f;

        return a;
    }
}

