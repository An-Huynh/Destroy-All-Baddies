package dab.client;

import java.io.IOException;
import java.util.Scanner;

import dab.client.graphic.DisplayWindow;
import dab.client.graphic.renderInitialization.RenderInitializer;
import dab.client.handler.DirectionListener;
import dab.client.logic.ClientLoopInitializer;
import dab.client.logic.GameLoop;
import dab.client.network.ClientUpdater;
import dab.client.network.ServerConnection;
import dab.client.state.GameState;

public class GameClient implements Runnable {
	
	private DisplayWindow display;
	private ServerConnection conn;
	private GameState gameState;
	private ClientUpdater clientUpdater;
	private GameLoop gameLoop;
	private ClientLoopInitializer cli;
	
	private String host;
	private int port;
	
	public GameClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	private void preInit() {
			this.display = new DisplayWindow();
			this.conn = new ServerConnection();
			this.gameState = new GameState();
			this.clientUpdater = new ClientUpdater(gameState, conn);
			this.cli = new ClientLoopInitializer(gameState, display);
	}
	
	private void init() throws IOException, ClassNotFoundException {
		display.start();
		
		RenderInitializer.preInit();
		RenderInitializer.init();
		
		conn.connect(host, port);
		clientUpdater.sync();
		
		Thread clientUpdateThread = new Thread(clientUpdater);
		clientUpdateThread.start();
		
		display.registerKeyListener(new DirectionListener(gameState));
		
		gameLoop = new GameLoop();
		cli.preInit();
		cli.init();
	}
	
	
	public void run() {
		preInit();
		try {
			init();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		gameLoop.run();
	}
	
	public void stop() {
		gameLoop.stop();
		clientUpdater.stop();
		display.stop();
		conn.close();
	}
	
	
	public static void main(String[] args) {
		GameClient gc = new GameClient("localhost", 7720);
		Thread gc_thread = new Thread(gc);
		gc_thread.start();
		
		Scanner in = new Scanner(System.in);
		while (!in.nextLine().equals("/stop")) {}
		gc.stop();
		in.close();
	}
	
}
