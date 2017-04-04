package dab.common.physics;

import java.io.Serializable;

import org.joml.Vector2f;

public class AABB implements Serializable {
	
	private static final long serialVersionUID = 4609901998137111747L;
	
	private Vector2f center;
	private float width;
	private float height;
	
	// Constructor
	
	public AABB() {
		this.center = new Vector2f();
	}
	
	// Accessors
	
	public Vector2f getCenter() {
		return this.center;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	// Mutators
	
	public AABB setCenter(Vector2f center) {
		this.center.set(center);
		return this;
	}
	
	public AABB setCenter(float x, float y) {
		this.center.set(x, y);
		return this;
	}
	
	public AABB setWidth(float width) {
		this.width = width;
		return this;
	}
	
	public AABB setHeight(float height) {
		return this;
	}
	
	// etc
	
	public boolean testCollision(AABB aabb) {
		if (Math.abs(this.center.x - aabb.center.x) > (this.width/2 + aabb.width/2) ||
				Math.abs(this.center.y - aabb.center.y) > (this.height/2 + aabb.height/2)) {
			return false;
		}
		return true;
	}
	
	public AABB copy(AABB aabb) {
		this.width = aabb.width;
		this.height = aabb.height;
		this.center.set(aabb.center);
		return this;
	}
	
	public AABB addOffset(float x, float y) {
		this.center.add(x, y);
		return this;
	}
	
}
