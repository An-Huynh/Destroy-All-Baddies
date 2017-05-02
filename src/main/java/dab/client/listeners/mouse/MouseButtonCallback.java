package dab.client.listeners.mouse;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

/**
 * This class is the observable that watches out for mouse clicks and contains all the
 * observers waiting for one. It implements MouseObservable which contains the register,
 * unregister, and notify methods for observers. It also extends GLFWMouseButtonCallback 
 * which contains the invoke method.
 *
 * @author Eli Irvin
 */
public class MouseButtonCallback extends GLFWMouseButtonCallback implements MouseObservable
{
	private List<MouseObserver> observers;
	
	/**
	 * Default Constructor for MouseButtonCallback.
	 * Initializes the observers ArrayList
	 */
	public MouseButtonCallback()
	{
		observers = new ArrayList<MouseObserver>();
	}
	
	/**
	 * Override of the invoke method from GLFWMouseButtonCallback.
	 * Simply notifies all observers of the observers of a click.
	 */
	@Override
	public void invoke(long window, int button, int action, int mods)
	{
		notifyObservers(window, button, action, mods);
	}
	
	@Override
	public void registerObserver(MouseObserver observer)
	{
		observers.add(observer);
	}
	
	@Override
	public void unregisterObserver(MouseObserver observer)
	{
		observers.remove(observer);
	}
	
	/**
	 * For each observer in the observers List, calls the observers invoke method.
	 */
	@Override
	public void notifyObservers(long window, int button, int action, int mods)
	{
		for (MouseObserver observer : observers)
		{
			observer.invoke(window, button, action, mods);
		}
	}
}
