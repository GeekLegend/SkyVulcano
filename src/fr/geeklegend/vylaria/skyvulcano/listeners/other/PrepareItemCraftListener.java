package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class PrepareItemCraftListener implements Listener
{
	
	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent event)
	{
		if (GameState.isState(GameState.GAME))
		{
			Material material = event.getRecipe().getResult().getType();
			byte data = event.getRecipe().getResult().getData().getData();
			
			if (material == Material.SHEARS)
			{
				event.getInventory().setResult(new ItemStack(Material.AIR));
			}
		}
	}

}
