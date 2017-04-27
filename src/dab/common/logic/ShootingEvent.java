package dab.common.logic;

import java.io.IOException;
import java.util.Iterator;

import org.joml.Vector2f;

import dab.common.entity.enemy.Enemy;
import dab.common.registry.EnemyRegistry;
import dab.server.GameServer;
import dab.server.network.ClientConnection;

public class ShootingEvent implements Event {

	private Vector2f source;
	private Vector2f destination;
	private String   zone;
	
	public void setSource(Vector2f source) {
		this.source = source;
	}
	
	public Vector2f getSource() {
		return source;
	}
	
	public void setDestination(Vector2f destination) {
		this.destination = destination;
	}
	
	public Vector2f getDestination() {
		return destination;
	}
	
	public void setZone(String zone) {
		this.zone = zone;
	}
	
	public String getZone() {
		return zone;
	}
	
	@Override
	public void process() {
		for(Enemy bot : EnemyRegistry.getEnemies()) {
			if(bot.getZone().equals(zone) && getCollisionWithGunshot(bot)) {
				EnemyRegistry.remove(bot);
				updateAllClients(bot);
			}
		}
	}
	
	private boolean getCollisionWithGunshot(Enemy bot) {
		float deltaX = destination.x - bot.getAABB().getCenter().x;
		float px = (bot.getAABB().getWidth()/2) - abs(deltaX);
		if(px <= 0) {
			return false;
		}
		float deltaY = destination.y - bot.getAABB().getCenter().y;
		float py = (bot.getAABB().getHeight()/2) - abs(deltaY);
		if(py <= 0) {
			return false;
		}
		return true;
	}

	private float abs(float value) {
		if(value < 0) {
			return -value;
		} else {
			return value;
		}
	}

	private void updateAllClients(Enemy bot) {
		Iterator<ClientConnection> conns = GameServer.socketManager.getConnections().iterator();
		while (conns.hasNext()) {
			try {
				updateClientOfDeath(bot, conns.next());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateClientOfDeath(Enemy bot, ClientConnection conn) throws IOException {
		conn.writeObject("update.bot.removal");
		conn.writeObject(bot.getName());
	}

}
