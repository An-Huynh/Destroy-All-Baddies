package dab.server;

import java.io.IOException;

import dab.common.event.EventQueue;
import dab.common.logic.GameLoop;
import dab.server.entity.EnemyList;
import dab.server.entity.PlayerList;
import dab.server.logic.ComponentInitializer;
import dab.server.network.ClientManager;
import dab.server.world.WorldInitializer;
import dab.server.world.tile.TileRegistry;
import dab.server.world.zone.ZoneRegistry;

public class Server implements Runnable
{
	private static Server instance = null;
	
	private static TileRegistry tileRegistry;
	private static ZoneRegistry zoneRegistry;
	private static PlayerList playerList;
	private static EnemyList enemyList;
	private static ClientManager clientManager;
	private static EventQueue eventQueue;
	private static GameLoop gameLoop;
	private boolean running;
	
	private final double tickRate = 60.0;
	private final double deltaTime = 1000/tickRate;
	private double targetUpdateTime;
	
	public static void main(String[] args)
	{	
		Server server = Server.getInstance();
		Thread serverThread = new Thread(server);
		serverThread.start();
		while (true);
	}
	
	public static Server getInstance()
	{
		if (instance == null)
		{
			instance = new Server();
		}
		return instance;
	}
	
	@Override
	public void run()
	{
		System.out.println("Starting Server");
		running = true;
		init();
		
		try
		{
			clientManager.start(7720);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			running = false;
		}
		
		targetUpdateTime = System.currentTimeMillis();
		while (running)
		{
			gameLoop.invokeAll();
			syncTiming();
		}
		System.out.println("Stopping Server");
		clientManager.stop();
	}
	
	public void stop()
	{
		running = false;
		clientManager.stop();
	}
	
	private void syncTiming()
	{
		double now = System.currentTimeMillis();
		if (targetUpdateTime > now)
		{
			try
			{
				Thread.sleep((long) (targetUpdateTime - now));
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		targetUpdateTime += deltaTime;
	}
	
	private void init()
	{
		System.out.println("Initializing Server");
		playerList = new PlayerList();
		enemyList = new EnemyList();
		clientManager = new ClientManager();
		eventQueue = new EventQueue();
		
		tileRegistry = new TileRegistry();
		zoneRegistry = new ZoneRegistry();
		
		WorldInitializer.initialize();
		
		gameLoop = new GameLoop();
		ComponentInitializer.initialize();
	}
	
	
	// Accessors
	public static TileRegistry getTileRegistry()
	{
		return tileRegistry;
	}
	
	public static ZoneRegistry getZoneRegistry()
	{
		return zoneRegistry;
	}
	
	public static PlayerList getPlayerList()
	{
		return playerList;
	}
	
	public static EnemyList getEnemyList()
	{
		return enemyList;
	}
	
	public static ClientManager getClientManager()
	{
		return clientManager;
	}
	
	public static GameLoop getGameLoop()
	{
		return gameLoop;
	}
	
	public static EventQueue getEventQueue()
	{
		return eventQueue;
	}
}
