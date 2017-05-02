package dab.client.listeners.mouse;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import dab.client.Client;

public class MouseListener implements MouseObserver
{
	private DoubleBuffer cursorPositionX;
	private DoubleBuffer cursorPositionY;
	private IntBuffer windowWidth;
	private IntBuffer windowHeight;
	
	public MouseListener()
	{
		cursorPositionX = BufferUtils.createDoubleBuffer(1);
		cursorPositionY = BufferUtils.createDoubleBuffer(1);
		windowWidth = BufferUtils.createIntBuffer(1);
		windowHeight = BufferUtils.createIntBuffer(1);
	}
	
	@Override
	public void invoke(long window, int button, int action, int modifier) {
		if (!Client.getPlayerList().getMainPlayer().getZoneID().equals(""))
		{
			if (isLeftClick(button, action))
			{
				handleLeftMouseClick(window);
			}
		}
	}
	
	private boolean isLeftClick(int button, int action)
	{
		return GLFW.GLFW_MOUSE_BUTTON_1 == button && GLFW.GLFW_PRESS == action;
	}
	
	private void handleLeftMouseClick(long window)
	{
		loadCursorPosAndWindowDimensions(window);
		Vector2f shotLocation = calcShootingLocation();
		try
		{
			Client.getServerConnectionManager().getConn().sendShootingLocation(shotLocation);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadCursorPosAndWindowDimensions(long window)
	{
		GLFW.glfwGetCursorPos(window, cursorPositionX, cursorPositionY);
		GLFW.glfwGetWindowSize(window, windowWidth, windowHeight);
	}
	
	private Vector2f calcShootingLocation()
	{
		Vector2i scale = Client.getRenderScalingRegistry().get(Client.getPlayerList().getMainPlayer().getZoneID());
		Vector2f shotLocation = new Vector2f();
		shotLocation.x = (float) cursorPositionX.get(0) / (windowWidth.get(0) / scale.x);
		shotLocation.y = -((float) cursorPositionY.get(0) / (windowHeight.get(0) / scale.y)) + scale.y;
		return shotLocation;
	}
}
