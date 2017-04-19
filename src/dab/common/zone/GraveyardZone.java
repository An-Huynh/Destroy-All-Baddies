package dab.common.zone;

import dab.common.registry.TileRegistry;

public class GraveyardZone  extends Zone {
	public GraveyardZone() {
        super();
        setName("dab:zone:graveyard");
        setLeftZone("dab:zone:night");
        setRightZone("");
    }
    
    public void setup() {
        for (int i = 0; i < 32; ++i) {
            addTile(TileRegistry.get("dab:tile:solidblock"), i, 0); // bottom layer
        }
        for (int i = 0; i < 24; ++i) {
            addTile(TileRegistry.get("dab:tile:movezoneblock"), -1, i);
            addTile(TileRegistry.get("dab:tile:solidblock"), 32, i);
        }
        addTile(TileRegistry.get("dab:tile:solidblock"), 8, 1);
        addTile(TileRegistry.get("dab:tile:solidblock"), 8, 2);
        addTile(TileRegistry.get("dab:tile:solidblock"), 11, 1);
        addTile(TileRegistry.get("dab:tile:solidblock"), 11, 2);
        addTile(TileRegistry.get("dab:tile:solidblock"), 13, 1);
        addTile(TileRegistry.get("dab:tile:solidblock"), 13, 2);
        addTile(TileRegistry.get("dab:tile:solidblock"), 14, 1);
        addTile(TileRegistry.get("dab:tile:solidblock"), 14, 2);
    }
}