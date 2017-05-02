package dab.server.entity;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dab.common.entity.Enemy;
import dab.server.Server;
import dab.server.network.ClientConnection;

public class EnemyList
{
	private Map<String, Enemy> enemies;
	
	public EnemyList()
	{
		enemies = new TreeMap<String, Enemy>();
	}
	
	public synchronized void add(Enemy enemy)
	{
		enemies.put(enemy.getName(), enemy);
	}
	
	public Enemy get(String name)
	{
		return enemies.get(name);
	}
	
	public synchronized void remove(String name)
	{
		enemies.remove(name);
		Iterator<ClientConnection> connIterator = Server.getClientManager().getConnectionIterator();
		while (connIterator.hasNext())
		{
			try
			{
				ClientConnection conn = connIterator.next();
				conn.write("enemy.removal");
				conn.write(name);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public Iterator<Enemy> getEnemyIterator()
	{
		return enemies.values().iterator();
	}
	
	public Collection<Enemy> getEnemies()
	{
		return enemies.values();
	}
	
	public Iterator<Enemy> getUpdatableEnemyIterator()
	{
		List<String> zonesWithPlayer = Server.getPlayerList().getPlayers()
				                             .stream()
				                             .filter(player -> !player.getZoneID().equals(""))
				                             .map(player -> player.getZoneID())
				                             .collect(Collectors.toList());
		return enemies.values().stream()
				      .filter(enemy -> zonesWithPlayer.contains(enemy.getZoneID()))
				      .collect(Collectors.toList())
				      .iterator();
	}
}
