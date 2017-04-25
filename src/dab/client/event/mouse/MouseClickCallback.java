package dab.client.event.mouse;

import java.util.ArrayList;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseClickCallback extends GLFWMouseButtonCallback implements M_Observable{

	private ArrayList<M_Observer> observers;
	
	public MouseClickCallback() {
		observers = new ArrayList<M_Observer>();
	}
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		notifyObservers(window, button, action, mods);
	}

	@Override
	public void registerObserver(M_Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void unregisterObserver(M_Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(long window, int button, int action, int mods) {
		for (M_Observer observer : observers) {
			observer.update(window, button, action, mods);
		}
	}

}
