package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener
{

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event)
	{
		Entity entity = event.getEntity();

		if (entity.getType() != EntityType.VILLAGER)
		{
			event.setCancelled(true);
		}
	}

}
