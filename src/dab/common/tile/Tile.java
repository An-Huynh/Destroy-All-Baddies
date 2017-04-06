package dab.common.tile;

import org.joml.Vector2f;

import dab.common.physics.AABB;

public abstract class Tile {
	
	private String name;
	private AABB boundingBox; // values set to local to itself (0, 0) bottom left to (1, 1) top right
	private boolean solid;
	
	// Constructor
	
	public Tile() {
		this.boundingBox = new AABB();
	}
	
	// Accessors
	
	public AABB getAABB() {
		return this.boundingBox;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isSolid() {
	    return this.solid;
	}
	
	// Mutators
	
	protected void setCenter(Vector2f center) {
		this.boundingBox.setCenter(center);
	}
	
	protected void setCenter(float x, float y) {
		this.boundingBox.setCenter(x, y);
	}
	
	protected void setWidth(float width) {
		this.boundingBox.setWidth(width);
	}
	
	protected void setHeight(float height) {
		this.boundingBox.setHeight(height);
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	protected void setSolid(boolean value) {
	    this.solid = value;
	}
}
