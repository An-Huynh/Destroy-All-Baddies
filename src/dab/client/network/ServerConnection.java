package dab.client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerConnection {
    
    private Socket connection;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    /**
     * ServerConnection Constructor
     * 
     * @param connection
     * @throws IOException
     */
    public ServerConnection(Socket connection) throws IOException {
        this.connection = connection;
        
        // setup input and output steams
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());
    }
    
    public void write(Object obj) throws IOException {
        synchronized(out) {
            out.writeObject(obj);
        }
    }
    
    public Object read() throws ClassNotFoundException, IOException {
        synchronized(in) {
            return in.readObject();
        }
    }
    
    public void setTimeout(int time) throws SocketException {
        connection.setSoTimeout(time);
    }
    
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
    
}
