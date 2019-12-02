package fr.geeklegend.vylaria.skyvulcano.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.schedulers.StartScheduler;
import fr.geeklegend.vylaria.skyvulcano.scoreboard.objective.ObjectiveSign;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

/*
 * This file is part of SamaGamesVylariaAPI.
 *
 * SamaGamesVylariaAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesVylariaAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesVylariaAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard
{
	private Player player;
	private final UUID uuid;
	private final ObjectiveSign objectiveSign;

	public PersonalScoreboard(Player player)
	{
		this.setPlayer(player);
		uuid = player.getUniqueId();
		objectiveSign = new ObjectiveSign("sidebar", "DevPlugin");

		reloadData();
		objectiveSign.addReceiver(player);
	}

	public void reloadData()
	{
	}

	public void setLines(String ip)
	{
		Teams team = TeamsManager.getTeam(player);

		objectiveSign.setDisplayName("§7- §eSky Vulcano §7-");
		if (GameState.isState(GameState.WAITING))
		{
			objectiveSign.setLine(0, "§6 ");
			objectiveSign.setLine(1,
					"§7Joueurs » §a" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
			objectiveSign.setLine(2, "§2 ");
			if (team != null)
			{
				objectiveSign.setLine(3, "§7Equipe » " + team.getNameColor() + team.getName());
			} else
			{
				objectiveSign.setLine(3, "§7Equipe » §cAucune");
			}
			objectiveSign.setLine(4, "§1 ");
			if (!StartScheduler.isRunning())
			{
				objectiveSign.setLine(5, "§cEn attente...");
			} else
			{
				objectiveSign.setLine(5, "§e" + StartScheduler.getTimer() + " seconde(s)");
			}
			objectiveSign.setLine(6, "§4 ");
			objectiveSign.setLine(7, "§7Id §f" + Bukkit.getMotd());
			objectiveSign.setLine(8, "§3 ");
			objectiveSign.setLine(9, ip);
		} else if (GameState.isState(GameState.GAME))
		{
			Cuboid greenHeartCuboid = Teams.GREEN.getHeartCuboid();
			Cuboid magentaHeartCuboid = Teams.MAGENTA.getHeartCuboid();

			objectiveSign.setLine(0, "§2§6 ");
			objectiveSign.setLine(1,
					Teams.GREEN.getNameColor() + Teams.GREEN.getName() + " §7(§e" + Teams.GREEN.getSize() + "§7)");
			for (Block greenHeartBlocks : greenHeartCuboid)
			{
				if (greenHeartBlocks.getType() != Material.AIR)
				{
					int ghcs = Teams.GREEN.getHeartHp();

					if (ghcs >= 100)
					{
						objectiveSign.setLine(2, "§1§a█████ §7" + ghcs + "% §c❤");
					} else if (ghcs == 75)
					{
						objectiveSign.setLine(2, "§1§e████ §7" + ghcs + "% §c❤");
					} else if (ghcs == 50)
					{
						objectiveSign.setLine(2, "§1§6███ §7" + ghcs + "% §c❤");
					} else if (ghcs == 25)
					{
						objectiveSign.setLine(2, "§1§c██ §7" + ghcs + "% §c❤");
					} else if (ghcs == 0)
					{
						objectiveSign.setLine(2, "§1§cCoeur détruit");
					}
				}
			}
			objectiveSign.setLine(3, "§2§2 ");
			objectiveSign.setLine(4, Teams.MAGENTA.getNameColor() + Teams.MAGENTA.getName() + " §7(§e"
					+ Teams.MAGENTA.getSize() + "§7)");
			for (Block magentaHeartBlocks : magentaHeartCuboid)
			{
				if (magentaHeartBlocks.getType() != Material.AIR)
				{
					int mhcs = Teams.MAGENTA.getHeartHp();

					if (mhcs >= 100)
					{
						objectiveSign.setLine(5, "§2§a█████ §7" + mhcs + "% §c❤");
					} else if (mhcs == 75)
					{
						objectiveSign.setLine(5, "§2§e████ §7" + mhcs + "% §c❤");
					} else if (mhcs == 50)
					{
						objectiveSign.setLine(5, "§2§6███ §7" + mhcs + "% §c❤");
					} else if (mhcs == 25)
					{
						objectiveSign.setLine(5, "§2§c██ §7" + mhcs + "% §c❤");
					} else if (mhcs == 0)
					{
						objectiveSign.setLine(5, "§2§cCoeur détruit");
					}
				}
			}
			objectiveSign.setLine(6, "§2§1 ");
			objectiveSign.setLine(7, "§7Tué(s) » §a" + player.getStatistic(Statistic.PLAYER_KILLS));
			objectiveSign.setLine(8, "§7Mort(s) » §c" + player.getStatistic(Statistic.DEATHS));
			objectiveSign.setLine(9, "§2§4 ");
			objectiveSign.setLine(10, "§7Id §f" + Bukkit.getMotd());
			objectiveSign.setLine(11, "§2§3 ");
			objectiveSign.setLine(12, ip);
		}
		objectiveSign.updateLines();

	}

	public void onLogout()
	{
		objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
	}

	public Player getPlayer()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}
}