package blocks;

import java.awt.Color;

public class OrangeMushroom extends Block{

	private int tileID = -20561;
	private int hardness;
	private Color color = Color.CYAN;
	private String tag = "VEGETATION";
	private String name = "Mushroom";
	
	@Override
	public int getTileID() {
		return tileID;
	}

	@Override
	public int getHardness() {
		return hardness;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
