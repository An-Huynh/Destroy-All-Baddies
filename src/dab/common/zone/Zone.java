package dab.common.zone;

import java.util.HashMap;
import java.util.Map;

import dab.common.physics.AABB;
import dab.common.tile.Tile;

public abstract class Zone {

	private String name;
	private Map<String, Tile> layout;
	
	// Constructor
	
	public Zone() {
		this.layout = new HashMap<String, Tile>();
	}
	
	// Accessors
	
	public String getName() {
		return this.name;
	}
	
	public Tile getTile(int x, int y) {
		return layout.get(convertToKey(x, y));
	}
	
	public AABB getTileAABB(int x, int y) {
		Tile tile = getTile(x, y);
		
		if (tile != null) {
			return new AABB().copy(tile.getAABB()).addOffset(x, y);
		}
		return null;
	}
	
	// Mutators
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void addTile(Tile tile, int x, int y) {
		this.layout.put(tile.getName(), tile);
	}
	
	// etc
	
	public String convertToKey(int x, int y) {
		return Integer.toString(x) + "." + Integer.toString(y);
	}
	
	
}
