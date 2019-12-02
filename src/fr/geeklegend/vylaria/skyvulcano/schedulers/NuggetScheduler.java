package fr.geeklegend.vylaria.skyvulcano.schedulers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;

public class NuggetScheduler extends BukkitRunnable
{
	
	private static int timer = 5;

	@Override
	public void run()
	{
		timer--;
		
		if (timer == 0)
		{
			Location location = GameManager.getNuggetLocation();
			location.getWorld().dropItemNaturally(location, new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("game.nuggets.material").toUpperCase().replace(" ", "_"))).toItemStack());
			
			reset();
		}
	}
	
	public void reset()
	{
		timer = 5;
	}

}
