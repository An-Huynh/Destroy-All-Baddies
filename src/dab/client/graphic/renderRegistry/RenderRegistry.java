package dab.client.graphic.renderRegistry;

import java.util.HashMap;
import java.util.Map;

import dab.client.graphic.drawable.Drawable;

public class RenderRegistry {
	
	private static final Map<String, Drawable> renderList = new HashMap<String, Drawable>();
	
	public static void register(String name, Drawable drawable) {
		renderList.put(name, drawable);
	}
	
	public static Drawable get(String name) {
		return renderList.get(name);
	}
}
