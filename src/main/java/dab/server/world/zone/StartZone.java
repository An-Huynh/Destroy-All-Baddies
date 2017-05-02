package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;
/**
 * initializes blocks and teleporters for
 * the start zone extending abstract zone class
 * 
 * @author Cristopher Huerta
 */
public class StartZone extends Zone
{
	/**
	* constructor handles initialization of zone
	 */
	public StartZone()
	{
		super();
		
		setID("dab:zone:start");//set id of zone

		// add ground and top layer
		for (int x = 0; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 24);
		}
		
		//add telporter to left size cave zone
		TileLocation leftSideDest = new TileLocation("dab:zone:cave", 11, 1);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), -1, y);
			addTeleporter(new TileLocation(getID(), -1, y), leftSideDest);
		}
		
		//add telporter to right night zone
		TileLocation rightSideDest = new TileLocation("dab:zone:night", 0, 1);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), 32, y);
			addTeleporter(new TileLocation(getID(), 32, y), rightSideDest);
		}
	}
}
