package dab.server.world.tile;

import org.joml.Vector2f;

import dab.common.physics.AABB;

public abstract class Tile
{
	private String name;
	private AABB boundingBox;
	private boolean solid;
	
	// Constructor
	public Tile()
	{
		name = "";
		boundingBox = new AABB();
		solid = true;
	}
	
	// Accessors
	public String getName()
	{
		return name;
	}
	
	public AABB getBoundingBox()
	{
		return boundingBox;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public Vector2f getMidPoint()
	{
		return boundingBox.getMidPoint();
	}
	
	// Mutator
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setBoundingBox(AABB boundingBox)
	{
		this.boundingBox.copy(boundingBox);
	}
	
	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
}
