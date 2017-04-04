package dab.common.registry;

import java.util.HashMap;
import java.util.Map;

import dab.common.zone.Zone;

public class ZoneRegistry {

	private static final Map<String, Zone> zoneList = new HashMap<String, Zone>();
	
	public static void register(Zone zone) {
		zoneList.put(zone.getName(), zone);
	}
	
	public static Zone get(String zoneName) {
		return zoneList.get(zoneName);
	}
	
}
