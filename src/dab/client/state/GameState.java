package dab.client.state;

import org.joml.Vector2f;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;

public class GameState {

	private Player player;
	private boolean directionDirty;
	// private ArrayList<Player> friends;
	
	public GameState() {
		player = new Player();
		directionDirty = false;
	}
	
	// Accessors
	public String getZone() {
		return player.getZone();
	}
	
	public Vector2f getPlayerLocation() {
		return player.getCenter();
	}
	
	public Direction getPlayerDirection() {
		return player.getDirection();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean isDirectionDirty() {
		return directionDirty;
	}
	
	// Mutators
	
	public void setZone(String zoneID) {
		this.player.setZone(zoneID);
	}
	
	public void setPlayerLocation(Vector2f location) {
		this.player.setLocation(location);
	}
	
	public void setDirection(Direction direction) {
		this.player.setDirection(direction);
		setDirectionDirty(true);
	}
	
	public void setDirectionDirty(boolean dirty) {
		this.directionDirty = dirty;
	}
	
	
}
