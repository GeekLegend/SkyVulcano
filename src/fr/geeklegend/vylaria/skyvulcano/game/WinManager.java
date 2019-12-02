package fr.geeklegend.vylaria.skyvulcano.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;

public class WinManager
{

	private String teamName;

	public WinManager(String teamName)
	{
		this.teamName = teamName;
	}

	public void check()
	{
		GameState.setState(GameState.FINISH);
		
		Bukkit.getScheduler().cancelAllTasks();
		
		Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.game.win").replace("%teamName%", teamName));

		Bukkit.getScheduler().runTaskLater(SkyVulcano.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				for (Player players : Bukkit.getOnlinePlayers())
				{
					VylariaAPI.getInstance().getBungeeChannelApi().connect(players, "lobby");
				}
			}
		}, 100L);
		
		Bukkit.getScheduler().runTaskLater(SkyVulcano.getInstance(), new Runnable()
		{
			@Override
			public void run()
			{
				Bukkit.getServer().shutdown();
			}
		}, 120L);
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

}
