package dab.common.logic;

import java.util.ArrayList;
import java.util.List;

public class GameLoop
{
	private List<Component> components;
	
	public GameLoop()
	{
		components = new ArrayList<Component>();
	}
	
	public void invokeAll()
	{
		components.forEach(component -> component.invoke());
	}
	
	public void register(Component component)
	{
		components.add(component);
	}
}
