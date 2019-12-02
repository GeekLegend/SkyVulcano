package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.geeklegend.vylaria.api.mysql.data.manager.PlayerDataManager;
import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.schedulers.WinScheduler;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class PlayerQuitListener implements Listener
{

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		PlayerDataManager.savePlayerData(player);
		
		SkyVulcano.getInstance().getScoreboardManager().onLogout(player);
		
		TeamsManager.remove(player);

		if (GameState.isState(GameState.WAITING))
		{
			event.setQuitMessage(Config.getDefaultConfig().getString("messages.quit").replace("&", "§")
					.replace("%playername%", player.getName())
					.replace("%online%", "" + (Bukkit.getOnlinePlayers().size() - 1))
					.replace("%maxonline%", "" + Bukkit.getMaxPlayers()));
		} else if (GameState.isState(GameState.GAME))
		{
			if (!WinScheduler.isRunning())
			{
				WinScheduler winScheduler = new WinScheduler();
				winScheduler.setRunning(true);
				winScheduler.runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
			}

			event.setQuitMessage(null);
		}
	}
	
}
