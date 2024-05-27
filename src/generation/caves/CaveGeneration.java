package generation.caves;

import static main.Panel.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaveGeneration {
	
	private boolean[][] cellmap;
	private BufferedImage cave;
	private int deathLimit = 3, birthLimit = 4, numberOfSteps = 15;
	public int caveWidth, caveHeight;
	 
	public CaveGeneration(int caveWidth, int caveHeight) {
		this.caveWidth = caveWidth;
		this.caveHeight = caveHeight;
		cellmap = new boolean[caveWidth][caveHeight];
	}
	
	public BufferedImage createCave() {
		cave = new BufferedImage(caveWidth, caveHeight, BufferedImage.TYPE_INT_ARGB);

		initialiseMap(cellmap);
		for(int i=0; i < numberOfSteps; i++){
	        doSimulationStep(cellmap);
	    }
		paint();
		
		return cave;
	}
	
	public void paint() {
		
		Graphics2D g2D = cave.createGraphics();
		
		for(int x = 0; x < caveWidth; x++){
			for(int y = 0; y < caveHeight; y++){
			    
				if(cellmap[x][y]) {
					g2D.setPaint(new Color(0, 0, 0));
					g2D.fillRect(x, y, 1, 1);
				}else {
					g2D.setPaint(new Color(255, 255, 255));
					g2D.fillRect(x, y, 1, 1);
				}
			        	
			}
		}
//		placeOres(cellmap, g2D);
		g2D.dispose();
	}
	
	public void placeOres(boolean[][] map, Graphics2D g2D) {
//		Random r = new Random();
//
//		for(int x = 0; x < map.length; x++) {
//			for(int y = 0; y < map[0].length; y++) {
//				
//				int nbs = 8 - countAliveNeighbours(map, x, y);
//				if(!map[x][y]) {
//					if(nbs < maxAir) {
//			        	float f = r.nextFloat();
//			        	
//			        	if(f < 0.6f) { //stone
//			        		g2D.setPaint(new Color(0, 0, 0));
//							g2D.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
//							
//			        	}else if(f > 0.6f && f < 0.85f) { //iron
//			        		g2D.setPaint(Color.GRAY);
//							g2D.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
//							
//			        	}else if(f > 0.85f && f < 0.95f) { //gold
//			        		g2D.setPaint(Color.YELLOW);
//							g2D.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
//			        	}
//			        	else if(f > 0.95f) { //diamonds
//			        		g2D.setPaint(Color.BLUE);
//							g2D.fillRect(x * pixelSize, y * pixelSize, pixelSize, pixelSize);
//			        	}
//					}
//				}
//			}
//		}
	}
	
	public void doSimulationStep(boolean[][] oldMap){
	    boolean[][] newMap = new boolean[caveWidth][caveHeight];
	    //Loop over each row and column of the map
	    for(int x=0; x<oldMap.length; x++){
	        for(int y=0; y<oldMap[0].length; y++){
	            int nbs = countAliveNeighbours(oldMap, x, y);
	            //The new value is based on our simulation rules
	            //First, if a cell is alive but has too few neighbours, kill it.
	            if(oldMap[x][y]){
	                if(nbs < deathLimit){
	                    newMap[x][y] = false;
	                }
	                else{
	                    newMap[x][y] = true;
	                }
	            } //Otherwise, if the cell is dead now, check if it has the right number of neighbours to be 'born'
	            else{
	                if(nbs > birthLimit){
	                    newMap[x][y] = true;
	                }
	                else{
	                    newMap[x][y] = false;
	                }
	            }
	        }
	    }
	    cellmap = newMap;
	}
	
	public void initialiseMap(boolean[][] map) {
		float chanceToStartAlive = 0.45f;
		Random r = new Random();
		
	    for(int x = 0; x < caveWidth; x++){
	        for(int y = 0; y < caveHeight; y++){
	        	
	        	float f = r.nextFloat();
	        	
	            if(f < chanceToStartAlive){
	                map[x][y] = true;
	            }
	        }
	    }
	}
	
	public int countAliveNeighbours(boolean[][] map, int x, int y){
	    int count = 0;
	    for(int i=-1; i<2; i++){
	        for(int j=-1; j<2; j++){
	            int neighbour_x = x+i;
	            int neighbour_y = y+j;
	            //If we're looking at the middle point
	            if(i == 0 && j == 0){
	                //Do nothing, we don't want to add ourselves in!
	            }
	            //In case the index we're looking at it off the edge of the map
	            else if(neighbour_x < 0 || neighbour_y < 0 || neighbour_x >= map.length || neighbour_y >= map[0].length){
	                count = count + 1;
	            }
	            //Otherwise, a normal check of the neighbour
	            else if(map[neighbour_x][neighbour_y]){
	                count = count + 1;
	            }
	        }
	    }
		return count;
	}
}
