package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.VylariaAPI;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.guis.TeamsGui;

public class PlayerInteractListener implements Listener
{
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack itemStack = event.getItem();

		if (GameState.isState(GameState.WAITING))
		{
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
			{
				if (itemStack != null)
				{
					if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(
							Config.getDefaultConfig().getString("setups.join.items.teams.name").replace("&", "§")))
					{
						player.openInventory(new TeamsGui().getInventory());
					} else if (itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(
							Config.getDefaultConfig().getString("setups.join.items.leave.name").replace("&", "§")))
					{
						VylariaAPI.getInstance().getBungeeChannelApi().connect(player, "lobby");
					}
				}
			}
		}
	}

}
