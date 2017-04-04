package dab.client.logic;

import java.util.ArrayList;

import dab.common.loop.Tickable;

public class GameLoop {
	
	private static final ArrayList<Tickable> components = new ArrayList<Tickable>();
	private boolean stop;
	
	public GameLoop() {
		this.stop = false;
	}
	
	public void stop() {
		this.stop = true;
	}
	
	
	private void tick() {
		for (int i = 0; i < components.size(); ++i) {
			components.get(i).update();
		}
	}
	
	public static void registerTickable(Tickable tickable) {
		components.add(tickable);
	}
	
	public void run() {
		final double tickRate = 60.0;
		final double deltaTime = 1000/tickRate;
		double targetUpdateTime = System.currentTimeMillis();
		
		while (!stop) {
			tick();
			targetUpdateTime += deltaTime;
			if (System.currentTimeMillis() < targetUpdateTime) {
				try {
					Thread.sleep((long) (targetUpdateTime - System.currentTimeMillis()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
