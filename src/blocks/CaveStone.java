package blocks;

import java.awt.Color;

public class CaveStone extends Block {
	
	private int tileID = -16777216;
	private int hardness;
	private Color color = new Color(255, 255, 255);
	private String tag = "CAVE";
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
