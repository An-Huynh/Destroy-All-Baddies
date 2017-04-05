package dab.server;

import java.io.IOException;
import java.util.Scanner;

import dab.common.initialization.TileInitializer;
import dab.common.initialization.ZoneInitializer;
import dab.server.logic.GameLoop;
import dab.server.logic.LoopComponentInitializer;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class GameServer implements Runnable {

	private SocketManager socketManager;
	private GameLoop gameLoop;
	private PlayerList playerList;
	private int port;
	
	public GameServer(int port) {
		this.port = port;
		
		playerList = new PlayerList();
		socketManager = new SocketManager(playerList);
		gameLoop = new GameLoop(playerList);
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
	}
	
	public void postInit() throws IOException, InterruptedException {
		socketManager.start(port);
		gameLoop.run();
	}
	
	@Override
	public void run() {
		preInit();
		init();
		try {
			postInit();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		gameLoop.stop();
		playerList.stop();
		socketManager.stop();
	}
	
	public static void main(String[] args) {
		GameServer gs = new GameServer(7720);
		Thread gameThread = new Thread(gs);
		gameThread.start();
		
		Scanner in = new Scanner(System.in);
		while (!in.nextLine().equals("/stop")) {}
		in.close();
		gs.stop();
	}
	
	
	
}
