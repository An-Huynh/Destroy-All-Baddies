package dab.server.world.tile;

import java.util.Objects;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class TileLocation
{
	private String zoneID;
	private Vector2i location;
	
	// Constructors
	public TileLocation(String zoneID, Vector2i location)
	{
		this.zoneID = zoneID;
		this.location = location;
	}
	
	public TileLocation(String zoneID, int x, int y)
	{
		this.zoneID = zoneID;
		this.location = new Vector2i(x, y);
	}
	
	// Accessors
	public String getZoneID()
	{
		return zoneID;
	}
	
	public Vector2i getLocationi()
	{
		return location;
	}
	
	public Vector2f getLocationf()
	{
		return new Vector2f(location.x+0.5f, location.y+0.5f);
	}
	
	// Mutators
	public void setZoneID(String zoneID)
	{
		this.zoneID = zoneID;
	}
	
	public void setLocation(Vector2i location)
	{
		this.location.set(location);
	}
	
	public void setLocation(int x, int y)
	{
		this.location.set(x, y);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj.getClass().equals(getClass()))
		{
			TileLocation object = (TileLocation) obj;
			if (object.zoneID.equals(zoneID) && object.location.x == location.x &&
				object.location.y == location.y)
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(zoneID, location);
	}
	
}
