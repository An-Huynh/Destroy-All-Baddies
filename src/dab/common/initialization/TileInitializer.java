package dab.common.initialization;

import dab.common.registry.TileRegistry;
import dab.common.tile.MoveZoneBlock;
import dab.common.tile.SolidBlock;

public class TileInitializer {
	
	private static SolidBlock solidBlock;
	private static MoveZoneBlock moveZoneBlock;
	
	public static void preInit() {
		solidBlock = new SolidBlock();
		moveZoneBlock = new MoveZoneBlock();
	}
	
	public static void init() {
		TileRegistry.register(solidBlock);
		TileRegistry.register(moveZoneBlock);
	}
}
