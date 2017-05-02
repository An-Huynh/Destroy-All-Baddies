package dab.client.graphic.registry;

import dab.client.Client;
import dab.client.graphic.drawable.EntityDrawable;
import dab.client.graphic.drawable.ZoneDrawable;

public class RenderInitializer
{
	public static void initialize()
	{
		Client.getRenderRegistry().register("dab:zone:start", new ZoneDrawable("/images/zone/start_zone.png"));
		Client.getRenderRegistry().register("dab:zone:night", new ZoneDrawable("/images/zone/night_zone.png"));
		Client.getRenderRegistry().register("dab:zone:graveyard", new ZoneDrawable("/images/zone/graveyard_zone.png"));
		Client.getRenderRegistry().register("dab:zone:home", new ZoneDrawable("/images/zone/home_zone.png"));
		Client.getRenderRegistry().register("dab:zone:street", new ZoneDrawable("/images/zone/street_zone.png"));
		Client.getRenderRegistry().register("dab:zone:hell", new ZoneDrawable("/images/zone/hell_zone.png"));
		Client.getRenderRegistry().register("dab:zone:hellexit", new ZoneDrawable("/images/zone/hellexit_zone.png"));
		
		Client.getRenderRegistry().register("dab:player", new EntityDrawable("/images/player/player.png"));
		Client.getRenderRegistry().register("dab:enemy:zombie", new EntityDrawable("/images/enemy/zombie.png"));
	}
}