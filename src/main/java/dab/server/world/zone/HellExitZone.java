package dab.server.world.zone;

import dab.server.Server;
import dab.server.world.tile.TileLocation;
/**
 * initializes blocks and teleporters for
 * the hell exit zone
 * 
 * @author Cristopher Huerta
 */
public class HellExitZone extends Zone
{
	/**
	* constructor handles initialization of zone
	 */
	public HellExitZone()
	{
		super();
		setID("dab:zone:hellexit");
		//add platform blocks
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 0, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 1, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 4, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 5, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 8, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 9, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 11, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 12, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 13, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 15, 3);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 16, 3);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 18, 4);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 20, 5);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 23, 6);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 24, 6);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 25, 7);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 25, 8);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 27, 9);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 28, 9);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 28, 11);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 29, 4);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 4);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 24, 11);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 23, 12);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 22, 13);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 21, 14);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 20, 15);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 19, 16);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 16, 16);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 15, 17);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 14, 17);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 13, 17);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 12, 17);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 11, 17);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 9, 18);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 9, 19);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 10, 18);
		for(int i = 12; i < 29; i++){
			addTile(Server.getTileRegistry().get("dab:tile:solid"), i, 20);
		}
		for(int i = 3; i < 27; i++){
			addTile(Server.getTileRegistry().get("dab:tile:solid"), i, 23);
		}
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 28, 21);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 29, 23);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 23);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 30, 2);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 31, 1);
		addTile(Server.getTileRegistry().get("dab:tile:solid"), 31, 2);
		
		//add telport for moving to left zone
		TileLocation leftSideDest = new TileLocation("dab:zone:hell", 31, 3);
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), -1, y);
			addTeleporter(new TileLocation(getID(), -1, y), leftSideDest);
		}
		
		//add telport for moving up to street zone
		TileLocation streetTeleporter = new TileLocation("dab:zone:street", 3, 1);
		for (int x = 27; x < 29; ++x)
		{
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), x, 23);
			addTeleporter(new TileLocation(getID(), x, 23), streetTeleporter);
		}
		
		//add telport to beginning of map after falling into lava
		TileLocation respawn = new TileLocation("dab:zone:hellexit", 0, 3);
		for (int x = 0; x < 32; ++x) {
			addTile(Server.getTileRegistry().get("dab:tile:teleporter"), x, 1);
			addTeleporter(new TileLocation(getID(), x, 1), respawn);
		}
		
		// add right wall
		for (int y = 0; y < 24; ++y)
		{
			addTile(Server.getTileRegistry().get("dab:tile:solid"), 31, y);
		}
	}
}
