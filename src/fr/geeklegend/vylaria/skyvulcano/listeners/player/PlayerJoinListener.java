package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.schedulers.StartScheduler;

public class PlayerJoinListener implements Listener
{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();

		SkyVulcano.getInstance().getScoreboardManager().onLogin(player);

		if (GameState.isState(GameState.WAITING))
		{
			setup(player);

			if (Bukkit.getOnlinePlayers().size() >= Config.getDefaultConfig().getInt("schedulers.start.minplayers"))
			{
				if (!StartScheduler.isRunning())
				{
					StartScheduler startScheduler = new StartScheduler();
					startScheduler.setRunning(true);
					startScheduler.runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
				}
			}
			
			event.setJoinMessage(Config.getDefaultConfig().getString("messages.join").replace("&", "§")
					.replace("%playername%", player.getName())
					.replace("%online%", "" + Bukkit.getOnlinePlayers().size())
					.replace("%maxonline%", "" + Bukkit.getMaxPlayers()));
		} else
		{
			if (!GameManager.spectatorsContains(player))
			{
				GameManager.addSpectator(player);

				player.setGameMode(GameMode.SPECTATOR);
				player.getInventory().clear();
				player.sendMessage(Config.getDefaultConfig().getString("messages.alreadystart").replace("&", "§"));

				event.setJoinMessage(null);
			}
		}
	}

	private void setup(Player player)
	{
		
		player.setHealth(20.0D);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setStatistic(Statistic.PLAYER_KILLS, 0);
		player.setStatistic(Statistic.DEATHS, 0);
		player.setGameMode(GameMode.valueOf(Config.getDefaultConfig().getString("setups.join.gamemode").toUpperCase()));
		player.teleport(new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
				Config.getDefaultConfig().getDouble("setups.join.spawn.x"),
				Config.getDefaultConfig().getDouble("setups.join.spawn.y"),
				Config.getDefaultConfig().getDouble("setups.join.spawn.z"),
				Config.getDefaultConfig().getInt("setups.join.spawn.yaw"),
				Config.getDefaultConfig().getInt("setups.join.spawn.pitch")));
		player.getInventory().setHelmet(new ItemStack(Material.AIR));
		player.getInventory().setChestplate(new ItemStack(Material.AIR));
		player.getInventory().setLeggings(new ItemStack(Material.AIR));
		player.getInventory().setBoots(new ItemStack(Material.AIR));

		giveItems(player);
	}

	private void giveItems(Player player)
	{
		player.getInventory().clear();
		player.getInventory().setItem(Config.getDefaultConfig().getInt("setups.join.items.teams.slot"),
				new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("setups.join.items.teams.material")
						.replace(" ", "_").toUpperCase())).setName(
								Config.getDefaultConfig().getString("setups.join.items.teams.name").replace("&", "§"))
								.toItemStack());
		player.getInventory().setItem(Config.getDefaultConfig().getInt("setups.join.items.leave.slot"),
				new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("setups.join.items.leave.material")
						.replace(" ", "_").toUpperCase())).setName(
								Config.getDefaultConfig().getString("setups.join.items.leave.name").replace("&", "§"))
								.toItemStack());
	}

}
