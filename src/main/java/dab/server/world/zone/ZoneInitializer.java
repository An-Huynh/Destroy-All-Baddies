package dab.server.world.zone;

import dab.server.Server;

public class ZoneInitializer
{
	/*
	 * adds all zones to be used into zone registry
	 * 
	*/
	public static void initialize()
	{
		Server.getZoneRegistry().register(new StartZone());
		Server.getZoneRegistry().register(new NightZone());
		Server.getZoneRegistry().register(new GraveyardZone());
		Server.getZoneRegistry().register(new CaveZone());
		Server.getZoneRegistry().register(new HellZone());
		Server.getZoneRegistry().register(new HellExitZone());
		Server.getZoneRegistry().register(new StreetZone());
	}
}
