package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class EntityDamageListener implements Listener
{

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event)
	{
		Entity entity = event.getEntity();
		
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		} else if (GameState.isState(GameState.GAME))
		{
			if (entity.getType() == EntityType.VILLAGER)
			{
				event.setCancelled(true);
			}
		}
	}

}
