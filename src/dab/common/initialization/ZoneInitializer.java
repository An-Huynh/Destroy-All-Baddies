package dab.common.initialization;

import dab.common.registry.ZoneRegistry;
import dab.common.zone.Maze;

public class ZoneInitializer {
	
	private static Maze maze;
	
	public static void preInit() {
		maze = new Maze();
	}
	
	public static void init() {
		maze.setup();
		ZoneRegistry.register(maze);
	}
}
