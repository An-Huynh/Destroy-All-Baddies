package dab.server.logic.component.position;

import java.util.ArrayList;
import java.util.Iterator;

import org.joml.Vector2f;
import org.joml.Vector2i;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;
import dab.common.logic.Collision;
import dab.common.physics.AABB;
import dab.common.registry.ZoneRegistry;
import dab.common.tile.Tile;
import dab.common.zone.Zone;

public class MovementCollisionChecker {

	public static AABB calculateNextPlayerAABB(Player player) {
		AABB boundingBox = new AABB().copy(player.getAABB());
		return updateAABBByDirection(boundingBox, player.getDirection());
	}
	
	public static AABB calculateNextPlayerGravityAABB(Player player) {
		AABB boundingBox = new AABB().copy(player.getAABB());
		return updateAABBByDirection(boundingBox, Direction.DOWN);
	}
	
	public static AABB calculateNextPlayerJumpAABB(Player player) {
		AABB boundingBox = new AABB().copy(player.getAABB());
		return updateAABBByDirection(boundingBox, Direction.UP, getPlayerJumpModifier(player));
	}
	
	public static boolean checkPlayerZoneSolidCollision(Player player) {
		Zone playerZone = getPlayerZone(player);
		return checkPlayerCollisionSolidZoneTiles(player, playerZone);
	}
	
	public static boolean checkPlayerZoneCollisionIgnoreSolid(Player player) {
		Zone playerZone = getPlayerZone(player);
		return checkPlayerCollisionZoneTilesIgnoreSolid(player, playerZone);
	}
	
	public static boolean checkFuturePlayerZoneSolidCollision(Player player) {
		AABB possibleAABB = calculateNextPlayerAABB(player);
		Player tempPlayer = getPlayerCopy(player);
		setPlayerAABB(tempPlayer, possibleAABB);
		return checkPlayerZoneSolidCollision(tempPlayer);
	}
	
	public static boolean checkFuturePlayerZoneCollisionNonSolid(Player player) {
		AABB possibleAABB = calculateNextPlayerAABB(player);
		Player tempPlayer =  getPlayerCopy(player);
		setPlayerAABB(tempPlayer, possibleAABB);
		return checkPlayerZoneCollisionIgnoreSolid(tempPlayer);
	}
	
	public static boolean isPlayerMoving(Player player) {
		return player.getDirection() != Direction.NONE;
	}
	

	public static ArrayList<Tile> getTilesCollidingWithPlayerIgnoreSolid(Player player) {
		ArrayList<Vector2i> possibleCollisionTileLocations = getPossiblePlayerCollisionArea(player);
		return getEachCollidingTile(player, possibleCollisionTileLocations);
  }

	public static boolean checkFuturePlayerGravityCollision(Player player) {
		AABB possibleAABB = calculateNextPlayerGravityAABB(player);
		Player tempPlayer = getPlayerCopy(player);
		setPlayerAABB(tempPlayer, possibleAABB);
		return checkPlayerZoneSolidCollision(tempPlayer);
	}
	
	public static boolean checkFuturePlayerJumpCollision(Player player) {
		AABB possibleAABB = calculateNextPlayerJumpAABB(player);
		Player tempPlayer = getPlayerCopy(player);
		setPlayerAABB(tempPlayer, possibleAABB);
		return checkPlayerZoneSolidCollision(tempPlayer);
	}
	
	private static AABB updateAABBByDirection(AABB boundingBox, Direction direction) {
		Vector2f offset = getOffsetByDirection(direction);
		return addOffsetToAABB(boundingBox, offset);
	}
	
	private static AABB updateAABBByDirection(AABB boundingBox, Direction direction, float movementScale) {
		Vector2f offset = getOffsetByDirection(direction);
		scaleOffsetVector(offset, movementScale);
		return addOffsetToAABB(boundingBox, offset);
	}
	
	private static Vector2f scaleOffsetVector(Vector2f offset, float offsetScaler) {
		offset.x *= offsetScaler;
		offset.y *= offsetScaler;
		return offset;
	}
	
	private static AABB addOffsetToAABB(AABB boundingBox, Vector2f offset) {
		return boundingBox.addOffset(offset.x, offset.y);
	}
	
	private static final Vector2f getOffsetByDirection(Direction direction) {
		Vector2f offset = new Vector2f();
		switch (direction) {
			case LEFT:
				offset.set(-0.1f, 0.0f);
				break;
			case RIGHT:
				offset.set(0.1f, 0.0f);
				break;
			case DOWN:
				offset.set(0.0f, -0.1f);
				break;
			case UP:
				offset.set(0.0f, 0.1f);
				break;
			default:
				offset.set(0.0f, 0.0f);
		}
		return offset;
	}
	
	private static boolean checkPlayerCollisionSolidZoneTiles(Player player, Zone zone) {
		Iterator<Vector2i> possibleTileLocations = getPossiblePlayerCollisionArea(player)
							                           .iterator();
		while (possibleTileLocations.hasNext()) {
			if (checkCollisionPlayerSolidTileLocation(player, possibleTileLocations.next())) {
				return true;
			}
		}
		return false;
	}
	
	private static ArrayList<Vector2i> getPossiblePlayerCollisionArea(Player player) {
		ArrayList<Vector2i> possibleCollisionLocations = new ArrayList<Vector2i>();
		Vector2i playerLocation = getPlayerLocation(player);
		for (int x = playerLocation.x - 1; x < playerLocation.x + 2; ++x) {
			for (int y = playerLocation.y - 1; y < playerLocation.y + 2; ++y) {
				possibleCollisionLocations.add(new Vector2i(x, y));
			}
		}
		return possibleCollisionLocations;
	}
	
	private static Vector2i getPlayerLocation(Player player) {
		Vector2i playerLocation = new Vector2i();
		int playerXCoord = convertToInt(player.getCenter().x);
		int playerYCoord = convertToInt(player.getCenter().y);
		playerLocation.set(playerXCoord, playerYCoord);
		return playerLocation;
	}
	
	private static int convertToInt(float value) {
		return (int) Math.floor(value);
	}
	
	private static boolean checkCollisionPlayerSolidTileLocation(Player player, Vector2i tileLocation) {
		Zone playerZone = getPlayerZone(player);
		Tile tile = getTileAtPlayerZone(playerZone, tileLocation);
		AABB tileAABB = getTileAABBAtPlayerZone(playerZone, tileLocation);
		return tile != null && tile.isSolid() && checkAABBCollision(player.getAABB(), tileAABB);
	}
	
	private static boolean checkAABBCollision(AABB a1, AABB a2) {
		return Collision.testCollision(a1, a2);
	}
	
	private static Zone getPlayerZone(Player player) {
		return ZoneRegistry.get(player.getZone());
	}
	
	private static AABB getTileAABBAtPlayerZone(Zone zone, Vector2i tileLocation) {
		return zone.getTileAABB(tileLocation.x, tileLocation.y);
	}
	
	private static Tile getTileAtPlayerZone(Zone zone, Vector2i tileLocation) {
		return zone.getTile(tileLocation.x, tileLocation.y);
	}
	
	private static boolean checkPlayerCollisionZoneTilesIgnoreSolid(Player player, Zone zone) {
		Iterator<Vector2i> possibleTileLocations = getPossiblePlayerCollisionArea(player)
				                                       .iterator();
		while (possibleTileLocations.hasNext()) {
			if (checkCollisionPlayerTileLocationIgnoreSolid(player, possibleTileLocations.next())) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkCollisionPlayerTileLocationIgnoreSolid(Player player, Vector2i tileLocation) {
		Zone playerZone = getPlayerZone(player);
		Tile tile = getTileAtPlayerZone(playerZone, tileLocation);
		AABB tileAABB = getTileAABBAtPlayerZone(playerZone, tileLocation);
		return tile != null && checkAABBCollision(player.getAABB(), tileAABB);
	}
	
	private static float getPlayerJumpModifier(Player player) {
		return player.getJumpState().getJumpDistanceModifier();
	}
	
	private static Player getPlayerCopy(Player player) {
		Player playerCopy = new Player();
		playerCopy.copy(player);
		return playerCopy;
	}
	
	private static Player setPlayerAABB(Player player, AABB aabb) {
		player.getAABB().copy(aabb);
		return player;
	}
	
	private static ArrayList<Tile> getEachCollidingTile(Player player, ArrayList<Vector2i> tileLocations) {
		ArrayList<Tile> collidingTiles = new ArrayList<Tile>();
		for (Vector2i tileLocation : tileLocations) {
			if (checkCollisionPlayerTileLocationIgnoreSolid(player, tileLocation)) {
				collidingTiles.add(getTileAtPlayerZone(getPlayerZone(player), tileLocation));
			}
		}
		return collidingTiles;
	}
	
}
