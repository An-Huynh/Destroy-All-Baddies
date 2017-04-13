package dab.common.initialization;

import dab.common.registry.ZoneRegistry;
import dab.common.zone.GraveyardZone;
import dab.common.zone.NightZone;
import dab.common.zone.StartZone;

public class ZoneInitializer {
	
    private static StartZone startZone;
    private static NightZone nightZone;
    private static GraveyardZone graveyardZone;
	
    public static void preInit() {
        startZone = new StartZone();
        nightZone = new NightZone();
        graveyardZone = new GraveyardZone();
    }
	
    public static void init() {
        startZone.setup();
        nightZone.setup();
        graveyardZone.setup();
        ZoneRegistry.register(startZone);
        ZoneRegistry.register(nightZone);
        ZoneRegistry.register(graveyardZone);
    }
}
