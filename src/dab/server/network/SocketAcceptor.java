package dab.server.network;

import java.io.IOException;
import java.net.Socket;

public class SocketAcceptor implements Runnable {
	
	private SocketManager socketManager;
	private boolean running;
	
	public SocketAcceptor(SocketManager socketManager) {
		this.socketManager = socketManager;
		running = false;
	}
	
	public void stop() {
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			handleIncomingConnection();
		}
	}
	
	private void handleIncomingConnection() {
		try {
			ClientConnection conn = getClientConnection();
			boolean sync = initiateSync(conn);
			if (sync) {conn.writeObject("update.sync.complete");}
			else {conn.close();}
		} catch (IOException | ClassNotFoundException e) {
			// do nothing for now
		}
	}
	
	private ClientConnection getClientConnection() throws IOException {
		Socket socket = socketManager.acceptConnection();
		return new ClientConnection(socket);
	}
	
	private boolean initiateSync(ClientConnection conn) throws IOException, ClassNotFoundException {
		conn.writeObject("request.name");
		conn.setTimeout(10000);
		boolean result = addClient(getClientName(conn), conn);
		return result;
	}
	
	private String getClientName(ClientConnection conn) throws ClassNotFoundException, IOException {
		String[] messageComponent = splitMessage((String) conn.readObject());
		if (messageComponent[0].equals("update")) { // message expected update.<client name>
			return messageComponent[1]; // second component should contain client name
		}
		return null;
	}
	
	private boolean checkNameExists(String name) {
		return socketManager.getConnection(name) != null;
	}
	
	private boolean addClient(String name, ClientConnection conn) throws IOException {
		if (!checkNameExists(name)) {
			socketManager.setupNewConnection(name, conn);
			return true;
		}
		conn.writeObject("message.name.exists");
		return false;
	}
	
	private String[] splitMessage(String message) {
		String[] components = new String[2];
		if (message.contains(".")) { // can be split into parts
			components[0] = message.substring(0, message.indexOf('.'));
			components[1] = message.substring(message.indexOf('.') + 1);
		} else { // store entire message in second part
			components[0] = "";
			components[1] = message;
		}
		return components;
	}
}
