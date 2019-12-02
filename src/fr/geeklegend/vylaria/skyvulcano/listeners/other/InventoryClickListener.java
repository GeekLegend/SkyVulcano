package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener
{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		}
	}

}
