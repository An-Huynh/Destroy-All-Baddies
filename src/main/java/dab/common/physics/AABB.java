package dab.common.physics;

import java.io.Serializable;

import org.joml.Vector2f;

public class AABB implements Serializable
{
	private static final long serialVersionUID = 8858831820673061976L;
	private Vector2f midPoint;
	private float width;
	private float height;
	
	// Constructors
	public AABB()
	{
		midPoint = new Vector2f(0, 0);
		width = 0;
		height = 0;
	}
	
	public AABB(Vector2f midPoint, float width, float height)
	{
		this.midPoint = new Vector2f().set(midPoint);
		this.width = width;
		this.height = height;
	}
	
	public AABB(float x, float y, float width, float height)
	{
		this.midPoint = new Vector2f(x, y);
		this.width = width;
		this.height = height;
	}
	
	// Accessors
	public Vector2f getMidPoint()
	{
		return midPoint;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	// Mutators
	public void setMidPoint(Vector2f midPoint)
	{
		this.midPoint.set(midPoint);
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}
	
	// etc
	public void copy(AABB aabb)
	{
		midPoint.set(aabb.midPoint);
		width = aabb.width;
		height = aabb.height;
	}
}
