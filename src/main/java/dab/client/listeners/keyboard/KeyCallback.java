package dab.client.listeners.keyboard;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyCallback extends GLFWKeyCallback implements KeyboardObservable
{
	private List<KeyboardObserver> observers;
	private static boolean[] keys;
	
	public KeyCallback()
	{
		observers = new ArrayList<KeyboardObserver>();
		keys = new boolean[63356];
	}
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		keys[key] = (action != GLFW.GLFW_RELEASE);
		if (action != 2)
			notifyObservers(key, action, mods);
	}
	
	@Override
	public void notifyObservers(int key, int action, int mods)
	{
		observers.stream()
		         .forEach(observer -> observer.update(key, action, mods));
	}
	
	@Override
	public void registerObserver(KeyboardObserver observer)
	{
		observers.add(observer);
	}
	
	@Override
	public void unregisterObserver(KeyboardObserver observer)
	{
		observers.remove(observer);
	}
	
	public static boolean isKeyPressed(int key)
	{
		return keys[key];
	}
}