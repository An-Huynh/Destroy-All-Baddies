package dab.server.network;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketLifeChecker implements Runnable {
	
	private SocketManager socketManager;
	private ThreadPoolExecutor executor;
	private boolean running;
	
	public SocketLifeChecker(SocketManager socketManager) {
		this.socketManager = socketManager;
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			Iterator<String> connectionKeys = socketManager.getKeyIterator();
			while (connectionKeys.hasNext()) {
				String key = connectionKeys.next();
				executor.execute(new Runnable() {
					public void run() {
						ClientConnection conn = socketManager.getConnection(key);
						try {
							conn.setTimeout(20000);
							conn.writeObject("request.heartbeat");
						} catch (IOException e) {
							//e.printStackTrace();
							socketManager.removeConnection(key);
							running = false;
						}
					}
				});
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
		}
		
		
		
		
		/*
		
		
		
		
		
		
		
		System.out.println("Starting SocketLifeChecker thread");
		running = true;
		while (running) {
			// check each connection
			Iterator<String> connectionKeys = socketManager.getKeyIterator();
			while (connectionKeys.hasNext()) {
				String key = connectionKeys.next();
				executor.execute(new Runnable() {
					public void run() {
						System.out.println("checking");
						ClientConnection conn = socketManager.getConnection(key);
						try {
							conn.writeObject("sever.send.check");
							conn.setTimeout(3000);
						} catch (IOException e) {
							e.printStackTrace();
							socketManager.removeConnection(key);
							System.out.println("removing");
						}
						
						/*
						System.out.println("STARTING CHECK");
						ClientConnection conn = socketManager.getConnection(key);
						try {
							conn.writeObject("server.send.check");
							conn.setTimeout(3000);
							System.out.println("acquring");
							synchronized(conn.getIn()) {
								System.out.println("slc - acquired");
								
								String message = (String) conn.readObject();
								if (message.equals("client.send.live")) {
									System.out.println("Messge fine");
								}
								System.out.println("reading - done");
								conn.getIn().notifyAll();
							}
						} catch (IOException | ClassNotFoundException e) {
							System.out.println("ERROR");
							e.printStackTrace();
							socketManager.removeConnection(key);
						}
						
					}
				});
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
		}
		*/
		executor.shutdownNow();
		System.out.println("Stopping SocketLifeChecker Thread");
	}
	
	public void stop() {
		running = false;
	}
	
}
