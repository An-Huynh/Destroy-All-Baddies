package dab.client.network;

import java.io.IOException;
import java.net.UnknownHostException;

public class ServerConnectionManager
{
	private ServerConnection conn;
	private ServerUpdater serverUpdater;
	
	private String clientName;
	private String hostName;
	private int port;
	
	
	public ServerConnectionManager(String clientName, String hostName, int port)
	{
		this.clientName = clientName;
		this.hostName = hostName;
		this.port = port;
	}
	
	public ServerConnection getConn()
	{
		return conn;
	}
	
	public void start() throws UnknownHostException, IOException
	{
		conn = new ServerConnection(hostName, port);
		startServerUpdater();
	}
	
	public void stop()
	{
		serverUpdater.stop();
	}
	
	private void startServerUpdater()
	{
		serverUpdater = new ServerUpdater(conn);
		Thread serverUpdaterThread = new Thread(serverUpdater);
		serverUpdaterThread.start();
	}
	
	public String getClientName()
	{
		return clientName;
	}
}
