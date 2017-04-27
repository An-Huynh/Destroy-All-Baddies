package dab.client.event.mouse.listener;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import dab.client.GameClient;
import dab.client.event.mouse.M_Observer;
import dab.client.network.ServerConnection;

public class ClickListener implements M_Observer {
	
	private DoubleBuffer cursorX;
	private DoubleBuffer cursorY;
	private IntBuffer widthBuffer;
	private IntBuffer heightBuffer;
	private ServerConnection conn;
	
	public ClickListener(ServerConnection conn) {
		this.conn = conn;
	}

	@Override
	public void update(long window, int button, int action, int modifier) {
		if(button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS) {
			handleLeftMouseClick(window);
		}
	}
	
	private void handleLeftMouseClick(long window) {
		cursorX = BufferUtils.createDoubleBuffer(1);
		cursorY = BufferUtils.createDoubleBuffer(1);
		widthBuffer = BufferUtils.createIntBuffer(1);
		heightBuffer = BufferUtils.createIntBuffer(1);
		GLFW.glfwGetCursorPos(window, cursorX, cursorY);
		GLFW.glfwGetWindowSize(window, widthBuffer, heightBuffer);
		int width = widthBuffer.get(0)/32;
		int height = heightBuffer.get(0)/24;
		try {
			conn.write("update.event.shooting");
			conn.write(new Vector2f((float)cursorX.get(0)/width, (float)(24 - (cursorY.get(0)/height))));
			conn.write(GameClient.clientManager.getPlayerList().getMainPlayer().getLocation());
			conn.write(GameClient.clientManager.getPlayerList().getMainPlayer().getZone());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
