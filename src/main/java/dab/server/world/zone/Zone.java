package dab.server.world.zone;

import java.util.HashMap;
import java.util.Map;

import org.joml.Vector2i;

import dab.common.physics.AABB;
import dab.common.physics.AABBMath;
import dab.server.Server;
import dab.server.world.tile.TeleporterTile;
import dab.server.world.tile.Tile;
import dab.server.world.tile.TileLocation;
/**
 * abstract base zone class 
 * 
 * @author Cristopher Huerta
 */
public abstract class Zone
{
	private String id; //id of zone
	private Map<String, Tile> layout;//map layout
	
	// Constructor
	public Zone()
	{
		id = "";
		layout = new HashMap<String, Tile>();
	}
	
	/**
	* returns id of zone
	*/
	public String getID()
	{
		return id;
	}
	
	/**
	* initializes blocks and teleporters for
	* the graveyard zone
	* 
	* @author Cristopher Huerta
	*/
	public Tile getTile(int x, int y)
	{
		return layout.get(convertToKey(x, y));
	}
	
	/**
	* call to get tile
	*
	* @param	location	location of tile to get
	*/
	public Tile getTile(Vector2i location)
	{
		return getTile(location.x, location.y);
	}
	
	public AABB getTileAABB(int x, int y)
	{
		AABB tileAABB = new AABB();
		tileAABB.copy(getTile(x, y).getBoundingBox());
		AABBMath.add(tileAABB, x, y);
		return tileAABB;
	}
	
	public AABB getTileAABB(Vector2i location)
	{
		return getTileAABB(location.x, location.y);
	}
	
	// Mutators
	/**
	 *set id of zone given string
	* 
	* @param name	string name to set ID to
	*/
	public void setID(String name)
	{
		this.id = name;
	}
	
	/**
	* initializes blocks and teleporters for
	* the graveyard zone
	* 
	* @author Cristopher Huerta
	*/
	public void addTile(Tile tile, int x, int y)
	{
		layout.put(convertToKey(x, y), tile);
	}
	
	/**
	* call to add tile
	*
	* @param	location	location to add tile to
	*/
	public void addTile(Tile tile, Vector2i location)
	{
		layout.put(convertToKey(location), tile);
	}
	
	/**
	* checks if tile exists at the given coordinates
	*
	* @param 	x	x coord of location to check
	* @param	y	y coord of location to check
	*/
	public boolean tileExistsAt(int x, int y)
	{
		return getTile(x, y) != null;
	}
	
	/**
	* calls tile exist check
	*
	* @param location	location to check
	*/
	public boolean tileExistsAt(Vector2i location)
	{
		return tileExistsAt(location.x, location.y);
	}
	
	/**
	* adds teleporter to zone given source and destination
	*
	* 
	* @param	src		source tile being teleported from
	* @param	dest	destination tile to be teleported to
	*/
	protected void addTeleporter(TileLocation src, TileLocation dest)
	{
		TeleporterTile teleTile = (TeleporterTile) Server.getTileRegistry().get("dab:tile:teleporter");
		teleTile.addTeleportPath(src, dest);
	}
	
	// etc
	private String convertToKey(int x, int y)
	{
		return String.format("%d:%d", x, y);
	}
	
	private String convertToKey(Vector2i location)
	{
		return convertToKey(location.x, location.y);
	}
}
