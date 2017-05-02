package dab.server.world.tile;

import dab.common.physics.AABB;

public class SolidTile extends Tile
{
	public SolidTile()
	{
		super();
		
		setName("dab:tile:solid");
		setBoundingBox(new AABB(0.5f, 0.5f, 1.0f, 1.0f));
		setSolid(true);
	}
}
