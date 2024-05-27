package blocks;

import java.awt.Color;

public abstract class Block {

	public abstract int getTileID();
	public abstract int getHardness();
	public abstract Color getColor();
	public abstract String getTag();
	public abstract String getName();
}
