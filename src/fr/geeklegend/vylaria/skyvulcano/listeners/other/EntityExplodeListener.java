package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class EntityExplodeListener implements Listener
{
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event)
	{
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		} else if (GameState.isState(GameState.GAME))
		{
			for (Block blocks : event.blockList())
			{
				for (Player players : Bukkit.getOnlinePlayers())
				{
					Teams pt = TeamsManager.getTeam(players);
					Cuboid ptc = pt.getHeartCuboid();
					
					if (blocks.getType() == Material.WOOL)
					{
						pt.setHeartHp(pt.getHeartHp() - blocks.getDrops().size());
					}
				}
				
				if (blocks.getType() != Material.MONSTER_EGG)
				{
					blocks.setType(Material.AIR);					
				}
			}
		}
	}

}
