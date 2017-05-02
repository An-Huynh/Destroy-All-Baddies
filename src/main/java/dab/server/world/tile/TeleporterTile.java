package dab.server.world.tile;

import java.util.HashMap;
import java.util.Map;

import dab.common.physics.AABB;

public class TeleporterTile extends Tile
{
	private Map<TileLocation, TileLocation> teleportPaths;
	
	public TeleporterTile()
	{
		super();
		
		setName("dab:tile:teleporter");
		setBoundingBox(new AABB(0.5f, 0.5f, 1.0f, 1.0f));
		setSolid(false);
		
		teleportPaths = new HashMap<TileLocation, TileLocation>();
	}
	
	public void addTeleportPath(TileLocation src, TileLocation dest)
	{
		teleportPaths.put(src, dest);
	}
	
	public TileLocation getDestination(TileLocation src)
	{
		return teleportPaths.get(src);
	}
}
