package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;

public class GraveyardZone extends Zone
{
	public GraveyardZone()
	{
		super();
		
		setID("dab:zone:graveyard");
		
		// add top row
		for (int x = 0; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 24);
		}
		
		for (int x = 0; x < 26; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
		}
		for (int x = 28; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
		}
		
		// add left side teleporters
		TileLocation leftSideDest = new TileLocation("dab:zone:night", 31, 1);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), -1, y);
			addTeleporter(new TileLocation(getID(), -1, y), leftSideDest);
		}
		
		// add right wall
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), 32, y);
		}
		
		// add graves
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 8, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 8, 2);
		
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 11, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 11, 2);
		
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 13, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 13, 2);
		
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 14, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 14, 2);
		
		TileLocation hellDest = new TileLocation("dab:zone:hell", 1, 23);
		for (int x = 26; x < 28; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), x, -1);
			addTeleporter(new TileLocation(getID(), x, -1), hellDest);
		}
	}
}