package dab.client.listeners.mouse;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import dab.client.Client;

/**
 * This class is the implementation of the MouseObserver. It handles
 * mouse clicks. This is primarily used to handle left mouse clicks
 * for the purpose of shooting.
 * 
 * @author Eli Irvin
 */
public class MouseListener implements MouseObserver
{
	private DoubleBuffer cursorPositionX;
	private DoubleBuffer cursorPositionY;
	private IntBuffer windowWidth;
	private IntBuffer windowHeight;
	
	/**
	 * Default constructor for the MouseListener.
	 * Initiates the four buffers for cursor position
	 * and window dimensions.
	 */
	public MouseListener()
	{
		cursorPositionX = BufferUtils.createDoubleBuffer(1);
		cursorPositionY = BufferUtils.createDoubleBuffer(1);
		windowWidth = BufferUtils.createIntBuffer(1);
		windowHeight = BufferUtils.createIntBuffer(1);
	}
	
	/**
	 * The invoke method of the observer. If the players zoneID isn't empty,
	 * then check for the click being a left mouse click. If it is, call
	 * the handler method.
	 */
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
	
	/**
	 * Checks is the button parameter is the left mouse button
	 * and if the action is a click and not a release
	 * 
	 * @param button - the button that was pressed
	 * @param action - the action of that button, usually either PRESS or RELEASE
	 * 
	 * @return True if it is a left mouse click | False otherwise
	 */
	private boolean isLeftClick(int button, int action)
	{
		return GLFW.GLFW_MOUSE_BUTTON_1 == button && GLFW.GLFW_PRESS == action;
	}
	
	/**
	 * First, it loads the cursor position and window dimensions
	 * into the buffers by calling the helper method. It then
	 * uses those to calculate the shooting location that the
	 * server can relate to. It then sends that location to the
	 * server for it to handle.
	 * 
	 * @param window - the window of the client display
	 */
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
	
	/**
	 * Uses GLFW to get the current window size and current cursor position.
	 * The GLFW methods will output those variables into the buffers.
	 * 
	 * @param window - the window of the client display
	 */
	private void loadCursorPosAndWindowDimensions(long window)
	{
		GLFW.glfwGetCursorPos(window, cursorPositionX, cursorPositionY);
		GLFW.glfwGetWindowSize(window, windowWidth, windowHeight);
	}
	
	/**
	 * Uses the scale of the zone to calculate the shot location. Gets the current
	 * cursor position and divides it by the window dimension over the scale of 
	 * the zone. The y of the shot location will have to be the negative of itself with
	 * the scale added on the get an accurate y value.
	 * 
	 * @return Vector2f of the shot location
	 */
	private Vector2f calcShootingLocation()
	{
		Vector2i scale = Client.getRenderScalingRegistry().get(Client.getPlayerList().getMainPlayer().getZoneID());
		Vector2f shotLocation = new Vector2f();
		shotLocation.x = (float) cursorPositionX.get(0) / (windowWidth.get(0) / scale.x);
		shotLocation.y = -((float) cursorPositionY.get(0) / (windowHeight.get(0) / scale.y)) + scale.y;
		return shotLocation;
	}
}
