package dab.common.zone;

import dab.common.registry.TileRegistry;

public class StartZone extends Zone {
    
    public StartZone() {
        super();
        setName("dab:zone:start");
    }
    
    public void setup() {
        for (int i = 0; i < 32; ++i) {
            addTile(TileRegistry.get("dab:tile:solidblock"), i, 0); // bottom layer
        }
        for (int i = 0; i < 24; ++i) {
            addTile(TileRegistry.get("dab:tile:solidblock"), -1, i);
            addTile(TileRegistry.get("dab:tile:solidblock"), 32, i);
        }
    }

}
