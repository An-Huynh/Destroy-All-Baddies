package dab.server.logic;

import dab.server.Server;
import dab.server.logic.components.EnemyBehavior;
import dab.server.logic.components.EventHandler;
import dab.server.logic.components.Movement;
import dab.server.logic.components.Updater;
import dab.server.logic.components.ZombieAttack;
import dab.server.logic.components.ZoneTransitions;

public class ComponentInitializer
{
	public static void initialize()
	{
		Server.getGameLoop().register(new EnemyBehavior());
		Server.getGameLoop().register(new Movement());
		Server.getGameLoop().register(new ZombieAttack());
		Server.getGameLoop().register(new EventHandler());
		Server.getGameLoop().register(new ZoneTransitions());
		Server.getGameLoop().register(new Updater());
	}
}
