package dab.client.listeners.keyboard;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;


/**
 * This class is the observable that watches for KeyPresses. It contains a list
 * of observers, and also an array of booleans for all the keys pressed and whether
 * they are pressed.
 * It implements KeyboardObservable as well as extend GLFWKeyCallback.
 * GLFWKeyCallback implements an invoke method that will notify observers
 * and pass the key pressed.
 * 
 * @author Eli Irvin
 */
public class KeyCallback extends GLFWKeyCallback implements KeyboardObservable
{
	private List<KeyboardObserver> observers;
	private static boolean[] keys;
	
	/**
	 * Default constructor for KeyCallback
	 * Initializes a new ArrayList of observers as well as
	 * a new array of booleans for the status of the keys
	 */
	public KeyCallback()
	{
		observers = new ArrayList<KeyboardObserver>();
		keys = new boolean[63356];
	}
	
	/**
	 * Override of GLFWKeyCallback's invoke method.
	 * Sets the state of the key in the keys array to to
	 * true if the action isn't release and true if its press
	 * If the action isn't a repeated press, then it notifies
	 * all observers of the change
	 */
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods)
	{
		keys[key] = (action != GLFW.GLFW_RELEASE);
		if (action != 2)
			notifyObservers(key, action, mods);
	}

	/**
	 * Overrides the notify observers method of KeyboardObservable
	 * to call the update method of each observers in the observers
	 * List.
	 */
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
	
	/**
	 * Gets whether or not a key is currently pressed
	 * 
	 * @param key - the key that is being checked
	 * 
	 * @return True if the key is pressed | False otherwise
	 */
	public static boolean isKeyPressed(int key)
	{
		return keys[key];
	}
}