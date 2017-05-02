package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.entity.Entity;
import dab.common.entity.Player;
import dab.common.entity.attributes.JumpState;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.logic.CollisionMath;

public class Movement implements Component
{
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
	
	public void handleDirectionUpdate(Entity Entity)
	{
		if (CollisionMath.futureDirectionAABBSafe(Entity))
		{
			Entity.setBoundingBox(CollisionMath.calcDirectionUpdatedAABB(Entity));
			Entity.setDirty(true);
		}
	}
	
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
	
	private void handleGravityUpdate(Entity Entity)
	{
		if (CollisionMath.futureGravityAABBSafe(Entity))
		{
			Entity.setBoundingBox(CollisionMath.calcGravityUpdatedAABB(Entity));
			Entity.setDirty(true);
		}
	}
}