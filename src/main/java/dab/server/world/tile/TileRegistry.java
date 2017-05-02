package dab.server.world.tile;

import java.util.HashMap;
import java.util.Map;

public class TileRegistry
{
	public Map<String, Tile> tiles;
	
	public TileRegistry()
	{
		tiles = new HashMap<String, Tile>();
	}
	
	public void register(Tile tile)
	{
		tiles.put(tile.getName(), tile);
	}
	
	public Tile get(String tileName)
	{
		if (!tileExists(tileName))
			throw new RuntimeException(String.format("Unknown Tile Access: %s", tileName));
		return tiles.get(tileName);
	}
	
	public boolean tileExists(String tileName)
	{
		return tiles.get(tileName) != null;
	}
}
