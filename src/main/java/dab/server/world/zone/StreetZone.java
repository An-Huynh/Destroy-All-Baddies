package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;
/**
 * initializes blocks and teleporters for
 * the street zone extending abstract zone class
 * 
 * @author Cristopher Huerta
 */
public class StreetZone extends Zone
{
	/**
	* constructor handles initialization of zone
	 */
	public StreetZone()
	{
		super();
		setID("dab:zone:street");//set id of zone
		
		// add ground layer
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 0, 0);
		for (int x = 3; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
		}
		
		// add top layer
		for (int x = 0; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 24);
		}
		
		// add left and right solid walls
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), -1, y);
			addTile(Server.getTileRegistry().get("dab:tile:solid"), 32, y);
		}
		
		// add teleporter for falling down to hell zone
		TileLocation sewerDestination = new TileLocation("dab:zone:hellexit", 27, 22);
		for (int x = 1; x < 3; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), x, 0);
			addTeleporter(new TileLocation(getID(), x, 0), sewerDestination);
		}
	}
}
