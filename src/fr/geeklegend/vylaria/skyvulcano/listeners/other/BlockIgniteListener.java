package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;

public class BlockIgniteListener implements Listener
{

	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event)
	{
		IgniteCause igniteCause = event.getCause();
		
		if (igniteCause == IgniteCause.SPREAD)
		{
			event.setCancelled(true);
		}
	}
	
}
