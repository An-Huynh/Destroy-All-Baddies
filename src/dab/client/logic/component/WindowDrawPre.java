package dab.client.logic.component;

import dab.client.graphic.DisplayWindow;
import dab.common.loop.Tickable;

public class WindowDrawPre implements Tickable {
	
	private DisplayWindow display;
	
	public WindowDrawPre(DisplayWindow display) {
		this.display = display;
	}

	public void update() {
		display.clearBuffer();
		display.pollEvents();
	}
}
