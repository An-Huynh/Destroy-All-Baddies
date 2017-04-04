package dab.common.entity;

import java.io.Serializable;

import org.joml.Vector2f;

import dab.common.physics.AABB;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 40131634443525852L;
	
	private AABB boundingBox;
	
	// Constructors
	public Entity() {
		this.boundingBox = new AABB();
	}
	
	// Accessors
	public Vector2f getCenter() {
		return this.boundingBox.getCenter();
	}
	
	public AABB getAABB() {
		return this.boundingBox;
	}
	
	// Mutators
	
	protected void setCenter(float x, float y) {
		this.boundingBox.setCenter(x, y);
	}
	
	protected void setCenter(Vector2f center) {
		this.boundingBox.setCenter(center);
	}
	
	protected void setWidth(float width) {
		this.boundingBox.setWidth(width);
	}
	
	protected void setHeight(float height) {
		this.boundingBox.setHeight(height);
	}
	
}
