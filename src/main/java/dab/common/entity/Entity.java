package dab.common.entity;

import java.io.Serializable;

import org.joml.Vector2f;
import org.joml.Vector2i;

import dab.common.entity.attributes.Direction;
import dab.common.entity.attributes.JumpState;
import dab.common.physics.AABB;

/**
 * This class is all entities in the entire game. From enemies to players,
 * this class is the pass. As such, it contains many things that all entities
 * need.
 * 
 * @author Eli Irvin
 */
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
	
	/**
	 * Default Constructor for an Entity
	 */
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
	
	/**
	 * Constructor for an Entity that takes in a name
	 * 
	 * @param name - the name of the Entity
	 */
	public Entity(String name)
	{
		this();
		this.name = name;
	}
	
	/**
	 * Gets the Entity name
	 * 
	 * @return the name of the Entity
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the current zoneID for the Entity
	 * 
	 * @return the zoneID of the Entity
	 */
	public String getZoneID()
	{
		return zoneID;
	}
	
	/**
	 * Gets the current Direction for the Entity
	 * 
	 * @return the Direction of the Entity
	 */
	public Direction getDirection()
	{
		return direction;
	}
	
	/**
	 * Gets the current JumpState for the Entity
	 * 
	 * @return the JumpState of the Entity
	 */
	public JumpState getJumpState()
	{
		return jumpState;
	}
	
	/**
	 * Gets the AABB Bounding Box for the Entity
	 * 
	 * @return the AABB Bounding Box of the Entity
	 */
	public AABB getBoundingBox()
	{
		return boundingBox;
	}
	
	/**
	 * Gets the current location of the Entity
	 * 
	 * @return the Vector2f of the Entity's location
	 */
	public Vector2f getLocation()
	{
		return boundingBox.getMidPoint();
	}
	
	/**
	 * Gets the approximate tile position of the Entity based on it's
	 * Bounding Box
	 *  
	 * @return the Vector2i of the Entity's approximate tile location
	 */
	public Vector2i getApproxTileLocation()
	{
		return new Vector2i((int) boundingBox.getMidPoint().x, (int) boundingBox.getMidPoint().y);
	}
	
	/**
	 * Checks whether the Entity is going in any direction
	 * 
	 * @return True if the Entity's Direction is not NONE | False otherwise
	 */
	public boolean hasDirection()
	{
		return !direction.equals(Direction.NONE);
	}
	
	/**
	 * Checks whether the Entity is currently jumping
	 * 
	 * @return True if the Entity's JumpState is not GROUND | False otherwise
	 */
	public boolean isJumping()
	{
		return !jumpState.equals(JumpState.GROUND);
	}
	
	/**
	 * Gets the movementSpeedModifier of the Entity. This is how fast the
	 * entity moves relative to a player
	 * 
	 * @return the movement speed modifier of the Entity
	 */
	public float getMovementSpeedModifier()
	{
		return movementSpeedModifier;
	}
	
	/**
	 * Gets whether or not the Entity has changed
	 * 
	 * @return True if the Entity is dirty | False otherwise
	 */
	public boolean isDirty()
	{
		return dirty;
	}
	
	/**
	 * Sets the Entity's name
	 * 
	 * @param name - the new name of the Entity
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the ZoneID of the Entity
	 * 
	 * @param zoneID - the new zoneID of the Entity
	 */
	public void setZoneID(String zoneID)
	{
		this.zoneID = zoneID;
	}
	
	/**
	 * Sets the Direction of the Entity
	 * 
	 * @param direction - the new Direction
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
	
	/**
	 * Sets the JumpState of the Entity
	 * 
	 * @param jumpState - the new JumpState
	 */
	public void setJumpState(JumpState jumpState)
	{
		this.jumpState = jumpState;
	}
	
	/**
	 * Sets the AABB Bounding Box of the Entity
	 * 
	 * @param boundingBox - the new AABB bounding box
	 */
	public void setBoundingBox(AABB boundingBox)
	{
		this.boundingBox.copy(boundingBox);
	}
	
	/**
	 * Sets whether an Entity needs to be updated
	 * 
	 * @param dirty - the dirty state of the Entity
	 */
	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}
	
	/**
	 * Sets the Vector2f location of the Entity
	 * 
	 * @param location - the new Vector2f location
	 */
	public void setLocation(Vector2f location)
	{
		getBoundingBox().getMidPoint().set(location).add(0.5f, 0.5f);
	}
	
	/**
	 * Sets the location of the Entity based on an x and y
	 * 
	 * @param x - the new x position of the Entity
	 * @param y - the new y position of the Entity
	 */
	public void setLocation(float x, float y)
	{
		getBoundingBox().getMidPoint().set(x, y).add(0.5f, 0.5f);
	}
	
	/**
	 * Sets the movementSpeedModifier for the Entity
	 * 
	 * @param movementSpeedModifier - the new movement speed modifier
	 */
	public void setMovementSpeedModifier(float movementSpeedModifier)
	{
		this.movementSpeedModifier = movementSpeedModifier;
	}
	
	/**
	 * Sets the height of the Entity
	 * 
	 * @param height - the new height
	 */
	public void setHeight(float height)
	{
		getBoundingBox().setHeight(height);
	}
	
	/**
	 * Sets the width of the Entity
	 * 
	 * @param width - the new width
	 */
	public void setWidth(float width)
	{
		getBoundingBox().setWidth(width);
	}
	
	/**
	 * Copies an Entity into this Entity
	 * 
	 * @param entity - the Entity to be copied
	 */
	public void copy(Entity entity)
	{
		name = entity.name;
		zoneID = entity.zoneID;
		direction = entity.direction;
		jumpState = entity.jumpState;
		boundingBox.copy(entity.boundingBox);
	}
	
	/**
	 * Iterates the JumpState of the Entity to the next position
	 */
	public void iterateJumpState()
	{
		setJumpState(jumpState.getNext());
	}
}
