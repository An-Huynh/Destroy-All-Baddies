package dab.server.world.zone;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;

import dab.common.physics.AABB;
import dab.common.physics.AABBMath;
import dab.server.Server;
import dab.server.world.tile.TeleporterTile;
import dab.server.world.tile.Tile;
import dab.server.world.tile.TileLocation;

public abstract class Zone
{
	private String id;
	private Map<String, Tile> layout;
	
	// Constructor
	public Zone()
	{
		id = "";
		layout = new HashMap<String, Tile>();
	}
	
	// Accessors
	public String getID()
	{
		return id;
	}
	
	public Tile getTile(int x, int y)
	{
		return layout.get(convertToKey(x, y));
	}
	
	public Tile getTile(Vector2i location)
	{
		return getTile(location.x, location.y);
	}
	
	public AABB getTileAABB(int x, int y)
	{
		AABB tileAABB = new AABB();
		tileAABB.copy(getTile(x, y).getBoundingBox());
		AABBMath.add(tileAABB, x, y);
		return tileAABB;
	}
	
	public AABB getTileAABB(Vector2i location)
	{
		return getTileAABB(location.x, location.y);
	}
	
	// Mutators
	public void setID(String name)
	{
		this.id = name;
	}
	
	public void addTile(Tile tile, int x, int y)
	{
		layout.put(convertToKey(x, y), tile);
	}
	
	public void addTile(Tile tile, Vector2i location)
	{
		layout.put(convertToKey(location), tile);
	}
	
	public boolean tileExistsAt(int x, int y)
	{
		return getTile(x, y) != null;
	}
	
	public boolean tileExistsAt(Vector2i location)
	{
		return tileExistsAt(location.x, location.y);
	}
	
	protected void addTeleporter(TileLocation src, TileLocation dest)
	{
		TeleporterTile teleTile = (TeleporterTile) Server.getTileRegistry().get("dab:tile:teleporter");
		teleTile.addTeleportPath(src, dest);
	}
	
	// etc
	private String convertToKey(int x, int y)
	{
		return String.format("%d:%d", x, y);
	}
	
	private String convertToKey(Vector2i location)
	{
		return convertToKey(location.x, location.y);
	}
}
