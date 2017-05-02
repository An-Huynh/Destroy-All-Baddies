package dab.client.listeners.keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.lwjgl.glfw.GLFW;

import dab.client.Client;
import dab.common.entity.attributes.Direction;


/**
 * Observer from the Observer design pattern that is
 * listening for keypresses. This is primarily used
 * to handle directions and jumping from the player
 * and other entities.
 * 
 * @author Eli Irvin
 */
public class KeyListener implements KeyboardObserver
{
	private List<Integer> directionKeys;
	private Map<Integer, Direction> keyDirectionMap;
	
	/**
	 * Default constructor for the KeyListener.
	 * Calls the two setup methods.
	 */
	public KeyListener()
	{
		setupDirectionKeyList();
		setupKeyDirectionMap();
	}
	
	/**
	 * Updates the observer by first checking what kind of key was
	 * pressed. If it is a direction key, then it calls that method.
	 * If it is the space key and is being pressed, then it handles
	 * for a jump.
	 */
	@Override
	public void update(int key, int action, int mods)
	{
		if (isDirectionKey(key))
		{
			updateClientDirection();
		}
		else if (key == GLFW.GLFW_KEY_SPACE && action == GLFW.GLFW_PRESS)
		{
			handleJumpRequest();
		}
	}
	
	/**
	 * Handles a jump request. Simply sends to the server that the jump
	 * key was pressed for this client. The server will then handle it.
	 */
	private void handleJumpRequest()
	{
		try
		{
			Client.getServerConnectionManager().getConn().sendJumpRequest();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks for whether the key that the action was done upon is one of the direction keys
	 * 
	 * @param key - the key being sent through the update
	 * 
	 * @return True if the key is a direction key | False otherwise
	 */
	private boolean isDirectionKey(int key)
	{
		return directionKeys.contains(key);
	}
	
	/**
	 * Sends to the server updates to update the direction of this client.
	 * If they are pressing a single direction key, it will send an update
	 * for the player to go in that direction. If more than 1 key, or no keys
	 * are pressed, it will send an update for the direction to be nowhere.
	 */
	private void updateClientDirection()
	{
		if (getNumDirectionKeysPressed() == 1)
		{
			try
			{
				Client.getServerConnectionManager().getConn().write("update.direction");
				Client.getServerConnectionManager().getConn().write(getSingleDirection());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				Client.getServerConnectionManager().getConn().write("update.direction");
				Client.getServerConnectionManager().getConn().write(Direction.NONE);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets the number of directions keys currently being pressed.
	 * Go through each direction key and calls the isKeyPressed method.
	 * Counts each of the keys for which that method returns true.
	 * 
	 * @return The number of directions keys currently pressed.
	 */
	private int getNumDirectionKeysPressed()
	{
		return (int) directionKeys.stream()
				                  .filter(key -> KeyCallback.isKeyPressed(key))
				                  .count();
	}
	
	/**
	 * Gets the single direction key that is being pressed. Filters all
	 * the currently pressed keys, and returns the first one.
	 * 
	 * @return A single direction key, the first one in the list of keys
	 *         being pressed.
	 */
	private int getSingleDirectionKey()
	{
		return directionKeys.stream()
				            .filter(key -> KeyCallback.isKeyPressed(key))
				            .collect(Collectors.toList())
				            .get(0);
	}
	
	/**
	 * Gets the Direction attribute that connects to the single
	 * direction key that is currently being pressed
	 * 
	 * @return a Direction is the currently pressed Direction key.
	 */
	private Direction getSingleDirection()
	{
		return keyDirectionMap.get(getSingleDirectionKey());
	}
	
	/**
	 * Adds all of the keys that fit as a direction key to the list.
	 */
	private void setupDirectionKeyList()
	{
		directionKeys = new ArrayList<Integer>();
		directionKeys.add(GLFW.GLFW_KEY_UP);
		directionKeys.add(GLFW.GLFW_KEY_RIGHT);
		directionKeys.add(GLFW.GLFW_KEY_DOWN);
		directionKeys.add(GLFW.GLFW_KEY_LEFT);
		directionKeys.add(GLFW.GLFW_KEY_W);
		directionKeys.add(GLFW.GLFW_KEY_D);
		directionKeys.add(GLFW.GLFW_KEY_S);
		directionKeys.add(GLFW.GLFW_KEY_A);
	}
	
	/**
	 * Maps each of the keys with a Direction from the
	 * Direction enum.
	 */
	private void setupKeyDirectionMap()
	{
		keyDirectionMap = new TreeMap<Integer, Direction>();
		keyDirectionMap.put(GLFW.GLFW_KEY_UP, Direction.UP);
		keyDirectionMap.put(GLFW.GLFW_KEY_W, Direction.UP);
		keyDirectionMap.put(GLFW.GLFW_KEY_RIGHT, Direction.RIGHT);
		keyDirectionMap.put(GLFW.GLFW_KEY_D, Direction.RIGHT);
		keyDirectionMap.put(GLFW.GLFW_KEY_DOWN, Direction.DOWN);
		keyDirectionMap.put(GLFW.GLFW_KEY_S, Direction.DOWN);
		keyDirectionMap.put(GLFW.GLFW_KEY_LEFT, Direction.LEFT);
		keyDirectionMap.put(GLFW.GLFW_KEY_A, Direction.LEFT);
	}
}
