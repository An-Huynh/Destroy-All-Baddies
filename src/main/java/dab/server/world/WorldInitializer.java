package dab.server.world;

import org.joml.Vector2f;

import dab.common.entity.Zombie;
import dab.server.Server;

public class WorldInitializer
{
	private static int zombieCounter = 0;
	
	
	public static void initialize()
	{
		addZombie("dab:zone:graveyard", 22, 1);
		addZombie("dab:zone:hell", 5, 5);
		addZombie("dab:zone:hell", 6, 6);
		addZombie("dab:zone:hell", 7, 7);
	}
	
	public static void addZombie(String zoneID, Vector2f location)
	{
		addZombie(zoneID, location.x, location.y);
	}
	
	public static void addZombie(String zoneID, float x, float y)
	{
		Zombie zombie = createZombie();
		zombie.setLocation(x, y);
		zombie.setZoneID(zoneID);
		Server.getEnemyList().add(zombie);
	}
	
	public static Zombie createZombie()
	{
		Zombie zombie = new Zombie();
		zombie.setName(String.format("zombie-%d", zombieCounter++));
		return zombie;
	}
}
