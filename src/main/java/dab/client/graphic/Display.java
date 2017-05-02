package dab.client.graphic;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import dab.client.listeners.keyboard.KeyCallback;
import dab.client.listeners.keyboard.KeyboardObserver;
import dab.client.listeners.mouse.MouseButtonCallback;
import dab.client.listeners.mouse.MouseObserver;
import dab.client.listeners.window.WindowResizeCallback;

public class Display
{
	private long window;
	private KeyCallback keyboardCallback;
	private MouseButtonCallback mouseButtonCallback;
	
	private static final float[] WHITE = {1.0f, 1.0f, 1.0f, 1.0f};
	
	public void start()
	{
		startGLFW();
		createWindow();
		setupCallbacks();
	}
	
	public void stop()
	{
		terminateWindow();
		stopGLFW();
	}
	
	public void clearBuffer()
	{
		GL30.glClearBufferfv(GL11.GL_COLOR, 0, WHITE);
	}
	
	public void pollEvents()
	{
		GLFW.glfwPollEvents();
	}
	
	public void swapBuffers()
	{
		GLFW.glfwSwapBuffers(window);
	}
	
	private void startGLFW()
	{
		boolean initResult = GLFW.glfwInit();
		if (initResult == false)
		{
			System.err.println("Failed to initialize GLFW");
			System.exit(1);
		}
	}
	
	private void createWindow()
	{
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);
        
		window = GLFW.glfwCreateWindow(640, 480, "DAB", 0, 0);
		if (window == 0)
		{
			System.err.println("Failed to create GLFW Window");
			System.exit(1);
		}
		
		GLFW.glfwSetWindowAspectRatio(window, 4, 3);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	private void stopGLFW()
	{
		GLFW.glfwTerminate();
	}
	
	private void terminateWindow()
	{
		GLFW.glfwDestroyWindow(window);
	}
	
	private void setupCallbacks()
	{
		keyboardCallback = new KeyCallback();
		GLFW.glfwSetKeyCallback(window, keyboardCallback);
		GLFW.glfwSetWindowSizeCallback(window, new WindowResizeCallback());
		
		mouseButtonCallback = new MouseButtonCallback();
		GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);
	}
	
	public void registerKeyListener(KeyboardObserver observer)
	{
		keyboardCallback.registerObserver(observer);
	}
	
	public void unregisterKeyListener(KeyboardObserver observer)
	{
		keyboardCallback.unregisterObserver(observer);
	}
	
	public void registerMouseListener(MouseObserver observer)
	{
		mouseButtonCallback.registerObserver(observer);
	}
	
	public boolean shouldStop()
	{
		return GLFW.glfwWindowShouldClose(window);
	}
}
