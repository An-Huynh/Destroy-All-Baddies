package dab.common.zone;

import dab.common.registry.TileRegistry;

public class NightZone  extends Zone {
	public NightZone() {
        super();
        setName("dab:zone:night");
        setLeftZone("dab:zone:start");
        setRightZone("dab:zone:graveyard");
    }
    
    public void setup() {
        for (int i = 0; i < 32; ++i) {
            addTile(TileRegistry.get("dab:tile:solidblock"), i, 0); // bottom layer
        }
        for (int i = 0; i < 24; ++i) {
            addTile(TileRegistry.get("dab:tile:movezoneblock"), -1, i);
            addTile(TileRegistry.get("dab:tile:movezoneblock"), 32, i);
        }
    }
}