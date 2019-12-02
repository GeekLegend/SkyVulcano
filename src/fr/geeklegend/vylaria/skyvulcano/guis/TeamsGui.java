package fr.geeklegend.vylaria.skyvulcano.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.gui.Gui;
import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class TeamsGui extends Gui implements Listener
{

	private static ItemBuilder iconItem;

	public TeamsGui()
	{
		super(Config.getDefaultConfig().getInt("guis.teams.size"),
				Config.getDefaultConfig().getString("guis.teams.name").replace("&", "§"));

		contents();
	}

	@Override
	public void contents()
	{
		for (Teams teams : TeamsManager.getTeams())
		{
			iconItem = new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("guis.teams.icons.material.type").toUpperCase().replace(" ", "_")),
					teams.getSize()).setDurability(teams.getIconData())
							.setName(teams.getNameColor() + teams.getName() + " §7(" + teams.getSize() + "/" + TeamsManager.getTeamMaxSize() + ")");

			for (Player players : teams.getPlayers())
			{
				iconItem.addLoreLine("§f» §7" + players.getName());
			}

			iconItem.toItemStack().setItemMeta(iconItem.toItemStack().getItemMeta());

			inventory.addItem(iconItem.toItemStack());
		}
		inventory.setItem(Config.getDefaultConfig().getInt("guis.teams.size") - 1,
				new ItemBuilder(Material.BARRIER).setName("§cFermer").toItemStack());
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();

		if (GameState.isState(GameState.WAITING))
		{
			if (inventory != null && inventory.getName()
					.equalsIgnoreCase(Config.getDefaultConfig().getString("guis.teams.name").replace("&", "§")))
			{
				if (item != null)
				{
					event.setCancelled(true);

					for (Teams teams : TeamsManager.getTeams())
					{
						if (item.getDurability() == teams.getIconData())
						{
							TeamsManager.add(player, teams, false);
						}
						if (item.getType() == Material.BARRIER)
						{
							player.closeInventory();
						}

						refresh();
					}
				}
			}
		}
	}

	public static void refresh()
	{
		for (Player players : Bukkit.getOnlinePlayers())
		{
			InventoryView inventoryView = players.getOpenInventory();
			if (inventoryView.getTitle().equalsIgnoreCase(name))
			{
				Inventory contents = inventoryView.getTopInventory();

				contents.clear();

				for (Teams teams : TeamsManager.getTeams())
				{
					iconItem = new ItemBuilder(Material.valueOf(Config.getDefaultConfig().getString("guis.teams.icons.material.type").toUpperCase().replace(" ", "_")),
							teams.getSize()).setDurability(teams.getIconData())
									.setName(teams.getNameColor() + teams.getName() + " §7(" + teams.getSize() + "/" + TeamsManager.getTeamMaxSize() + ")");

					for (Player playersTeam : teams.getPlayers())
					{
						iconItem.addLoreLine("§f» §7" + playersTeam.getName());
					}

					iconItem.toItemStack().setItemMeta(iconItem.toItemStack().getItemMeta());

					contents.addItem(iconItem.toItemStack());
				}

				contents.setItem(Config.getDefaultConfig().getInt("guis.teams.size") - 1,
						new ItemBuilder(Material.BARRIER).setName("§cFermer").toItemStack());
			}
		}
	}

}
