package dab.client.event.keyboard.listener;

import org.lwjgl.glfw.GLFW;

import dab.client.event.keyboard.K_Observer;
import dab.client.event.keyboard.KeyCallback;
import dab.client.manager.ClientManager;
import dab.common.entity.attribute.Direction;

public class DirectionListener implements K_Observer {
    
    private ClientManager clientManager;
    
    public DirectionListener(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Override
    public void update(int key, int action, int mods) {
        if (key == getKey("up") || key == getKey("right") || key == getKey("down") || key == getKey("left")) {
            if (keyUp("up") && !keyUp("right") && !keyUp("down") && !keyUp("left")) {
                clientManager.getPlayerList().getMainPlayer().setDirection(Direction.UP);
            } else if (!keyUp("up") && keyUp("right") && !keyUp("down") && !keyUp("left")) {
                clientManager.getPlayerList().getMainPlayer().setDirection(Direction.RIGHT);
            } else if (!keyUp("up") && !keyUp("right") && keyUp("down") && !keyUp("left")) {
                clientManager.getPlayerList().getMainPlayer().setDirection(Direction.DOWN);
            } else if (!keyUp("up") && !keyUp("right") && !keyUp("down") && keyUp("left")) {
                clientManager.getPlayerList().getMainPlayer().setDirection(Direction.LEFT);
            } else {
                clientManager.getPlayerList().getMainPlayer().setDirection(Direction.NONE);
            }
        }
    }
    
    public int getKey (String key) {
        if (key.equals("up")) {
            return GLFW.GLFW_KEY_UP;
        } else if (key.equals("right")) {
            return GLFW.GLFW_KEY_RIGHT;
        } else if (key.equals("down")) {
            return GLFW.GLFW_KEY_DOWN;
        } else if (key.equals("left")) {
            return GLFW.GLFW_KEY_LEFT;
        } else {
            return 0;
        }
    }
    
    public boolean keyUp(String key) {
        return KeyCallback.isKeyDown(getKey(key));
    }
    
}
