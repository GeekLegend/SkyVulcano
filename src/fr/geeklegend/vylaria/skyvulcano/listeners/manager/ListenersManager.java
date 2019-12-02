package fr.geeklegend.vylaria.skyvulcano.listeners.manager;

import org.bukkit.plugin.PluginManager;

import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.AsyncPlayerChatListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.BlockBreakListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.BlockIgniteListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.BlockPlaceListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.CreatureSpawnListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.EntityDamageByEntityListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.EntityDamageListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.EntityExplodeListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.FoodLevelChangeListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.InventoryClickListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.PrepareItemCraftListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.other.WeatherChangeListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerDeathListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerDropItemListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerInteractEntityListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerInteractListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerJoinListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerMoveListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerQuitListener;
import fr.geeklegend.vylaria.skyvulcano.listeners.player.PlayerRespawnListener;

public class ListenersManager
{

	private SkyVulcano instance;
	
	public ListenersManager(SkyVulcano instance)
	{
		this.instance = instance;
	}
	
	public void register()
	{
		PluginManager pluginManager = instance.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(), instance);
		pluginManager.registerEvents(new PlayerQuitListener(), instance);
		pluginManager.registerEvents(new PlayerInteractListener(), instance);
		pluginManager.registerEvents(new PlayerDropItemListener(), instance);
		pluginManager.registerEvents(new PlayerRespawnListener(), instance);
		pluginManager.registerEvents(new PlayerDeathListener(), instance);
		pluginManager.registerEvents(new PlayerMoveListener(), instance);
		pluginManager.registerEvents(new PlayerInteractEntityListener(), instance);
		
		pluginManager.registerEvents(new AsyncPlayerChatListener(), instance);
		pluginManager.registerEvents(new CreatureSpawnListener(), instance);
		pluginManager.registerEvents(new BlockBreakListener(), instance);
		pluginManager.registerEvents(new BlockPlaceListener(), instance);
		pluginManager.registerEvents(new BlockIgniteListener(), instance);
		pluginManager.registerEvents(new FoodLevelChangeListener(), instance);
		pluginManager.registerEvents(new PrepareItemCraftListener(), instance);
		pluginManager.registerEvents(new WeatherChangeListener(), instance);
		pluginManager.registerEvents(new InventoryClickListener(), instance);
		pluginManager.registerEvents(new EntityDamageListener(), instance);
		pluginManager.registerEvents(new EntityDamageByEntityListener(), instance);
		pluginManager.registerEvents(new EntityExplodeListener(), instance);
	}
	
}
