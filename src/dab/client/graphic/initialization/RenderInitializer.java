package dab.client.graphic.initialization;

import java.io.IOException;

import dab.client.graphic.drawable.zone.ZoneDrawable;
import dab.client.graphic.renderRegistry.RenderRegistry;

public class RenderInitializer {
	private static ZoneDrawable startZone;
	
	public static void preInit() throws IOException {
	    startZone = new ZoneDrawable("/resource/img/zone/start_zone.png");
	}
	
	public static void init() {
	    RenderRegistry.register("dab:zone:start", startZone);
	}
}
