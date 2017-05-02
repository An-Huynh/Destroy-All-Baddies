package dab.client.network;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import dab.client.Client;
import dab.common.entity.Enemy;
import dab.common.entity.Player;

public class ServerUpdater implements Runnable
{
	private ServerConnection conn;
	private Map<String, Runnable> responses;
	private boolean running;
	
	public ServerUpdater(ServerConnection conn)
	{
		this.conn = conn;
		responses = new TreeMap<String, Runnable>();
		setupResponses();
	}
	
	@Override
	public void run()
	{
		running = true;
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
			}
		}
	}
	
	public void stop()
	{
		running = false;
	}
	
	private void setupResponses()
	{
		on("update.player", () -> {
			try
			{
				Player player = (Player) conn.read();
				String name = player.getName();
				if (name.equals(Client.getServerConnectionManager().getClientName()))
				{
					Client.getPlayerList().setMainPlayer(player);
				}
				else
				{
					if (Client.getPlayerList().hasPlayer(name))
					{
						Client.getPlayerList().get(name).copy(player);
					}
					else
					{
						Client.getPlayerList().add(player);
					}
				}
			}
			catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		});
		
		on("update.enemy", () -> {
			try
			{
				Enemy enemy = (Enemy) conn.read();
				if (Client.getEnemyList().hasEnemy(enemy))
				{
					Client.getEnemyList().get(enemy.getName()).copy(enemy);
				}
				else
				{
					Client.getEnemyList().add(enemy);
				}
			}
			catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		});
		
		on("request.name", () -> {
			try
			{
				conn.write(Client.getServerConnectionManager().getClientName());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		
		on("request.live", () -> {
			try
			{
				conn.write("update.live");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
		
		on("player.removal", () -> {
			try
			{
				String playerName = (String) conn.read();
				Client.getPlayerList().remove(playerName);
			}
			catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
		});
		
		on("enemy.removal", () -> {
			try
			{
				String enemyName = (String) conn.read();
				Client.getEnemyList().remove(enemyName);
			}
			catch (IOException | ClassNotFoundException e)
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
