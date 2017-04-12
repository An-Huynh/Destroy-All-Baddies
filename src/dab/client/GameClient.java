package dab.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import dab.client.event.keyboard.listener.DirectionListener;
import dab.client.graphic.Display;
import dab.client.graphic.initialization.RenderInitializer;
import dab.client.graphic.initialization.VariableRenderInitializer;
import dab.client.logic.LoopCycle;
import dab.client.logic.LoopCycleInitializer;
import dab.client.manager.ClientManager;
import dab.client.network.ConnectionManager;

public class GameClient implements Runnable {

    private Display display;
    private ConnectionManager connManager;
    private ClientManager clientManager;
    private LoopCycle loopCycle;
    
    private boolean running;
    
    private String host;
    private int port;
    private String name;
    
    public GameClient(String name, String host, int port) {
        running = false;
        this.host = host;
        this.port = port;
        this.name = name;
    }
    
    
    private void preInit() throws IOException {
        display = new Display();
        display.start();
        clientManager = new ClientManager();
        connManager = new ConnectionManager(clientManager);
        loopCycle = new LoopCycle();
        RenderInitializer.preInit();
        VariableRenderInitializer.preInit();
    }
    
    private void init() throws UnknownHostException, IOException, ClassNotFoundException {
        connManager.start(host, port);
        connManager.sync(name);
        RenderInitializer.init();
        VariableRenderInitializer.init();
        
        display.registerKeyListener(new DirectionListener(clientManager));
        
        LoopCycleInitializer.init(clientManager, connManager);
    }
    
    private void postInit() {
        clientManager.start(connManager.getConnection());
        LoopCycleInitializer.postInit();
    }
    
    
    public void run() {
        running = true;
        
        try {
            preInit();
            init();
            postInit();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            running = false;
        }
        
        
        
        final double tickRate = 60.0;
        final double deltaTime = 1000/tickRate;
        double targetUpdateTime = System.currentTimeMillis();
        while (running) {
            display.clearBuffer();
            display.pollEvents();
            
            try {
                loopCycle.tick();
            } catch (IOException e1) {
                e1.printStackTrace();
                running = false;
            }
            
            display.swapBuffers();
            
            targetUpdateTime += deltaTime;
            double now = System.currentTimeMillis();
            if (now < targetUpdateTime) {
                try {
                    Thread.sleep((long) (targetUpdateTime - now));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                }
            }
        }
        
        clientManager.stop();
        while (clientManager.isRunning()) {}
        connManager.close();
        display.stop();
    }
    
    public void stop() {
        running = false;
    }
    
    public static void main(String[] args) {
        GameClient gc = new GameClient("Finmitz11", "localhost", 7720);
        Thread gameThread = new Thread(gc);
        gameThread.start();
        
        Scanner in = new Scanner(System.in);
        while (!in.nextLine().equals("/stop")) {}
        in.close();
        gc.stop();
    }
    
    
}
