package dab.server.logic.components;

import java.io.IOException;
import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.network.ClientConnection;

public class Updater implements Component
{
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
