package dab.client.graphic.registry;

import java.util.Map;
import java.util.TreeMap;

import dab.client.graphic.drawable.StaticDrawable;
import dab.client.graphic.drawable.VariableDrawable;

public class RenderRegistry
{
	private Map<String, StaticDrawable> staticDrawList;
	private Map<String, VariableDrawable> variableDrawList;
	
	public RenderRegistry()
	{
		staticDrawList = new TreeMap<String, StaticDrawable>();
		variableDrawList = new TreeMap<String, VariableDrawable>();
	}
	
	public void register(String name, StaticDrawable drawable)
	{
		staticDrawList.put(name, drawable);
	}
	
	public void register(String name, VariableDrawable drawable)
	{
		variableDrawList.put(name, drawable);
	}
	
	public StaticDrawable getStatic(String name)
	{
		return staticDrawList.get(name);
	}
	
	public VariableDrawable getVariable(String name)
	{
		return variableDrawList.get(name);
	}
}
