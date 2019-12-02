package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class PlayerMoveListener implements Listener
{

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();

		if (GameState.isState(GameState.WAITING))
		{
			if (player.getLocation().getY() <= 0)
			{
				player.teleport(new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
						Config.getDefaultConfig().getDouble("setups.join.spawn.x"),
						Config.getDefaultConfig().getDouble("setups.join.spawn.y"),
						Config.getDefaultConfig().getDouble("setups.join.spawn.z"),
						Config.getDefaultConfig().getInt("setups.join.spawn.yaw"),
						Config.getDefaultConfig().getInt("setups.join.spawn.pitch")));
			}
		}
	}

}
