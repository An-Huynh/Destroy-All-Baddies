package dab.client.graphic.renderInitialization;

import java.io.IOException;

import dab.client.graphic.drawable.zone.ZoneDrawable;
import dab.client.graphic.renderRegistry.RenderRegistry;

public class RenderInitializer {
	private static ZoneDrawable maze;
	
	public static void preInit() throws IOException {
		maze = new ZoneDrawable("/resource/zone/img/maze.png");
	}
	
	public static void init() {
		RenderRegistry.register("dab:zone:maze", maze);
	}
}
