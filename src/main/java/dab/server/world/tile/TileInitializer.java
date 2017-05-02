package dab.server.world.tile;

import dab.server.Server;

public class TileInitializer
{
	public static void initialize()
	{
		Server.getTileRegistry().register(new SolidTile());
		Server.getTileRegistry().register(new TeleporterTile());
	}
}
