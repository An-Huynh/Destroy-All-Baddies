package dab.client.logic.component;

import dab.client.graphic.drawable.Drawable;
import dab.client.graphic.renderRegistry.RenderRegistry;
import dab.client.state.GameState;
import dab.common.loop.Tickable;

public class Render implements Tickable {
	
	private GameState gs;
	
	public Render(GameState gs) {
		this.gs = gs;
	}
	
	public void update() {
		System.out.println(gs.getZone());
		
		System.out.println(gs.getPlayerLocation().toString());
		
		
		Drawable zd = RenderRegistry.get(gs.getZone());
		if (zd != null) {
			zd.render();
		}
	}
}
