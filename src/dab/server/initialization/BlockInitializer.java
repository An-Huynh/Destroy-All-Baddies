package dab.server.initialization;

import dab.server.block.Dirt;
import dab.server.resource.BlockList;

public class BlockInitializer {
    private Dirt dirt;
    
    public void preInit() {
        dirt = new Dirt();
    }
    
    public void init() {
        BlockList.register(dirt);
    }
}
