package dab.server.logic;

import java.util.ArrayList;

import dab.server.logic.component.Tickable_S;
import dab.server.players.PlayerList;

public class GameLoop {
	
	private static ArrayList<Tickable_S> components = new ArrayList<Tickable_S>();
	private PlayerList playerList;
	
	private boolean running;
	
	public GameLoop(PlayerList playerList) {
		this.playerList = playerList;
		running = false;
	}
	
	public void run() throws InterruptedException {
		running = true;
		final double tickRate = 30.0;
		final double deltaTime = 1000/tickRate;
		double targetUpdateTime = System.currentTimeMillis();
		while (running) {
			tickAll();
			targetUpdateTime += deltaTime;
			if (System.currentTimeMillis() < targetUpdateTime) {
				Thread.sleep((long) (targetUpdateTime - System.currentTimeMillis()));
				
			}
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public void tickAll() {
		for (Tickable_S tickable : components) {
			tickable.update(playerList);
		}
	}
	
	public static void registerTickable(Tickable_S tickable) {
		components.add(tickable);
	}
	
}
