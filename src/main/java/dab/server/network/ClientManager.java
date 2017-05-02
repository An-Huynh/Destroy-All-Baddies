package dab.server.network;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import dab.server.Server;

public class ClientManager
{
	private Map<String, ClientConnection> clients;
	private ClientAcceptor clientAcceptor;
	private ThreadPoolExecutor executor;
	private ClientLifeChecker clientChecker;
	
	public ClientManager()
	{
		clients = new TreeMap<String, ClientConnection>();
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		clientChecker = new ClientLifeChecker();
		Thread clientCheckerThread = new Thread(clientChecker);
		clientCheckerThread.start();
	}
	
	public void start(int port) throws IOException
	{
		startClientAcceptor(port);
	}
	
	public void stop()
	{
		clientChecker.stop();
		clientAcceptor.stop();
		executor.shutdown();
	}
	
	public void addConnection(String clientName, ClientConnection conn)
	{
		clients.put(clientName, conn);
		Server.getPlayerList().addDefault(clientName);
		executor.execute(new ClientUpdater(conn, Server.getPlayerList().get(clientName)));
	}
	
	public void removeConnection(String clientName)
	{
		clients.remove(clientName);
		Server.getPlayerList().remove(clientName);
		Iterator<ClientConnection> connIterator = clients.values().iterator();
		while (connIterator.hasNext())
		{
			try
			{
				ClientConnection conn = connIterator.next();
				conn.write("player.removal");
				conn.write(clientName);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public ClientConnection getConnection(String clientName)
	{
		return clients.get(clientName);
	}
	
	public Iterator<String> getConnectionNameIterator()
	{
		return clients.keySet().iterator();
	}
	
	public Iterator<ClientConnection> getConnectionIterator()
	{
		return clients.values().iterator();
	}
	
	private void startClientAcceptor(int port) throws IOException
	{
		clientAcceptor = new ClientAcceptor(port);
		Thread clientAcceptorThread = new Thread(clientAcceptor);
		clientAcceptorThread.start();
	}
}
