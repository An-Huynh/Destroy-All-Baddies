package dab.server.logic.components;

import java.io.IOException;
import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.network.ClientConnection;

/**
 * This class is for syncing server and client side
 * data about Entities such as Players and Enemies.
 * If any Entity attribute has been changed, the Entity
 * is send to all clients to update their information.
 */
public class Updater implements Component
{
	/**
	 * Iterator through all players and enemies and write
	 * to clients all that have been modified by checking
	 * if entity.isDirty() evaluates to true
	 * If so, write the entity to each client to update
	 * their version's state
	 * 
	 * @author An Huynh
	 */
	@Override
	public void invoke()
	{
		Iterator<Player> players = Server.getPlayerList().getPlayerIterator();
		while (players.hasNext())
		{
			Player player = players.next();
			if (player.isDirty())
			{
				sendPlayerUpdateToAll(player);
				player.setDirty(false);
			}
		}
		
		Iterator<Enemy> enemies = Server.getEnemyList().getEnemyIterator();
		while (enemies.hasNext())
		{
			Enemy enemy = enemies.next();
			if (enemy.isDirty())
			{
				sendEnemyUpdateToAll(enemy);
				enemy.setDirty(false);
			}
		}
	}
	
	/**
	 * Write a Player Object to all clients
	 * 
	 * @param player
	 */
	private void sendPlayerUpdateToAll(Player player)
	{
		Iterator<ClientConnection> conns = Server.getClientManager().getConnectionIterator();
		while (conns.hasNext())
		{
			try
			{
				conns.next().sendPlayer(player);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Write an Enemy Object to all Clients
	 * 
	 * @param enemy
	 */
	private void sendEnemyUpdateToAll(Enemy enemy)
	{
		Iterator<ClientConnection> conns = Server.getClientManager().getConnectionIterator();
		while (conns.hasNext())
		{
			try
			{
				conns.next().sendEnemy(enemy);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
