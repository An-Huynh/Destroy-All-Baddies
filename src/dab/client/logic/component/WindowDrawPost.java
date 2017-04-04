package dab.client.logic.component;

import dab.client.graphic.DisplayWindow;
import dab.common.loop.Tickable;

public class WindowDrawPost implements Tickable {
	private DisplayWindow display;
	
	public WindowDrawPost(DisplayWindow display) {
		this.display = display;
	}

	public void update() {
		display.swapBuffers();
	}
	
}
