package fr.geeklegend.vylaria.skyvulcano.teams.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;

public class TeamsManager
{

	private static List<Teams> teams = new ArrayList<Teams>();
	private static Map<Player, Teams> team = new HashMap<Player, Teams>();
	private static int teamMaxSize = Config.getDefaultConfig().getInt("teams.maxplayers");

	public static void load()
	{
		teams.add(Teams.GREEN);
		teams.add(Teams.MAGENTA);
	}

	public static void add(Player player, Teams t, boolean random)
	{
		if (!random)
		{
			if (t.getPlayers().contains(player))
			{
				player.sendMessage(Config.getDefaultConfig().getString("messages.teams.already").replace("&", "§")
						.replace("%teamcolor%", "" + t.getNameColor()).replace("%teamname%", t.getName()));
				return;
			} else
			{
				remove(player);
			}
		} else
		{
			remove(player);
		}

		if (t.getSize() >= Config.getDefaultConfig().getInt("teams.maxplayers"))
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.teams.full").replace("&", "§")
					.replace("%teamcolor%", "" + t.getNameColor()).replace("%teamname%", t.getName()));
			return;
		}

		t.addPlayer(player);
		team.put(player, t);

		if (!random)
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.teams.join").replace("&", "§")
					.replace("%teamcolor%", "" + t.getNameColor()).replace("%teamname%", t.getName()));
		}
	}

	public static void random()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			Teams team = getTeam(players);

			if (team == null)
			{
				if (Teams.GREEN.getSize() >= Teams.MAGENTA.getSize())
				{
					add(players, Teams.MAGENTA, false);
				} else
				{
					add(players, Teams.GREEN, false);
				}
			}
		}
	}

	public static void remove(Player player)
	{
		Teams t = getTeam(player);

		if (t != null)
		{
			t.removePlayer(player);
			team.remove(player);
		}
	}

	public static Teams getTeam(Player player)
	{
		return team.get(player);
	}

	public static Teams getRandom(Player player)
	{
		List<Teams> givenList = Lists.newArrayList(teams);
		int randomIndex = new Random().nextInt(givenList.size());
		Teams randomTeam = givenList.get(randomIndex);
		givenList.remove(randomIndex);
		return randomTeam;
	}

	public static int getTeamMaxSize()
	{
		return teamMaxSize;
	}

	public static List<Teams> getTeams()
	{
		return teams;
	}

}
