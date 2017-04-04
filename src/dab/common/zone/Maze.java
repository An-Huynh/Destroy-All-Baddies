package dab.common.zone;

import dab.common.registry.TileRegistry;

public class Maze extends Zone {
	
	public Maze() {
		super();
		setName("dab:zone:maze");
	}
	
	public void setup() {
		/*
		for (int i = 0; i < 32; ++i) { // add top and bottom walls
			addTile(TileRegistry.get("dab:tile:wall"), i, 0);  // 0 is bottom layer
			addTile(TileRegistry.get("dab:tile:wall"), i, 23); // 23 is top layer
		}
		
		for (int i = 1; i < 23; ++i) { // add left and right walls
			addTile(TileRegistry.get("dab:tile:wall"), 0, i);  // 0 is left wall
			addTile(TileRegistry.get("dab:tile:wall"), 31, i); // 31 is right wall
		}
		*/
	}

}
