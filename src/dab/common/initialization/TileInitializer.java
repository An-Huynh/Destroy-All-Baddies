package dab.common.initialization;

import dab.common.registry.TileRegistry;
import dab.common.tile.Wall;

public class TileInitializer {
	
	private static Wall wall;
	
	public static void preInit() {
		wall = new Wall();
	}
	
	public static void init() {
		TileRegistry.register(wall);
	}
}
