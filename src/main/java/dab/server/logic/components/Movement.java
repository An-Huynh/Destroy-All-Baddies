package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.entity.Entity;
import dab.common.entity.Player;
import dab.common.entity.attributes.JumpState;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.logic.CollisionMath;

/**
 * This class handles the movement of the player character
 * and of the enemies
 * 
 * @author Eli Irvin
 */
public class Movement implements Component
{
	/**
	 * Updates the position of all players and all enemies to their
	 * current position
	 */
	@Override
	public void invoke()
	{
		// Handle Player Movements
		Iterator<Player> players = Server.getPlayerList().getPlayerIterator();
		while (players.hasNext())
		{
			updateEntityPosition(players.next());
		}
		
		// Handle Enemies
		Iterator<Enemy> enemies = Server.getEnemyList().getUpdatableEnemyIterator();
		while (enemies.hasNext())
		{
			updateEntityPosition(enemies.next());
		}
	}
	
	/**
	 * If the Entity has a direction, this will handle an update for that.
	 * If the Entity is currently jumping, this will handle an update
	 * for that. It will then handle an update for gravity.
	 * 
	 * @param Entity - the Entity to be updated
	 */
	private void updateEntityPosition(Entity Entity)
	{
		if (Entity.hasDirection())
		{
			handleDirectionUpdate(Entity);
		}
		if (Entity.isJumping())
		{
			handleJumpUpdate(Entity);
		}
		handleGravityUpdate(Entity);
	}
	
	/**
	 * This checks if the future direction of the Entity is safe,
	 * and then sets it AABB to the newly updated one based on 
	 * its direction. It then sets it as dirty, so its known
	 * to update others of its change.
	 * 
	 * @param Entity - the Entity to be updated
	 */
	public void handleDirectionUpdate(Entity Entity)
	{
		if (CollisionMath.futureDirectionAABBSafe(Entity))
		{
			Entity.setBoundingBox(CollisionMath.calcDirectionUpdatedAABB(Entity));
			Entity.setDirty(true);
		}
	}
	
	/**
	 * This checks if the location of the jump is safe, and then if it is
	 * sets the AABB accordingly and iterates through the JumpState. If it
	 * is not safe, the Entity will be set to the ground. Either way,
	 * the entity will be set to dirty so its known to update others.
	 * 
	 * @param Entity - the Entity to be updated
	 */
	private void handleJumpUpdate(Entity Entity)
	{
		if (CollisionMath.futureJumpAABBSafe(Entity))
		{
			Entity.setBoundingBox(CollisionMath.calcJumpUpdatedAABB(Entity));
			Entity.iterateJumpState();
		}
		else
		{
			Entity.setJumpState(JumpState.GROUND);
		}
		Entity.setDirty(true);
	}
	
	/**
	 * This checks if the future AABB of Gravity for this Entity is
	 * safe, and if it is it will set its AABB accordingly. It will
	 * then be set to dirty so its known to update others.
	 * 
	 * @param Entity - the Entity to be updated
	 */
	private void handleGravityUpdate(Entity Entity)
	{
		if (CollisionMath.futureGravityAABBSafe(Entity))
		{
			Entity.setBoundingBox(CollisionMath.calcGravityUpdatedAABB(Entity));
			Entity.setDirty(true);
		}
	}
}