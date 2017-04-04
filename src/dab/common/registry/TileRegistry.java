package dab.common.registry;

import java.util.HashMap;
import java.util.Map;

import dab.common.tile.Tile;

public class TileRegistry {

	private static final Map<String, Tile> tileList = new HashMap<String, Tile>();
	
	public static void register(Tile tile) {
		tileList.put(tile.getName(), tile);
	}
	
	public static Tile get(String tileName) {
		return tileList.get(tileName);
	}
	
}
