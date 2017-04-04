package dab.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new ObjectOutputStream(this.socket.getOutputStream());
		this.in = new ObjectInputStream(this.socket.getInputStream());
	}
	
	public Object readObject() {
		synchronized(in) {
			try {
				return in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public void writeObject(Object obj) {
		synchronized(out) {
			try {
				out.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
