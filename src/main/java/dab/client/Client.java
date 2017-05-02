package dab.client;

import java.io.IOException;

import dab.client.entities.EnemyList;
import dab.client.entities.PlayerList;
import dab.client.graphic.Display;
import dab.client.graphic.registry.RenderInitializer;
import dab.client.graphic.registry.RenderRegistry;
import dab.client.graphic.registry.RenderScalingInitializer;
import dab.client.graphic.registry.RenderScalingRegistry;
import dab.client.listeners.keyboard.KeyListener;
import dab.client.listeners.mouse.MouseListener;
import dab.client.logic.ComponentInitializer;
import dab.client.network.ServerConnectionManager;
import dab.common.logic.GameLoop;

public class Client implements Runnable
{
	private static PlayerList playerList;
	private static EnemyList enemyList;
	private static ServerConnectionManager serverConnManager;
	private static Display display;
	private static RenderRegistry renderRegistry;
	private static RenderScalingRegistry renderScalingRegistry;
	private static GameLoop gameLoop;
	private boolean running;
	
	private final double tickRate = 60.0;
	private final double deltaTime = 1000/tickRate;
	private double targetUpdateTime;
	
	private String clientName;
	private String hostName;
	private int port;
	
	public Client(String clientName, String hostName, int port)
	{
		this.clientName = clientName;
		this.hostName = hostName;
		this.port = port;
	}
	
	public static void main(String[] args)
	{
		Client client = new Client(args[0], args[1], 7720);
		Thread clientThread = new Thread(client);
		clientThread.start();
		while (true);
	}
	
	@Override
	public void run()
	{
		running = true;
		init();
		
		try
		{
			serverConnManager.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			running = false;
		}
		
		display.registerKeyListener(new KeyListener());
		display.registerMouseListener(new MouseListener());
		
		targetUpdateTime = System.currentTimeMillis();
		while (running)
		{
			display.pollEvents();
			display.clearBuffer();
			gameLoop.invokeAll();
			display.swapBuffers();
			syncTiming();
		}
		serverConnManager.stop();
		display.stop();
	}
	
	private void init()
	{
		playerList = new PlayerList();
		enemyList = new EnemyList();
		serverConnManager = new ServerConnectionManager(clientName, hostName, port);
		display = new Display();
		renderRegistry = new RenderRegistry();
		renderScalingRegistry = new RenderScalingRegistry();
		gameLoop = new GameLoop();
		
		display.start();
		
		RenderInitializer.initialize();
		RenderScalingInitializer.initialize();
		ComponentInitializer.initialize();
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
	
	public static PlayerList getPlayerList()
	{
		return playerList;
	}
	
	public static ServerConnectionManager getServerConnectionManager()
	{
		return serverConnManager;
	}
	
	public static RenderRegistry getRenderRegistry()
	{
		return renderRegistry;
	}
	
	public static GameLoop getGameLoop()
	{
		return gameLoop;
	}
	
	public static RenderScalingRegistry getRenderScalingRegistry()
	{
		return renderScalingRegistry;
	}
	
	public static EnemyList getEnemyList()
	{
		return enemyList;
	}
}
