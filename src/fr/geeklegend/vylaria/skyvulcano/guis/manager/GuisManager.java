package fr.geeklegend.vylaria.skyvulcano.guis.manager;

import org.bukkit.plugin.PluginManager;

import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.guis.ArmorerGui;
import fr.geeklegend.vylaria.skyvulcano.guis.FletcherGui;
import fr.geeklegend.vylaria.skyvulcano.guis.MagicianGui;
import fr.geeklegend.vylaria.skyvulcano.guis.TeamsGui;
import fr.geeklegend.vylaria.skyvulcano.guis.WeaponSmithGui;

public class GuisManager
{

	private SkyVulcano instance;

	public GuisManager(SkyVulcano instance)
	{
		this.instance = instance;
	}

	public void register()
	{
		PluginManager pluginManager = instance.getServer().getPluginManager();
		pluginManager.registerEvents(new TeamsGui(), instance);
		pluginManager.registerEvents(new MagicianGui(), instance);
		pluginManager.registerEvents(new ArmorerGui(), instance);
		pluginManager.registerEvents(new FletcherGui(), instance);
		pluginManager.registerEvents(new WeaponSmithGui(), instance);
	}
	
}
