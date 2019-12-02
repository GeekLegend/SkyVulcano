package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class FoodLevelChangeListener implements Listener
{
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event)
	{
		if (GameState.isState(GameState.WAITING))
		{
			event.setCancelled(true);
		}
	}

}
