package dab.server.logic;

import dab.server.logic.component.HelloTicker;

public class LoopComponentInitializer {

	private static HelloTicker helloTicker;
	
	public static void preInit() {
		helloTicker = new HelloTicker();
	}
	
	public static void init() {
		GameLoop.registerTickable(helloTicker);
	}
	
}
