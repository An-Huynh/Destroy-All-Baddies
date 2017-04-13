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
	
	boolean running;
	
	public GameServer(int port) {
	    running = false;
		this.port = port;
		playerList = new PlayerList();
		socketManager = new SocketManager(playerList);
		gameLoop = new GameLoop();
	}
	
	public void preInit() {
		TileInitializer.preInit();
		ZoneInitializer.preInit();
		LoopComponentInitializer.preInit(socketManager, playerList);
	}
	
	public void init() {
		TileInitializer.init();
		ZoneInitializer.init();
		LoopComponentInitializer.init();
	}
	
	public void postInit() throws IOException, InterruptedException {
		socketManager.start(port);
	}
	
	@Override
	public void run() {
	    running = true;
	    try {
	        preInit();
	        init();
	        postInit();
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	        running = false;
	    }
	    
	    final double tickRate = 60.0;
	    final double deltaTime = 1000/tickRate;
	    double targetUpdateTime = System.currentTimeMillis();
	    while (running) {
	        try {
                gameLoop.tickAll();
            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }
	        targetUpdateTime += deltaTime;
            double now = System.currentTimeMillis();
            if (now < targetUpdateTime) {
                try {
                    Thread.sleep((long) (targetUpdateTime - now));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
            }
	    }

	    System.out.println("stopping");
	    
	    try {
			socketManager.stop();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		running = false;
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
