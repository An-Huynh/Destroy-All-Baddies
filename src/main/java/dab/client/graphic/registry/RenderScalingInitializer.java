package dab.client.graphic.registry;

import dab.client.Client;

public class RenderScalingInitializer
{
	public static void initialize()
	{
		Client.getRenderScalingRegistry().register("dab:zone:start", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:night", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:graveyard", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:home", 12, 9);
		Client.getRenderScalingRegistry().register("dab:zone:hell", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:hellexit", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:street", 32, 24);
	}
}
