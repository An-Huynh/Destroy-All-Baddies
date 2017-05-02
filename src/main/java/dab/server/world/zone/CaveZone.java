package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;

public class CaveZone extends Zone
{
	public CaveZone()
	{
		super();
		setID("dab:zone:cave");
		
		// add top and bottom
		for (int x = 0; x < 12; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 12);
		}
		
		// add left wall
		for (int y = 0; y < 9; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), -1, y);
		}
		
		// add right side teleporters
		TileLocation rightSideDest = new TileLocation("dab:zone:start", 0, 1);
		for (int y = 0; y < 9; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), 12, y);
			addTeleporter(new TileLocation("dab:zone:cave", 12, y), rightSideDest);
		}
	}
}
