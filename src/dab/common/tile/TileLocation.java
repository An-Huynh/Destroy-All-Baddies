package dab.common.tile;

import java.util.Objects;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class TileLocation {
	private String zone;
	private Vector2i location;
	
	// constructor
	public TileLocation() {}
	
	public TileLocation(String zone, Vector2i location) {
		this.location = location;
		this.zone = zone;
	}
	
	public TileLocation(String zone, int x, int y) {
		this.location = new Vector2i(x, y);
		this.zone = zone;
	}
	
	// Accessors
	public String getZone() {
		return zone;
	}
	
	public Vector2i getLocation() {
		return location;
	}
	
	public Vector2f getLocationfv() {
		return new Vector2f(location.x + 0.5f, location.y + 0.5f);
	}
	
	// Mutators
	public void setZone(String zoneName) {
		zone = zoneName;
	}
	
	public void setLocation(Vector2i location) {
		this.location = location;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    
	    TileLocation tl = (TileLocation) obj;
	    if (!zone.equals(tl.zone)) {
	    	return false;
	    }
	    
	    if (location.x != tl.location.x && location.y != tl.location.y) {
	    	return false;
	    }
	    
	    return true;
	}
	
	public int hashCode() {
        return Objects.hash(zone, location.x, location.y);
    }
	
}
