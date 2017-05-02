package dab.client.graphic.registry;

import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2i;

public class RenderScalingRegistry
{
	private Map<String, Vector2i> scalers;
	
	public RenderScalingRegistry()
	{
		scalers = new TreeMap<String, Vector2i>();
	}
	
	public void register(String name, Vector2i scale)
	{
		scalers.put(name, scale);
	}
	
	public void register(String name, int width, int height)
	{
		scalers.put(name, new Vector2i(width, height));
	}
	
	public Vector2i get(String name)
	{
		return scalers.get(name);
	}
}
