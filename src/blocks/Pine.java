package blocks;

import java.awt.Color;

public class Pine extends Block{

	private int tileID = -256;
	private int hardness;
	private Color color = Color.CYAN;
	private String tag = "VEGETATION";
	private String name = "Pine";
	
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
