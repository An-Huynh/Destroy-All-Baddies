package dab.client.graphic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import dab.client.event.keyboard.K_Observer;
import dab.client.event.keyboard.KeyCallback;
import dab.client.event.window.resize.WindowSizeCallback;

public class Display {

    private long window;
    private KeyCallback k_Callback;
    
    private final float[] WHITE = {1.0f, 1.0f, 1.0f, 1.0f};
    
    public void start() {
        startGLFW();
        createWindow();
        setupCallbacks();
    }
    
    public void stop() {
        terminateWindow();
        stopGLFW();
    }
    
    public void clearBuffer() {
        GL30.glClearBufferfv(GL11.GL_COLOR, 0, WHITE);
    }
    
    public void pollEvents() {
        GLFW.glfwPollEvents();
    }
    
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(window);
    }
    
    private void startGLFW() {
        if (!GLFW.glfwInit()) {
            System.err.println("Failed to initialize GLFW");
            System.exit(1);
        }
    }
    
    private void createWindow() {
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        
        window = GLFW.glfwCreateWindow(640, 480, "DAB", 0, 0);
        if (this.window == 0) {
            System.err.println("Failed to create GLFW Window");
            System.exit(1);
        }
        
        GLFW.glfwSetWindowAspectRatio(window, 4, 3);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    private void stopGLFW() {
        GLFW.glfwTerminate();
    }
    
    private void terminateWindow() {
        GLFW.glfwDestroyWindow(window);
    }
    
    private void setupCallbacks() {
        k_Callback = new KeyCallback();
        GLFW.glfwSetKeyCallback(window, k_Callback);
        GLFW.glfwSetWindowSizeCallback(window, new WindowSizeCallback());
    }
    
    public void registerKeyListener(K_Observer observer) {
        k_Callback.registerObserver(observer);
    }
    
    public void removeKeyListener(K_Observer observer) {
        k_Callback.unregisterObserver(observer);
    }
    
}
