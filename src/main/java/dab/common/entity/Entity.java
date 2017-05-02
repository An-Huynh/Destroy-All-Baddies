package dab.common.entity;

import java.io.Serializable;

import org.joml.Vector2f;
import org.joml.Vector2i;

import dab.common.entity.attributes.Direction;
import dab.common.entity.attributes.JumpState;
import dab.common.physics.AABB;

public class Entity implements Serializable
{
	private static final long serialVersionUID = 3347039189635370510L;
	private String name;
	private String zoneID;
	private Direction direction;
	private JumpState jumpState;
	private AABB boundingBox;
	private float movementSpeedModifier;
	private boolean dirty;
	
	// Constructor
	public Entity()
	{
		boundingBox = new AABB();
		
		setJumpState(JumpState.GROUND);
		setDirection(Direction.NONE);
		
		setName("");
		setZoneID("");
		
		setMovementSpeedModifier(1.0f);
		setDirty(true);
	}
	
	public Entity(String name)
	{
		this();
		this.name = name;
	}
	
	// Accessors
	public String getName()
	{
		return name;
	}
	
	public String getZoneID()
	{
		return zoneID;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public JumpState getJumpState()
	{
		return jumpState;
	}
	
	public AABB getBoundingBox()
	{
		return boundingBox;
	}
	
	public Vector2f getLocation()
	{
		return boundingBox.getMidPoint();
	}
	
	public Vector2i getApproxTileLocation()
	{
		return new Vector2i((int) boundingBox.getMidPoint().x, (int) boundingBox.getMidPoint().y);
	}
	
	public boolean hasDirection()
	{
		return !direction.equals(Direction.NONE);
	}
	
	public boolean isJumping()
	{
		return !jumpState.equals(JumpState.GROUND);
	}
	
	public float getMovementSpeedModifier()
	{
		return movementSpeedModifier;
	}
	
	public boolean isDirty()
	{
		return dirty;
	}
	
	// Mutators
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setZoneID(String zoneID)
	{
		this.zoneID = zoneID;
	}
	
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	public void setJumpState(JumpState jumpState)
	{
		this.jumpState = jumpState;
	}
	
	public void setBoundingBox(AABB boundingBox)
	{
		this.boundingBox.copy(boundingBox);
	}
	
	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}
	
	public void setLocation(Vector2f location)
	{
		getBoundingBox().getMidPoint().set(location).add(0.5f, 0.5f);
	}
	
	public void setLocation(float x, float y)
	{
		getBoundingBox().getMidPoint().set(x, y).add(0.5f, 0.5f);
	}
	
	public void setMovementSpeedModifier(float movementSpeedModifier)
	{
		this.movementSpeedModifier = movementSpeedModifier;
	}
	
	public void setHeight(float height)
	{
		getBoundingBox().setHeight(height);
	}
	
	public void setWidth(float width)
	{
		getBoundingBox().setWidth(width);
	}
	
	// etc
	public void copy(Entity entity)
	{
		name = entity.name;
		zoneID = entity.zoneID;
		direction = entity.direction;
		jumpState = entity.jumpState;
		boundingBox.copy(entity.boundingBox);
	}
	
	public void iterateJumpState()
	{
		setJumpState(jumpState.getNext());
	}
}
