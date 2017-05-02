package dab.client.listeners.mouse;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButtonCallback extends GLFWMouseButtonCallback implements MouseObservable
{
	private List<MouseObserver> observers;
	
	public MouseButtonCallback()
	{
		observers = new ArrayList<MouseObserver>();
	}
	
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
	
	@Override
	public void notifyObservers(long window, int button, int action, int mods)
	{
		for (MouseObserver observer : observers)
		{
			observer.invoke(window, button, action, mods);
		}
	}
}
