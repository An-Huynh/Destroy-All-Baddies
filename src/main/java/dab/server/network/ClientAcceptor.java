package dab.server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import dab.server.Server;

public class ClientAcceptor implements Runnable
{
	private ServerSocket listener;
	private boolean running;
	
	public ClientAcceptor(int port) throws IOException
	{
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		listener.bind(new InetSocketAddress(port));
	}
	
	@Override
	public void run()
	{
		running = true;
		while (running)
		{
			try
			{
				acceptConnection();
			}
			catch (ClassNotFoundException | IOException e)
			{
				//e.printStackTrace();
				running = false;
			}
		}
	}
	
	public void stop()
	{
		try
		{
			running = false;
			listener.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void acceptConnection() throws IOException, ClassNotFoundException
	{
		ClientConnection conn = new ClientConnection(listener.accept());
		addConnectionToServer(conn);
	}
	
	private void addConnectionToServer(ClientConnection conn) throws IOException, ClassNotFoundException
	{
		conn.write("request.name");
		String name = (String) conn.read();
		if (Server.getClientManager().getConnection(name) == null)
			Server.getClientManager().addConnection(name, conn);
		else
			conn.close();
	}
}
