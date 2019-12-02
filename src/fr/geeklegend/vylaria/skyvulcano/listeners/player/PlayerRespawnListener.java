package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.schedulers.RespawnScheduler;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class PlayerRespawnListener implements Listener
{

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		Teams team = TeamsManager.getTeam(player);
		
		if (GameState.isState(GameState.GAME))
		{
			if (!team.isHearthBroken())
			{
				GameManager.addRespawn(player);
				
				if (!RespawnScheduler.isRunning())
				{
					RespawnScheduler respawnScheduler = new RespawnScheduler();
					respawnScheduler.setPlayer(player);
					respawnScheduler.setRunning(true);
					respawnScheduler.runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
				}
				
				event.setRespawnLocation(team.getSpawnLocation());
			} else 
			{
				if (!GameManager.spectatorsContains(player))
				{
					GameManager.addSpectator(player);

					TeamsManager.remove(player);
					
					player.setGameMode(GameMode.SPECTATOR);
					player.getInventory().clear();
					
					List<Player> players = new ArrayList<Player>();
					for (Player pls : Bukkit.getOnlinePlayers())
					{
						players.add(pls);
						Player randomPlayer = players.get(new Random().nextInt(players.size()));
						event.setRespawnLocation(randomPlayer.getLocation());
					}
				}
			}
		}
	}
	
}
