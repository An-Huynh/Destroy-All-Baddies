package dab.client.handler;

import org.lwjgl.glfw.GLFW;

import dab.client.event.keyboard.K_Observer;
import dab.client.state.GameState;
import dab.common.entity.attribute.Direction;

public class DirectionListener implements K_Observer {
	private GameState gameState;
	
	public DirectionListener(GameState gameState) {
		this.gameState = gameState;
	}
	
    @Override
    public void update(int key, int action, int mods) {
        if (action == GLFW.GLFW_PRESS) {
            if (key == GLFW.GLFW_KEY_UP) {
                gameState.setDirection(Direction.UP);
            } else if (key == GLFW.GLFW_KEY_RIGHT) {
                gameState.setDirection(Direction.RIGHT);
            } else if (key == GLFW.GLFW_KEY_DOWN) {
                gameState.setDirection(Direction.DOWN);
            } else if (key == GLFW.GLFW_KEY_LEFT) {
                gameState.setDirection(Direction.LEFT);
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (key == GLFW.GLFW_KEY_UP && gameState.getPlayerDirection() == Direction.UP) {
                gameState.setDirection(Direction.NONE);
            } else if (key == GLFW.GLFW_KEY_RIGHT && gameState.getPlayerDirection() == Direction.RIGHT) {
                gameState.setDirection(Direction.NONE);
            } else if (key == GLFW.GLFW_KEY_DOWN && gameState.getPlayerDirection() == Direction.DOWN) {
                gameState.setDirection(Direction.NONE);
            } else if (key == GLFW.GLFW_KEY_LEFT && gameState.getPlayerDirection() == Direction.LEFT) {
                gameState.setDirection(Direction.NONE);
            }
        }
    }

}
