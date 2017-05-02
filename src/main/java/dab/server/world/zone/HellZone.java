package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;
/**
 * initializes blocks and teleporters for
 * the hell zone extending abstract zone class
 * 
 * @author Cristopher Huerta
 */
public class HellZone extends Zone
{
	/**
	* constructor handles initialization of zone
	*/
	public HellZone()
	{
		super();
		setID("dab:zone:hell");//set id of zone
		
		// add ground layer
		for (int x = 0; x < 32; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), x, 0);
		}
		
		//add extra wall blocks
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 1, 3);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 1, 4);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 2, 3);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 2, 4);
		for (int x = 1; x < 5; ++x)
		{
			for (int y = 1; y < 3; ++y)
			{
				addTile(Server.getTileRegistry().get("dab:tile:solid"), x, y);
			}
		}
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 31, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 31, 2);
		
		//add right side teleporter to hell exit zone
		TileLocation rightSideDest = new TileLocation("dab:zone:hellexit", 0, 3);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), 32, y);
			addTeleporter(new TileLocation(getID(), 32, y), rightSideDest);
			
			addTile(Server.getTileRegistry().get("dab:tile:solid"), 0, y);
		}
	}
}
