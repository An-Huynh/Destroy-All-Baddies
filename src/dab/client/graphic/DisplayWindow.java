package dab.client.graphic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import dab.client.event.keyboard.K_Observer;
import dab.client.event.keyboard.KeyCallback;
import dab.client.event.window.resize.WindowSizeCallback;

public class DisplayWindow {
	private long window;
	private KeyCallback k_Callback;
	
	// constants
	private static final float[] WHITE = {1.0f, 1.0f, 1.0f, 1.0f};
	
	public void start() {
		startGLFW();
		createWindow();
		setupCallbacks();
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public void stop() {
		terminateWindow();
		stopGLFW();
	}
	
	public void clearBuffer() {
		GL30.glClearBufferfv(GL11.GL_COLOR, 0, DisplayWindow.WHITE);
	}
	
	public void pollEvents() {
		GLFW.glfwPollEvents();
	}
	
	private void startGLFW() {
		// Try to initialize GLFW library
		if (!GLFW.glfwInit()) {
			System.err.println("Failed to initialize GLFW");
			System.exit(1);
		}
	}
	
	private void stopGLFW() {
		GLFW.glfwTerminate();
	}
	
	private void createWindow() {
		// Set window hints for GLFW
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		
		// create window
		this.window = GLFW.glfwCreateWindow(640, 480, "Destroy-All-Baddies", 0, 0);
		if (this.window == 0) {
			System.err.println("Failed to create GLFW Window");
			System.exit(1);
		}
		
		// lock aspect ratio to 4/3
		GLFW.glfwSetWindowAspectRatio(this.window, 4, 3);
		
		// Makes the context of the specified window current for the calling thread
		GLFW.glfwMakeContextCurrent(this.window);
		
		GL.createCapabilities();
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void terminateWindow() {
		GLFW.glfwDestroyWindow(this.window);
	}
	
	private void setupCallbacks() {
		// create Observable keyHandler
		this.k_Callback = new KeyCallback();
		
		// attach callbacks to current window
		GLFW.glfwSetKeyCallback(this.window, this.k_Callback);
		GLFW.glfwSetWindowSizeCallback(this.window, new WindowSizeCallback());
	}
	
	public void registerKeyListener(K_Observer observer) {
		if (k_Callback == null) {
			System.out.println("null");
		}
		k_Callback.registerObserver(observer);
	}
	
	public void unregisterKeyListener(K_Observer observer) {
		k_Callback.unregisterObserver(observer);
	}
		
}
