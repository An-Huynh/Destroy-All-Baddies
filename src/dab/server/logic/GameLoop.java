package dab.server.logic;

import java.io.IOException;
import java.util.ArrayList;

import dab.server.logic.component.Tickable_S;

public class GameLoop {
	
	private static ArrayList<Tickable_S> components = new ArrayList<Tickable_S>();
	
	public void tickAll() throws IOException {
	    for (Tickable_S tickable : components) {
	        tickable.invoke();
	    }
	}
	
	public static void registerTickable(Tickable_S tickable) {
		components.add(tickable);
	}
	
}
