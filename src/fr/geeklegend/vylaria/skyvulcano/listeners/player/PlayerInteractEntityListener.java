package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.guis.ArmorerGui;
import fr.geeklegend.vylaria.skyvulcano.guis.FletcherGui;
import fr.geeklegend.vylaria.skyvulcano.guis.MagicianGui;
import fr.geeklegend.vylaria.skyvulcano.guis.WeaponSmithGui;

public class PlayerInteractEntityListener implements Listener
{

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		Entity rightClicked = event.getRightClicked();

		if (GameState.isState(GameState.GAME))
		{
			if (rightClicked.getType() == EntityType.VILLAGER)
			{
				event.setCancelled(true);

				if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.magician.name") + " (Lvl.1)"))
				{
					GameManager.setMagicianLevel(1);

					MagicianGui magicianGui = new MagicianGui();
					magicianGui.setPlayer(player);
					
					player.openInventory(magicianGui.getInventory());
				} else if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.magician.name") + " (Lvl.2)"))
				{
					GameManager.setMagicianLevel(2);

					player.openInventory(new MagicianGui().getInventory());
				}
				if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.armorer.name") + " (Lvl.1)"))
				{
					GameManager.setArmorerLevel(1);
					
					ArmorerGui armorerGui = new ArmorerGui();
					armorerGui.setPlayer(player);

					player.openInventory(armorerGui.getInventory());
				} else if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.armorer.name") + " (Lvl.2)"))
				{
					GameManager.setArmorerLevel(2);

					player.openInventory(new ArmorerGui().getInventory());
				}
				if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.fletcher.name") + " (Lvl.1)"))
				{
					GameManager.setFletcherLevel(1);
					
					FletcherGui fletcherGui = new FletcherGui();
					fletcherGui.setPlayer(player);
					
					player.openInventory(fletcherGui.getInventory());
				} else if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.fletcher.name") + " (Lvl.2)"))
				{
					GameManager.setFletcherLevel(2);

					player.openInventory(new FletcherGui().getInventory());
				}
				if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.weaponsmith.name") + " (Lvl.1)"))
				{
					GameManager.setWeaponSmithLevel(1);
					
					WeaponSmithGui weaponSmithGui = new WeaponSmithGui();
					weaponSmithGui.setPlayer(player);

					player.openInventory(weaponSmithGui.getInventory());
				} else if (rightClicked.getName().equalsIgnoreCase(
						"§e" + Config.getDefaultConfig().getString("game.entities.weaponsmith.name") + " (Lvl.2)"))
				{
					GameManager.setWeaponSmithLevel(2);

					player.openInventory(new WeaponSmithGui().getInventory());
				}
			}
		}
	}

}
