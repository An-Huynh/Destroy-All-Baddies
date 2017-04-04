package dab.server;

import java.util.Scanner;

import dab.common.initialization.TileInitializer;
import dab.common.initialization.ZoneInitializer;
import dab.server.logic.GameLoop;
import dab.server.logic.LoopComponentInitializer;
import dab.server.network.SocketServer;

public class GameServer implements Runnable {
	
	private int port;
	private GameLoop gameLoop;
	private SocketServer socketServer;
	
	
	public GameServer(int port) {
		this.port = port;
	}
		
	public void preInit() {
		TileInitializer.preInit();
		ZoneInitializer.preInit();
		LoopComponentInitializer.preInit();
	}
	
	public void init() {
		TileInitializer.init();
		ZoneInitializer.init();
		LoopComponentInitializer.init();
		
		socketServer = new SocketServer(port);
		gameLoop = new GameLoop();
	}
	
	public void postInit() {
		Thread socketServerThread = new Thread(socketServer);
		socketServerThread.start();
		
		gameLoop.run();
	}
	
	public void run() {
		preInit();
		init();
		postInit();
	}
	
	public void stop() {
		gameLoop.stop();
	}
	
	public static void main(String[] args) {
		System.out.println("Starting...");
		GameServer server = new GameServer(7720);
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		Scanner in = new Scanner(System.in);
		while (!in.nextLine().equals("/stop")) {}
		server.stop();
		in.close();
		
		System.out.println("Stopping...");
	}
		
}
