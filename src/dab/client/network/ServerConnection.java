package dab.client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerConnection {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public void connect(String host, int port) throws IOException {
		this.socket = new Socket();
		this.socket.connect(new InetSocketAddress(host, port));
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		this.in = new ObjectInputStream(this.socket.getInputStream());
	}
	
	public Object readObject() throws ClassNotFoundException, IOException {
		synchronized(in) {
			return in.readObject();
		}
	}
	
	public void writeObject(Object obj) throws IOException {
		synchronized(out) {
			out.writeObject(obj);
		}
	}
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
