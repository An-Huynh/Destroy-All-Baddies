package dab.client.logic.components;

import java.util.Iterator;

import org.joml.Vector2i;

import dab.client.Client;
import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.logic.Component;

public class Render implements Component
{
	@Override
	public void invoke()
	{
		String zone = Client.getPlayerList().getMainPlayer().getZoneID();
		if (!zone.equals(""))
		{
			renderZone();
			renderEnemies();
			renderRemotePlayers();
			renderMainPlayer();
		}
	}	
	
	private void renderZone()
	{
		String zone = Client.getPlayerList().getMainPlayer().getZoneID();
		Client.getRenderRegistry().getStatic(zone).render();
	}
	
	private void renderEnemies()
	{
		String zoneID = Client.getPlayerList().getMainPlayer().getZoneID();
		Vector2i scaler = Client.getRenderScalingRegistry().get(zoneID);
		
		Iterator<Enemy> enemies = Client.getEnemyList().getEnemyIterator();
		while (enemies.hasNext())
		{
			Enemy enemy = enemies.next();
			if (enemy.getZoneID().equals(zoneID))
			{
				Client.getRenderRegistry().getVariable(enemy.getClassName())
				      .render(enemy.getBoundingBox().getMidPoint(), scaler);
			}
		}
	}
	
	private void renderRemotePlayers()
	{
		String zoneID = Client.getPlayerList().getMainPlayer().getZoneID();
		Vector2i scaler = Client.getRenderScalingRegistry().get(zoneID);
		
		Iterator<Player> remotePlayers = Client.getPlayerList().getPlayerIterator();
		while (remotePlayers.hasNext())
		{
			Player player = remotePlayers.next();
			if (player.getZoneID().equals(zoneID))
			{
				Client.getRenderRegistry().getVariable("dab:player")
			      .render(player.getBoundingBox().getMidPoint(), scaler);
			}
		}
	}
	
	private void renderMainPlayer()
	{
		String zoneID = Client.getPlayerList().getMainPlayer().getZoneID();
		Vector2i scaler = Client.getRenderScalingRegistry().get(zoneID);
		
		Client.getRenderRegistry().getVariable("dab:player")
		       .render(Client.getPlayerList().getMainPlayer().getBoundingBox().getMidPoint(), scaler);
	}
}
