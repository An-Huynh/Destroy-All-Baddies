package dab.client.event.window.resize;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;

public class WindowSizeCallback extends GLFWWindowSizeCallback {
	public void invoke(long window, int width, int height) {
		GL11.glViewport(0, 0, width, height);
	}
}
