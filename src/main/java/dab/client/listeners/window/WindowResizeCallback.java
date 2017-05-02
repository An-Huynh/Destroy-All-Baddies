package dab.client.listeners.window;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;

public class WindowResizeCallback extends GLFWWindowSizeCallback
{
	@Override
	public void invoke(long window, int width, int height)
	{
		GL11.glViewport(0, 0, width, height);
	}
}
