package dab.client.event.keyboard;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyCallback extends GLFWKeyCallback implements K_Observable {

	private ArrayList<K_Observer> observers;
	private static boolean[] keys = new boolean[65536];
	
	public KeyCallback() {
		observers = new ArrayList<K_Observer>();
		for (int i = 0; i < keys.length; ++i) {
		    keys[i] = false;
		}
	}
	
	public void invoke(long window, int key, int scancode, int action, int mods) {
	    keys[key] = action != GLFW.GLFW_RELEASE;
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
	
	public static boolean isKeyDown(int keycode) {
	    return keys[keycode];
	}
	
}