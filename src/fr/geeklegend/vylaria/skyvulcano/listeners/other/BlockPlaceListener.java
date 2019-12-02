package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class BlockPlaceListener implements Listener
{

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Player player = event.getPlayer();
		Block block = event.getBlock();

		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		} else if (GameState.isState(GameState.GAME))
		{
			if (block != null)
			{
				Teams team = TeamsManager.getTeam(player);
				Cuboid teamHeartCuboid = team.getHeartCuboid();

				if (teamHeartCuboid.contains(block.getLocation()))
				{
					event.setCancelled(true);
				}

				for (Player players : Bukkit.getOnlinePlayers())
				{
					Teams playersTeam = TeamsManager.getTeam(players);
					Cuboid playersTeamHeartCuboid = playersTeam.getHeartCuboid();

					if (team != playersTeam)
					{
						if (playersTeamHeartCuboid.contains(block.getLocation()))
						{
							event.setCancelled(true);
						}
					}
				}

				if (block.getType() == Material.STAINED_CLAY)
				{
					player.getItemInHand().setAmount(64);
				}
			}
		}
	}

}
