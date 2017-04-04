package dab.client.event.keyboard;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyCallback extends GLFWKeyCallback implements K_Observable {

	private ArrayList<K_Observer> observers;
	
	public KeyCallback() {
		observers = new ArrayList<K_Observer>();
	}
	
	public void invoke(long window, int key, int scancode, int action, int mods) {
		notifyObservers(key, action, mods);
	}
	
	public void registerObserver(K_Observer observer) {
		observers.add(observer);
	}
	
	public void unregisterObserver(K_Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyObservers(int key, int action, int mods) {
		for (K_Observer observer : observers) {
			observer.update(key, action, mods);
		}
	}
	
}
