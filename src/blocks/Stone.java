package blocks;

import java.awt.Color;

public class Stone extends Block {
	
	private int tileID = -2302756;
	private int hardness;
	private Color color = new Color(0, 128, 0);
	private String tag = "OVERWORLD";
	private String name = "Rock";

	
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
