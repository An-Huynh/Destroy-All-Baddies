package dab.client.logic;

import dab.client.graphic.DisplayWindow;
import dab.client.logic.component.Render;
import dab.client.logic.component.WindowDrawPost;
import dab.client.logic.component.WindowDrawPre;
import dab.client.state.GameState;

public class ClientLoopInitializer {
	private GameState gameState;
	private DisplayWindow display;
	
	private WindowDrawPre windowDrawPre;
	private Render render;
	private WindowDrawPost windowDrawPost;
	
	public ClientLoopInitializer(GameState gs, DisplayWindow display) {
		this.gameState = gs;
		this.display = display;
	}
	
	public void preInit() {
		windowDrawPre = new WindowDrawPre(display);
		render = new Render(gameState);
		windowDrawPost = new WindowDrawPost(display);
	}
	
	public void init() {
		GameLoop.registerTickable(windowDrawPre);
		GameLoop.registerTickable(render);
		GameLoop.registerTickable(windowDrawPost);
	}
}
