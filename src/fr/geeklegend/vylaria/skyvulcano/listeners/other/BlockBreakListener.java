package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.schedulers.WinScheduler;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class BlockBreakListener implements Listener
{

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
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
				} else
				{
					if (block.getType() == Material.MOB_SPAWNER)
					{
						event.setCancelled(true);
					} else if (block.getType() == Material.WOOL)
					{
						block.setType(Material.AIR);

						for (Player players : Bukkit.getOnlinePlayers())
						{
							Teams pt = TeamsManager.getTeam(players);

							if (team != pt)
							{
								Cuboid pthc = pt.getHeartCuboid();
								int pthhp = pt.getHeartHp();

								pt.removeHeartHp(1);

								if (pthhp == 1)
								{
									pt.setHeartHp(0);
								}

								if (pthhp == 75 || pthhp == 50 || pthhp == 25)
								{
									players.playSound(players.getLocation(), Sound.ENDERDRAGON_HIT, 20.0f, 20.0f);
									players.sendMessage(
											Config.getDefaultConfig().getString("messages.game.hearts.attacked")
													.replace("&", "§").replace("%heartblocks%", "" + pthhp)
													.replace("%teamcolor%", "" + pt.getNameColor())
													.replace("%teamname%", pt.getName()));
								} else if (pthhp == 0)
								{
									pt.setHeartHp(0);
									pt.setHearthBroken(true);

									pthc.getCenter().getWorld().createExplosion(pthc.getCenter(), 3.0f);

									if (!WinScheduler.isRunning())
									{
										WinScheduler winScheduler = new WinScheduler();
										winScheduler.setRunning(true);
										winScheduler.runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
