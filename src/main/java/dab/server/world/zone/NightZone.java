package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;

public class NightZone extends Zone
{
	public NightZone()
	{
		super();
		
		setID("dab:zone:night");
		
		// add ground and top layer
		for (int x = 0; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 24);
		}
		
		// add left side teleporters
		TileLocation leftSideDestination = new TileLocation("dab:zone:start", 31, 1);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), -1, y);
			addTeleporter(new TileLocation(getID(), -1, y), leftSideDestination);
		}
		
		// add right side teleporters
		TileLocation rightSideDestination = new TileLocation("dab:zone:graveyard", 0, 1);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), 32, y);
			addTeleporter(new TileLocation(getID(), 32, y), rightSideDestination);
		}	
	}
}
