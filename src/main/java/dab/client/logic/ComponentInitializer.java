package dab.client.logic;

import dab.client.Client;
import dab.client.logic.components.Render;

public class ComponentInitializer
{
	public static void initialize()
	{
		Client.getGameLoop().register(new Render());
	}
}
