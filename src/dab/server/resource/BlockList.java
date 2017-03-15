package dab.server.resource;

import java.util.ArrayList;

import dab.server.block.Block;

public class BlockList {
    public static ArrayList<Block> blockList;
    
    public BlockList() {
        blockList = new ArrayList<Block>();
    }
    
    public static void register(Block block) {
        blockList.add(block);
    }
}
