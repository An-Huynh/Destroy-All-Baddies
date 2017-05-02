package dab.server.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.Player;
import dab.common.entity.attributes.Direction;
import dab.common.entity.attributes.JumpState;
import dab.common.physics.AABB;

public class PlayerList
{
	private Map<String, Player> players;
	
	public PlayerList()
	{
		players = new TreeMap<String, Player>();
	}
	
	public synchronized void add(Player player)
	{
		players.put(player.getName(), player);
	}
	
	public synchronized void addDefault(String clientName)
	{
		Player player = createDefaultPlayer(clientName);
		add(player);
	}
	
	public synchronized void remove(String clientName)
	{
		players.remove(clientName);
	}
	
	public synchronized void remove(Player player)
	{
		players.remove(player.getName());
	}
	
	public Player get(String clientName)
	{
		return players.get(clientName);
	}
	
	public Collection<Player> getPlayers()
	{
		return players.values();
	}
	
	public Iterator<Player> getPlayerIterator()
	{
		return players.values().iterator();
	}
	
	public Iterator<Player> getPlayerIteratorInZone(String zone)
	{
		return players.values().stream()
				      .filter(player -> player.getZoneID().equals(zone))
				      .iterator();
	}
	
	private Player createDefaultPlayer(String name)
	{
		Player player = new Player();
		player.setName(name);
		player.setZoneID("dab:zone:start");
		player.setJumpState(JumpState.GROUND);
		player.setDirection(Direction.NONE);
		player.setBoundingBox(new AABB(3.5f, 3.5f, 0.8f, 1.0f));
		player.setDirty(true);
		return player;
	}
}
