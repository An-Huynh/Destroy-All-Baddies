package dab.client.event.mouse.listener;

import java.nio.DoubleBuffer;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import dab.client.event.mouse.M_Observer;
import dab.client.manager.ClientManager;
import dab.common.entity.player.Player;

public class ClickListener implements M_Observer {
	
	private ClientManager clientManager;
	
	public ClickListener(ClientManager clientManager) {
		this.clientManager = clientManager;
	}

	@Override
	public void update(long window, int button, int action, int modifier) {
		if(button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS) {
			handleLeftMouseClick(window);
		}
	}
	
	private void handleLeftMouseClick(long window) {
		Player mainPlayer = getPlayer();
		DoubleBuffer b1 = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer b2 = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, b1, b2);
		System.out.println("Mouse x: " + b1.get(0) + ", Mouse y: " + b2.get(0));
		Vector2f playerPosition = mainPlayer.getCenter();
		System.out.println("Player x: " + playerPosition.x + ", Player y: " + playerPosition.y);
	}

	private Player getPlayer() {
    	return clientManager.getPlayerList().getMainPlayer();
    }

}
