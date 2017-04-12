package dab.client.event.keyboard.listener;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import dab.client.event.keyboard.K_Observer;
import dab.client.event.keyboard.KeyCallback;
import dab.client.manager.ClientManager;
import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;

public class DirectionListener implements K_Observer {
    
    private ClientManager clientManager;
    
    public DirectionListener(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Override
    public void update(int key, int action, int mods) {
    	if (isDirectionKey(key)) {
    		handleDirection();
    	}
    }
    
    private void setDirection(Direction direction) {
    	clientManager.getPlayerList().getMainPlayer().setDirection(direction);
    	clientManager.getPlayerList().getMainPlayer().setIsDirectionModified(true);
    }
    
    private void handleDirection() {
    	ArrayList<Direction> directionsPressed = getDirectionsPressed();
    	if (directionsPressed.size() == 1 && directionsPressed.get(0) != getPlayer().getDirection()) {
    		setDirection(directionsPressed.get(0));
    	} else if (directionsPressed.size() == 0 || directionsPressed.size() > 1) {
    		setDirection(Direction.NONE);
    	}
    }
    
    private boolean isKeyDown(int key) {
    	return KeyCallback.isKeyDown(key);
    }
    
    private ArrayList<Direction> getDirectionsPressed() {
    	ArrayList<Direction> directionsPressed = new ArrayList<Direction>();
    	if (isKeyDown(GLFW.GLFW_KEY_LEFT)) {
    		directionsPressed.add(Direction.LEFT);
    	}
    	if (isKeyDown(GLFW.GLFW_KEY_UP)) {
    		directionsPressed.add(Direction.UP);
    	}
    	if (isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
    		directionsPressed.add(Direction.RIGHT);
    	}
    	if (isKeyDown(GLFW.GLFW_KEY_DOWN)) {
    		directionsPressed.add(Direction.DOWN);
    	}
    	return directionsPressed;
    }
    
    private boolean isDirectionKey(int key) {
    	if (key == GLFW.GLFW_KEY_LEFT) {
    		return true;
    	} else if (key == GLFW.GLFW_KEY_UP) {
    		return true;
    	} else if (key == GLFW.GLFW_KEY_RIGHT) {
    		return true;
    	} else if (key == GLFW.GLFW_KEY_DOWN) {
    		return true;
    	}
    	return false;
    }
    
    private Player getPlayer() {
    	return clientManager.getPlayerList().getMainPlayer();
    }

}
