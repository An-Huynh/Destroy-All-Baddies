package dab.client.graphic.registry;

import dab.client.Client;
/**
 * initializes dimensions of each zone
 * 
 * @author Cristopher Huerta
 */
public class RenderScalingInitializer
{
	/**
	* registers the block dimensions of
	* each zone given zone id and dimension data
	*/
	public static void initialize()
	{
		Client.getRenderScalingRegistry().register("dab:zone:start", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:night", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:graveyard", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:cave", 12, 9);
		Client.getRenderScalingRegistry().register("dab:zone:hell", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:hellexit", 32, 24);
		Client.getRenderScalingRegistry().register("dab:zone:street", 32, 24);
	}
}
