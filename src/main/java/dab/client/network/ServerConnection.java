package dab.client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.joml.Vector2f;

public class ServerConnection
{
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ServerConnection(String host, int port) throws UnknownHostException, IOException
	{
		connection = new Socket(host, port);
		out = new ObjectOutputStream(connection.getOutputStream());
		in = new ObjectInputStream(connection.getInputStream());
	}
	
	public void write(Object obj) throws IOException
	{
		out.writeUnshared(obj);
	}
	
	public Object read() throws ClassNotFoundException, IOException
	{
		return in.readUnshared();
	}
	
	public void setTimeout(int time) throws SocketException
	{
		connection.setSoTimeout(time);
	}
	
	public void sendJumpRequest() throws IOException
	{
		write("request.jump");
	}
	
	public void sendShootingLocation(Vector2f shotLocation) throws IOException
	{
		write("event.shooting");
		write(shotLocation);
	}
	
	public void close()
	{
		try
		{
			connection.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}	
}
