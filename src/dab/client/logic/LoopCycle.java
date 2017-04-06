package dab.client.logic;

import java.io.IOException;
import java.util.ArrayList;

import dab.common.loop.Tickable;

public class LoopCycle {
    
    private static final ArrayList<Tickable> components = new ArrayList<Tickable>();
    
    public void tick() throws IOException {
        for (Tickable tickable : components) {
            tickable.update();
        }
    }
    
    public static void registerTickable(Tickable tickable) {
        components.add(tickable);
    }
}
