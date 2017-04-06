package dab.common.initialization;

import dab.common.registry.TileRegistry;
import dab.common.tile.SolidBlock;

public class TileInitializer {
	
	private static SolidBlock solidBlock;
	
	public static void preInit() {
		solidBlock = new SolidBlock();
	}
	
	public static void init() {
		TileRegistry.register(solidBlock);
	}
}
