package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class PlayerDropItemListener implements Listener
{

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		}
	}
	
}
