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

public class KeyListener implements KeyboardObserver
{
	private List<Integer> directionKeys;
	private Map<Integer, Direction> keyDirectionMap;
	
	public KeyListener()
	{
		setupDirectionKeyList();
		setupKeyDirectionMap();
	}
	
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
	
	private boolean isDirectionKey(int key)
	{
		return directionKeys.contains(key);
	}
	
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
	
	private int getNumDirectionKeysPressed()
	{
		return (int) directionKeys.stream()
				                  .filter(key -> KeyCallback.isKeyPressed(key))
				                  .count();
	}
	
	private int getSingleDirectionKey()
	{
		return directionKeys.stream()
				            .filter(key -> KeyCallback.isKeyPressed(key))
				            .collect(Collectors.toList())
				            .get(0);
	}
	
	private Direction getSingleDirection()
	{
		return keyDirectionMap.get(getSingleDirectionKey());
	}
	
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
