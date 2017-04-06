package dab.common.initialization;

import dab.common.registry.ZoneRegistry;
import dab.common.zone.StartZone;

public class ZoneInitializer {
	
    private static StartZone startZone;
	
    public static void preInit() {
        startZone = new StartZone();
    }
	
    public static void init() {
        startZone.setup();
        ZoneRegistry.register(startZone);
    }
}
