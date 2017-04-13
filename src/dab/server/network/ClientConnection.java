package dab.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientConnection {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new ObjectOutputStream(socket.getOutputStream());
		setTimeout(10000); // throw IOException if no communication from client
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	public void writeObject(Object obj) throws IOException {
		synchronized(out) {
			out.writeUnshared(obj);
		}
	}
	
	public Object readObject() throws ClassNotFoundException, IOException {
		synchronized(in) {
			return in.readUnshared();
		}
	}
	
	public void setTimeout(int time) throws SocketException {
		socket.setSoTimeout(time);
	}
	
	public ObjectOutputStream getOut() {
		return this.out;
	}
	
	public ObjectInputStream getIn() {
		return this.in;
	}
	
	public void close() {
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				socket = null;
			}
		}
	}
}
