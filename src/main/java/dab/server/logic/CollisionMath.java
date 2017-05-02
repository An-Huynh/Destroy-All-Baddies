package dab.server.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joml.Vector2f;
import org.joml.Vector2i;

import dab.common.entity.Entity;
import dab.common.entity.attributes.Direction;
import dab.common.physics.AABB;
import dab.common.physics.AABBMath;
import dab.server.Server;
import dab.server.world.zone.Zone;

public class CollisionMath
{
	public static AABB calcDirectionUpdatedAABB(Entity entity)
	{
		AABB boundingBox = new AABB();
		boundingBox.copy(entity.getBoundingBox());
		Vector2f offset = getDirectionOffset(entity.getDirection());
		offset.mul(entity.getMovementSpeedModifier());
		AABBMath.add(boundingBox, offset);
		return boundingBox;
	}
	
	public static AABB calcGravityUpdatedAABB(Entity entity)
	{
		AABB boundingBox = new AABB();
		boundingBox.copy(entity.getBoundingBox());
		addOffsetByDirection(boundingBox, Direction.DOWN);
		return boundingBox;
	}
	
	public static AABB calcJumpUpdatedAABB(Entity entity)
	{
		Vector2f jumpOffset = getDirectionOffset(Direction.UP);
		AABB boundingBox = new AABB();
		boundingBox.copy(entity.getBoundingBox());
		AABBMath.add(boundingBox, jumpOffset.mul(entity.getJumpState().getDistanceModifier()));
		return boundingBox;
	}
	
	public static boolean entityCollidesWithSolidTiles(Entity entity)
	{
		Zone zone = Server.getZoneRegistry().get(entity.getZoneID());
		return playerCollidesWithSolidTilesInZone(entity, zone);
	}
	
	public static boolean entityCollidesWithNonSolidTiles(Entity entity)
	{
		Zone zone = Server.getZoneRegistry().get(entity.getZoneID());
		return playerCollidesWithNonSolidTilesInZone(entity, zone);
	}
	
	public static boolean futureDirectionAABBSafe(Entity entity)
	{
		AABB possibleAABB = calcDirectionUpdatedAABB(entity);
		Entity tempEntity = new Entity();
		tempEntity.copy(entity);
		tempEntity.setBoundingBox(possibleAABB);
		return !entityCollidesWithSolidTiles(tempEntity);
	}
	
	public static boolean futureJumpAABBSafe(Entity entity)
	{
		Vector2f jumpOffset = getDirectionOffset(Direction.UP);
		jumpOffset.mul(entity.getJumpState().getDistanceModifier());
		Entity tempEntity = new Entity();
		tempEntity.copy(entity);
		tempEntity.getBoundingBox().getMidPoint().add(jumpOffset);
		return !entityCollidesWithSolidTiles(tempEntity);
	}
	
	public static boolean futureGravityAABBSafe(Entity entity)
	{
		AABB possibleAABB = calcGravityUpdatedAABB(entity);
		Entity tempEntity = new Entity();
		tempEntity.copy(entity);
		tempEntity.setBoundingBox(possibleAABB);
		return !entityCollidesWithSolidTiles(tempEntity);
	}
	
	public static List<Vector2i> getTileLocationsCollidingWithEntity(Entity entity)
	{
		Zone zone = Server.getZoneRegistry().get(entity.getZoneID());
		return getPossiblePlayerCollisionArea(entity)
				   .stream()
				   .filter(loc -> zone.getTile(loc) != null)
				   .filter(loc -> AABBMath.aabbCollides(zone.getTileAABB(loc), entity.getBoundingBox()))
				   .collect(Collectors.toList());
	}
	
	public static List<Vector2i> getTeleporterTileLocationsCollidingWithEntity(Entity entity)
	{
		Zone zone = Server.getZoneRegistry().get(entity.getZoneID());
		return getTileLocationsCollidingWithEntity(entity)
				   .stream()
				   .filter(loc -> zone.getTile(loc).getName().equals("dab:tile:teleporter"))
				   .collect(Collectors.toList());
	}
	
	public static void addOffsetByDirection(AABB boundingBox, Direction direction)
	{
		Vector2f offset = getDirectionOffset(direction);
		AABBMath.add(boundingBox, offset);
		
	}
	
	public static boolean playerCollidesWithSolidTilesInZone(Entity entity, Zone zone)
	{
		for (Vector2i tileLoc : getPossiblePlayerCollisionArea(entity))
		{
			if (zone.getTile(tileLoc) != null && zone.getTile(tileLoc).isSolid())
			{
				if (AABBMath.aabbCollides(zone.getTileAABB(tileLoc), entity.getBoundingBox()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean playerCollidesWithNonSolidTilesInZone(Entity entity, Zone zone)
	{
		for (Vector2i tileLoc : getPossiblePlayerCollisionArea(entity))
		{
			if (zone.getTile(tileLoc) != null && !zone.getTile(tileLoc).isSolid())
			{
				if (AABBMath.aabbCollides(zone.getTileAABB(tileLoc), entity.getBoundingBox()))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<Vector2i> getPossiblePlayerCollisionArea(Entity entity)
	{
		ArrayList<Vector2i> possibleCollisionLocations = new ArrayList<Vector2i>();
		Vector2i entityLocation = entity.getApproxTileLocation();
		for (int x = entityLocation.x - 1; x <= entityLocation.x + 1; ++x)
		{
			for (int y = entityLocation.y - 1; y <= entityLocation.y + 1; ++y)
			{
				possibleCollisionLocations.add(new Vector2i(x, y));
			}
		}
		return possibleCollisionLocations;
	}
	
	public static Vector2f getDirectionOffset(Direction direction)
	{
		Vector2f offset = new Vector2f();
		switch (direction)
		{
			case LEFT:  offset.set(-0.1f,  0.0f); break;
			case UP:    offset.set( 0.0f,  0.1f); break;
			case RIGHT: offset.set( 0.1f,  0.0f); break;
			case DOWN:  offset.set( 0.0f, -0.1f); break;
			case NONE:  offset.set( 0.0f,  0.0f); break;
			default: offset.set(0.0f, 0.0f);
		}
		return offset;
	}
}
