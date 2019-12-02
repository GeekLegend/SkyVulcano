package fr.geeklegend.vylaria.skyvulcano.guis;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.geeklegend.vylaria.api.gui.Gui;
import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class FletcherGui extends Gui implements Listener
{
	
	private Player player;

	public FletcherGui()
	{
		super(9, "Boutique de l'" + Config.getDefaultConfig().getString("game.entities.fletcher.name"));

		contents();
	}

	@Override
	public void contents()
	{
		Teams team = TeamsManager.getTeam(player);

		switch (GameManager.getFletcherLevel())
		{
		case 1:
			inventory.addItem(new ItemBuilder(Material.BOW).setName("§6Arc I")
					.setLore(Arrays.asList("§7Prix: 20 Pépites")).toItemStack());
			inventory.addItem(
					new ItemBuilder(Material.BOW).setName("§6Arc II").setLore(Arrays.asList("§7Prix: 32 Pépites"))
							.addEnchant(Enchantment.ARROW_DAMAGE, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.BOW).setName("§6Arc III")
					.setLore(Arrays.asList("§7Prix: 64 Pépites")).addEnchant(Enchantment.ARROW_DAMAGE, 2)
					.addEnchant(Enchantment.ARROW_FIRE, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.ARROW, 10).setName("§6Fleches")
					.setLore(Arrays.asList("§7Prix: 10 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.FISHING_ROD).setName("§6Cane à pêche")
					.setLore(Arrays.asList("§7Prix: 12 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.EGG, 4).setName("§6Oeufs")
					.setLore(Arrays.asList("§7Prix: 6 Pépites")).toItemStack());
			if (!team.isVillagerLvl2())
			{
				inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setDurability((byte) 120)
						.setName("§6Villageois (Lvl.2)").setLore(Arrays.asList("§7Prix: 8 Blocs d'or")).toItemStack());
			}	
			break;
		case 2:
			inventory.addItem(new ItemBuilder(Material.BOW).setName("§6Arc I").addEnchant(Enchantment.ARROW_DAMAGE, 3)
					.setLore(Arrays.asList("§7Prix: 1 Lingot d'or")).toItemStack());
			inventory.addItem(
					new ItemBuilder(Material.BOW).setName("§6Arc II").setLore(Arrays.asList("§7Prix: 2 Lingots d'or"))
							.addEnchant(Enchantment.ARROW_DAMAGE, 4).toItemStack());
			inventory.addItem(
					new ItemBuilder(Material.BOW).setName("§6Arc III").setLore(Arrays.asList("§7Prix: 6 Lingots d'or"))
							.addEnchant(Enchantment.ARROW_DAMAGE, 5).toItemStack());
			inventory.addItem(new ItemBuilder(Material.BOW).setName("§6Arc IV")
					.setLore(Arrays.asList("§7Prix: 1 Bloc d'or")).addEnchant(Enchantment.ARROW_KNOCKBACK, 1)
					.addEnchant(Enchantment.ARROW_FIRE, 1).addEnchant(Enchantment.ARROW_DAMAGE, 5).toItemStack());
			inventory.addItem(new ItemBuilder(Material.BOW).setName("§6Arc V")
					.setLore(Arrays.asList("§7Prix: 2 Blocs d'or")).addEnchant(Enchantment.ARROW_FIRE, 1)
					.addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_KNOCKBACK, 2)
					.addEnchant(Enchantment.ARROW_DAMAGE, 5).toItemStack());
			break;
		default:
			break;
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		Inventory inventory = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();

		if (GameState.isState(GameState.GAME))
		{
			if (inventory != null && inventory.getName().equalsIgnoreCase(name))
			{
				if (item != null)
				{
					event.setCancelled(true);

					switch (GameManager.getMagicianLevel())
					{
					case 1:
						switch (item.getType())
						{
						case BOW:
							if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc I"))
							{
								GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Arc I", Material.BOW, 1, false,
										null, null, (byte) 0, 20);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc II"))
							{
								GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Arc II", Material.BOW, 1, true,
										Arrays.asList(Enchantment.ARROW_DAMAGE), Arrays.asList(1), (byte) 0, 32);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc III"))
							{
								GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Arc III", Material.BOW, 1, true,
										Arrays.asList(Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE),
										Arrays.asList(2, 1), (byte) 0, 64);
							}
							break;
						case ARROW:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Fleches", Material.ARROW, 10, false,
									null, null, (byte) 0, 10);
							break;
						case FISHING_ROD:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Cane pêche", Material.FISHING_ROD,
									1, false, null, null, (byte) 0, 12);
							break;
						case EGG:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Oeufs", Material.EGG, 4, false,
									null, null, (byte) 0, 6);
							break;
						case MONSTER_EGG:
							if (inventory.getName().equalsIgnoreCase("Boutique de l'" + Config.getDefaultConfig().getString("game.entities.fletcher.name")))
							{
								GameManager.spawnVillagerLvl2(player, Material.GOLD_BLOCK, "Villageois (Lvl.2)", 8);
							}
							break;
						default:
							break;
						}
						break;
					case 2:
						switch (item.getType())
						{
						case BOW:
							if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc I"))
							{
								GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Arc I", Material.BOW, 1, true,
										Arrays.asList(Enchantment.ARROW_DAMAGE), Arrays.asList(3), (byte) 0, 32);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc II"))
							{
								GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Arc II", Material.BOW, 1,
										true, Arrays.asList(Enchantment.ARROW_DAMAGE), Arrays.asList(4), (byte) 0, 2);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc III"))
							{
								GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Arc III", Material.BOW, 1,
										true, Arrays.asList(Enchantment.ARROW_DAMAGE), Arrays.asList(5), (byte) 0, 6);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc IV"))
							{
								GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Arc IV", Material.BOW, 1,
										true, Arrays.asList(Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_FIRE,
												Enchantment.ARROW_DAMAGE),
										Arrays.asList(2, 1, 5), (byte) 0, 1);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Arc V"))
							{
								GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Arc V", Material.BOW, 1, true,
										Arrays.asList(Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE,
												Enchantment.ARROW_KNOCKBACK, Enchantment.ARROW_DAMAGE),
										Arrays.asList(1, 1, 2, 5), (byte) 0, 2);
							}
							break;
						default:
							break;
						}
						break;
					default:
						break;
					}
				}
			}
		}
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}

}
