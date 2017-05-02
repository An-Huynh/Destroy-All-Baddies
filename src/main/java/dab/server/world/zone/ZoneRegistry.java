package dab.server.world.zone;

import java.util.Map;
import java.util.TreeMap;

public class ZoneRegistry
{
	private Map<String, Zone> zones;
	
	public ZoneRegistry()
	{
		zones = new TreeMap<String, Zone>();
	}
	
	public void register(Zone zone)
	{
		zones.put(zone.getID(), zone);
	}
	
	public Zone get(String zoneID)
	{
		if (!zoneExists(zoneID))
			throw new RuntimeException(String.format("Unknown Zone Access: %s", zoneID));
		return zones.get(zoneID);
	}
	
	public boolean zoneExists(String zoneID)
	{
		return zones.get(zoneID) != null;
	}
}
