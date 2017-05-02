package dab.server.world.zone;

import java.util.Map;
import java.util.TreeMap;
/**
 * This classs manages the zone registry
 * and its methods
 * 
 * @author Cristopher Huerta
 */
public class ZoneRegistry
{
	private Map<String, Zone> zones;
	
	/* constructor for ZoneRegistry
	 * initialized zones Map
	*/
	public ZoneRegistry()
	{
		zones = new TreeMap<String, Zone>();
	}
	
	/* registers zone given in parameter into
	 * zone registry
	* 
	* @param	zone	Zone to be registered
	*/
	public void register(Zone zone)
	{
		zones.put(zone.getID(), zone);
	}
	
	/* returns a zone given zoneID, if zone exists.
	 *throws exception if zone not available
	* 
	* @param	zoneId	ID of zone to get
	*/
	public Zone get(String zoneID)
	{
		if (!zoneExists(zoneID))
			throw new RuntimeException(String.format("Unknown Zone Access: %s", zoneID));
		return zones.get(zoneID);
	}
	
	/* checks if zone exists given a zone ID
	* 
	* @param	zoneId	ID of zone being checked
	*/
	public boolean zoneExists(String zoneID)
	{
		return zones.get(zoneID) != null;
	}
}
