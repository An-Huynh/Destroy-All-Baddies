package dab.server.network;

import java.io.IOException;
import java.util.Iterator;

import dab.server.Server;

public class ClientLifeChecker implements Runnable
{
	private boolean running;
	
	@Override
	public void run()
	{
		running = true;
		while (running)
		{
			pingConnections();
			pauseThread(5000);
		}
	}
	
	private void pingConnections()
	{
		Iterator<String> connNames = Server.getClientManager().getConnectionNameIterator();
		while (connNames.hasNext())
		{
			String connName = "";
			try
			{
				connName = connNames.next();
				ClientConnection conn = Server.getClientManager().getConnection(connName);
				conn.setTimeout(10000);
				conn.write("request.live");
			}
			catch (IOException e)
			{
				Server.getClientManager().removeConnection(connName);
			}
		}
	}
	
	private void pauseThread(long milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void stop()
	{
		running = false;
	}
}
