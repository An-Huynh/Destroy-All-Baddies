package dab.server.network;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.joml.Vector2f;

import dab.common.entity.Player;
import dab.common.entity.attributes.Direction;
import dab.common.entity.attributes.JumpState;
import dab.server.Server;
import dab.server.events.ShootingEvent;
import dab.server.logic.CollisionMath;

public class ClientUpdater implements Runnable
{
	private ClientConnection conn;
	private Player player;
	private Map<String, Runnable> responses;
	
	public ClientUpdater(ClientConnection conn, Player player)
	{
		this.conn = conn;
		this.player = player;
		responses = new TreeMap<String, Runnable>();
		setupResponses();
	}
	
	@Override
	public void run()
	{
		System.out.println(String.format("Starting Updater for %s", player.getName()));
		boolean running = true;
		while (running)
		{
			try
			{
				String action = (String) conn.read();
				if (responses.containsKey(action))
				{
					responses.get(action).run();
				}
			}
			catch (ClassNotFoundException | IOException e)
			{
				running = false;
				conn.close();
				Server.getClientManager().removeConnection(player.getName());
			}
		}
		System.out.println(String.format("Ending Updater for %s", player.getName()));
	}
	
	private void setupResponses()
	{
		on("update.direction", () -> {
			try
			{
				player.setDirection((Direction) conn.read());
			}
			catch (ClassNotFoundException | IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		on("request.jump", () -> {
			if (!CollisionMath.futureGravityAABBSafe(player))
			{
				if (player.getJumpState().equals(JumpState.GROUND))
				{
					player.setJumpState(JumpState.ARC1);
				}
			}
		});
		
		on("update.live", () -> {
			// do nothing
		});
		
		on("event.shooting", () -> {
			try
			{
				Vector2f location = (Vector2f) conn.read();
				ShootingEvent shootingEvent = new ShootingEvent(player, location);
				Server.getEventQueue().push(shootingEvent);
			}
			catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	private void on(String action, Runnable response)
	{
		responses.put(action, response);
	}
}
