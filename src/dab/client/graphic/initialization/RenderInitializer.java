package dab.client.graphic.initialization;

import java.io.IOException;

import dab.client.graphic.drawable.zone.ZoneDrawable;
import dab.client.graphic.renderRegistry.RenderRegistry;

public class RenderInitializer {
	private static ZoneDrawable startZone;
	private static ZoneDrawable nightZone;
	private static ZoneDrawable graveyardZone;
	
	public static void preInit() throws IOException {
	    startZone = new ZoneDrawable("/resource/img/zone/start_zone.png");
	    nightZone = new ZoneDrawable("/resource/img/zone/night_zone.png");
	    graveyardZone = new ZoneDrawable("/resource/img/zone/graveyard_zone.png");
	}
	
	public static void init() {
	    RenderRegistry.register("dab:zone:start", startZone);
	    RenderRegistry.register("dab:zone:night", nightZone);
	    RenderRegistry.register("dab:zone:graveyard", graveyardZone);
	}
}
