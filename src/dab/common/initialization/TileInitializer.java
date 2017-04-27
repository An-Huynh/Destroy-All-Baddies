package dab.common.initialization;

import dab.common.registry.TileRegistry;
import dab.common.tile.MoveZoneBlock;
import dab.common.tile.SolidBlock;
import dab.common.tile.TeleporterTile;

public class TileInitializer {
	
	private static SolidBlock solidBlock;
	private static TeleporterTile teleporterTIle;
	
	public static void preInit() {
		solidBlock = new SolidBlock();
		teleporterTIle = new TeleporterTile();
	}
	
	public static void init() {
		TileRegistry.register(solidBlock);
		TileRegistry.register(teleporterTIle);
	}
}
