package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class AsyncPlayerChatListener implements Listener
{
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event)
	{
		Player player = event.getPlayer();
		
		if (GameState.isState(GameState.GAME))
		{
			Teams team = TeamsManager.getTeam(player);
			
			String message = event.getMessage();
			
			if (!message.startsWith("!"))
			{
				event.setCancelled(true);
				
				for (Player teams : team.getPlayers())
				{
					if (team.getPlayers().contains(teams))
					{
						Player players = teams.getPlayer();
						
						players.sendMessage(team.getNameColor() + "(Equipe) " + players.getName() + ": §f" + message);
					}
				}
			} else
			{
				event.setCancelled(true);

				Bukkit.broadcastMessage(team.getNameColor() + "(Global) " + player.getName() + ": §f" + message.replace("!", ""));
			}
		}
	}
	
}
