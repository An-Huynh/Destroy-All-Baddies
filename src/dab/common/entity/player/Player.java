package dab.common.entity.player;

import java.io.Serializable;

import org.joml.Vector2f;

import dab.common.entity.Entity;
import dab.common.entity.attribute.Direction;

public class Player extends Entity implements Serializable {

	private static final long serialVersionUID = -2520883013320086201L;
	
	private String playerName;
	private String currentZone;
	private Direction direction;
	
	// Constructor
	public Player() {
		super();
	}
	
	public Player(String name) {
		super();
		
	}
	
	// Accessors
	
	public String getName() {
		return this.playerName;
	}
	
	public String getZone() {
		return this.currentZone;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Vector2f getLocation() {
		return this.getCenter();
	}
	
	// Mutators
	
	public void setName(String name) {
		this.playerName = name;
	}
	
	public void setZone(String zoneName) {
		this.currentZone = zoneName;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void setLocation(Vector2f location) {
		this.setCenter(location);
	}
	
	public void setLocation(float x, float y) {
		this.setCenter(x, y);
	}
	
	public void setHeight(float height) {
		this.getAABB().setHeight(height);
	}
	
	public void setWidth(float width) {
		this.getAABB().setWidth(width);
	}
	
	//etc
	public void copy(Player player) {
		this.playerName = player.playerName;
		this.currentZone = player.currentZone;
		this.direction = player.direction;
		this.getAABB().copy(player.getAABB());
	}
	
}
