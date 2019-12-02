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

public class ArmorerGui extends Gui implements Listener
{
	
	private Player player;

	public ArmorerGui()
	{
		super(9, "Boutique de l'" + Config.getDefaultConfig().getString("game.entities.armorer.name"));
		
		contents();
	}

	@Override
	public void contents()
	{
		Teams team = TeamsManager.getTeam(player);

		switch (GameManager.getArmorerLevel())
		{
		case 1:
			inventory.addItem(new ItemBuilder(Material.LEATHER_HELMET).setName("§6Casque en cuire").setLore(Arrays.asList("§7Prix: 6 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§6Plastron en cuire").setLore(Arrays.asList("§7Prix: 10 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§6Pantalon en cuire").setLore(Arrays.asList("§7Prix: 8 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.LEATHER_BOOTS).setName("§6Bottes en cuire").setLore(Arrays.asList("§7Prix: 6 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§6Casque en maille").setLore(Arrays.asList("§7Prix: 18 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
			inventory.addItem(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§6Plastron en maille").setLore(Arrays.asList("§7Prix: 30 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
			inventory.addItem(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setName("§6Pantalon en maille").setLore(Arrays.asList("§7Prix: 25 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
			inventory.addItem(new ItemBuilder(Material.CHAINMAIL_BOOTS).setName("§6Bottes en maille").setLore(Arrays.asList("§7Prix: 18 Pépites")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).toItemStack());
			if (!team.isVillagerLvl2())
			{
				inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setDurability((byte) 120)
						.setName("§6Villageois (Lvl.2)").setLore(Arrays.asList("§7Prix: 8 Blocs d'or")).toItemStack());
			}		
			break;
		case 2:
			inventory.addItem(new ItemBuilder(Material.IRON_HELMET).setName("§6Casque en fer").setLore(Arrays.asList("§7Prix: 1 Lingot d'or")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.PROTECTION_FIRE, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_CHESTPLATE).setName("§6Plastron en fer").setLore(Arrays.asList("§7Prix: 2 Lingots d'or")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.PROTECTION_FIRE, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_LEGGINGS).setName("§6Pantalon en fer").setLore(Arrays.asList("§7Prix: 2 Lingots d'or")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.PROTECTION_FIRE, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_BOOTS).setName("§6Bottes en fer").setLore(Arrays.asList("§7Prix: 1 Lingot d'or")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addEnchant(Enchantment.PROTECTION_FIRE, 1).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_HELMET).setName("§6Casque en or").setLore(Arrays.asList("§7Prix: 2 Lingots d'ors")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.PROTECTION_FIRE, 4).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_CHESTPLATE).setName("§6Plastron en or").setLore(Arrays.asList("§7Prix: 3 Lingots d'ors")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.THORNS, 2).addEnchant(Enchantment.PROTECTION_FIRE, 4).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_LEGGINGS).setName("§6Pantalon en or").setLore(Arrays.asList("§7Prix: 3 Lingots d'ors")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.PROTECTION_FIRE, 4).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_BOOTS).setName("§6Bottes en or").setLore(Arrays.asList("§7Prix: 2 Lingots d'ors")).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.PROTECTION_FIRE, 4).addEnchant(Enchantment.DURABILITY, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.DIAMOND_BOOTS).setName("§6Bottes en diamant").setLore(Arrays.asList("§7Prix: 2 Blocs d'or")).addEnchant(Enchantment.PROTECTION_FALL, 4).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.PROTECTION_FIRE, 4).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.THORNS, 2).toItemStack());
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
						case LEATHER_HELMET:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Casque en cuire", Material.LEATHER_HELMET, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(1), (byte) 0, 6);
							break;
						case LEATHER_CHESTPLATE:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Plastron en cuire", Material.LEATHER_CHESTPLATE, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(1), (byte) 0, 10);
							break;
						case LEATHER_LEGGINGS:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Pantalon en cuire", Material.LEATHER_LEGGINGS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(1), (byte) 0, 8);
							break;
						case LEATHER_BOOTS:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Bottes en cuire", Material.LEATHER_BOOTS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(1), (byte) 0, 6);
							break;
						case CHAINMAIL_HELMET:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Casque en maille", Material.CHAINMAIL_HELMET, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(2), (byte) 0, 18);
							break;
						case CHAINMAIL_CHESTPLATE:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Plastron en maille", Material.CHAINMAIL_CHESTPLATE, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(2), (byte) 0, 30);
							break;
						case CHAINMAIL_LEGGINGS:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Pantalon en maille", Material.CHAINMAIL_LEGGINGS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(2), (byte) 0, 25);
							break;
						case CHAINMAIL_BOOTS:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Bottes en maille", Material.CHAINMAIL_BOOTS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL), Arrays.asList(2), (byte) 0, 18);
							break;
						case MONSTER_EGG:
							if (inventory.getName().equalsIgnoreCase("Boutique de l'" + Config.getDefaultConfig().getString("game.entities.armorer.name")))
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
						case IRON_HELMET:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Casque en fer", Material.IRON_HELMET, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(3, 1, 3), (byte) 0, 1);
							break;
						case IRON_CHESTPLATE:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Plastron en fer", Material.IRON_CHESTPLATE, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(3, 1, 3), (byte) 0, 2);
							break;
						case IRON_LEGGINGS:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Pantalon en fer", Material.IRON_LEGGINGS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(3, 1, 3), (byte) 0, 2);
							break;
						case IRON_BOOTS:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Bottes en fer", Material.IRON_BOOTS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(3, 1, 3), (byte) 0, 1);
							break;
						case GOLD_HELMET:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Casque en or", Material.GOLD_HELMET, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(4, 4, 3), (byte) 0, 2);
							break;
						case GOLD_CHESTPLATE:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Plastron en or", Material.GOLD_CHESTPLATE, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.THORNS, Enchantment.DURABILITY), Arrays.asList(4, 4, 2, 3), (byte) 0, 3);
							break;
						case GOLD_LEGGINGS:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Pantalon en or", Material.GOLD_LEGGINGS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(4, 4, 3), (byte) 0, 3);
							break;
						case GOLD_BOOTS:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Bottes en or", Material.GOLD_BOOTS, 1, true, Arrays.asList(Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.DURABILITY), Arrays.asList(4, 4, 3), (byte) 0, 2);
							break;
						case DIAMOND_BOOTS:
							GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Bottes en diamant", Material.DIAMOND_BOOTS, 1, true, Arrays.asList(Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.THORNS), Arrays.asList(4, 4, 4, 2), (byte) 0, 2);
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
