package fr.geeklegend.vylaria.skyvulcano.tablist;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class Tablist
{

	private static Scoreboard scoreboard;

	public static void setScoreboard()
	{
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

		scoreboard.registerNewTeam("000Green");
		scoreboard.registerNewTeam("001Magenta");

		scoreboard.getTeam("000Green").setPrefix("" + Teams.GREEN.getNameColor());
		scoreboard.getTeam("001Magenta").setPrefix("" + Teams.MAGENTA.getNameColor());

		for (Player players : Bukkit.getOnlinePlayers())
		{
			setTeams(players);
		}
	}

	private static void setTeams(Player player)
	{
		Teams team = TeamsManager.getTeam(player);
		String teamName = null;

		if (team == Teams.GREEN)
		{
			teamName = "000Green";
		} else if (team == Teams.MAGENTA)
		{
			teamName = "001Magenta";
		}
		
		scoreboard.getTeam(teamName).addPlayer(player);

		player.setScoreboard(scoreboard);
	}

}
