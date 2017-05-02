package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Player;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.logic.CollisionMath;
import dab.server.world.tile.TeleporterTile;
import dab.server.world.tile.TileLocation;

public class ZoneTransitions implements Component
{
	@Override
	public void invoke()
	{
		Iterator<Player> players = Server.getPlayerList().getPlayerIterator();
		while (players.hasNext())
		{
			processPossibleZoneTransition(players.next());
		}
	}
	
	private void processPossibleZoneTransition(Player player)
	{
		if (player.isDirty()) // If player hasn't moved, don't check
		{
			TileLocation teleporterSrc = getPlayerTeleporterSrc(player);
			if (teleporterSrc != null) // player on teleporter
			{
				TileLocation dest = ((TeleporterTile) Server.getTileRegistry().get("dab:tile:teleporter"))
						                .getDestination(teleporterSrc);
				player.getBoundingBox().setMidPoint(dest.getLocationf());
				player.setZoneID(dest.getZoneID());
				player.setDirty(true);
			}
		}
	}
	
	// returns null if player is not on a teleporter
	private TileLocation getPlayerTeleporterSrc(Player player)
	{
		return CollisionMath.getTeleporterTileLocationsCollidingWithEntity(player)
				            .stream()
				            .findAny()
				            .map(loc -> new TileLocation(player.getZoneID(), loc))
				            .orElse(null);
	}
}
