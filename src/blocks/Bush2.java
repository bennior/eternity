package blocks;

import java.awt.Color;

public class Bush2 extends Block {

	private int tileID = -1;
	private int hardness;
	private Color color = Color.CYAN;
	private String tag = "VEGETATION";
	private String name = "Bush";
	
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
