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

public class WeaponSmithGui extends Gui implements Listener
{

	private Player player;
	
	public WeaponSmithGui()
	{
		super(9, "Boutique du " + Config.getDefaultConfig().getString("game.entities.weaponsmith.name"));

		contents();
	}

	@Override
	public void contents()
	{
		Teams team = TeamsManager.getTeam(player);

		switch (GameManager.getWeaponSmithLevel())
		{
		case 1:
			inventory.addItem(new ItemBuilder(Material.STONE_SWORD).setName("§6Épée en pierre")
					.setLore(Arrays.asList("§7Prix: 10 Pépites")).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
			inventory.addItem(new ItemBuilder(Material.STONE_AXE).setName("§6Hache en pierre")
					.setLore(Arrays.asList("§7Prix: 15 Pépites")).addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_SWORD).setName("§6Épée en fer")
					.setLore(Arrays.asList("§7Prix: 25 Pépites")).addEnchant(Enchantment.DAMAGE_ALL, 2).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_AXE).setName("§6Hache en fer")
					.setLore(Arrays.asList("§7Prix: 35 Pépites")).addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack());
			if (!team.isVillagerLvl2())
			{
				inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setDurability((byte) 120)
						.setName("§6Villageois (Lvl.2)").setLore(Arrays.asList("§7Prix: 8 Blocs d'or")).toItemStack());
			}	
			break;
		case 2:
			inventory.addItem(new ItemBuilder(Material.IRON_SWORD).setName("§6Épée en fer")
					.setLore(Arrays.asList("§7Prix: 4 Lingots d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.IRON_AXE).setName("§6Hache en fer")
					.setLore(Arrays.asList("§7Prix: 5 Lingots d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.DAMAGE_ALL, 3).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_SWORD).setName("§6Épée en or")
					.setLore(Arrays.asList("§7Prix: 1 Bloc d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.DAMAGE_ALL, 4).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLD_AXE).setName("§6Hache en or")
					.setLore(Arrays.asList("§7Prix: 1 Bloc d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.DAMAGE_ALL, 4).toItemStack());
			inventory.addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§6Épée en diamant")
					.setLore(Arrays.asList("§7Prix: 2 Blocs d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.FIRE_ASPECT, 1).addEnchant(Enchantment.DAMAGE_ALL, 5)
					.addEnchant(Enchantment.KNOCKBACK, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.DIAMOND_AXE).setName("§6Hache en diamant")
					.setLore(Arrays.asList("§7Prix: 3 Blocs d'or")).addEnchant(Enchantment.DURABILITY, 3)
					.addEnchant(Enchantment.FIRE_ASPECT, 2).addEnchant(Enchantment.DAMAGE_ALL, 5)
					.addEnchant(Enchantment.KNOCKBACK, 2).toItemStack());
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
						case STONE_SWORD:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Épée en pierre",
									Material.STONE_SWORD, 1, true, Arrays.asList(Enchantment.DAMAGE_ALL),
									Arrays.asList(2), (byte) 0, 10);
							break;
						case STONE_AXE:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Hache en pierre",
									Material.STONE_AXE, 1, true, Arrays.asList(Enchantment.DAMAGE_ALL),
									Arrays.asList(3), (byte) 0, 15);
							break;
						case IRON_SWORD:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Épée en fer", Material.IRON_SWORD,
									1, true, Arrays.asList(Enchantment.DAMAGE_ALL), Arrays.asList(2), (byte) 0, 25);
							break;
						case IRON_AXE:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Hache en fer", Material.IRON_AXE, 1,
									true, Arrays.asList(Enchantment.DAMAGE_ALL), Arrays.asList(3), (byte) 0, 35);
							break;
						case MONSTER_EGG:
							if (inventory.getName().equalsIgnoreCase("Boutique du "
									+ Config.getDefaultConfig().getString("game.entities.weaponsmith.name")))
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
						case IRON_SWORD:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Épée en fer", Material.IRON_SWORD,
									1, true, Arrays.asList(Enchantment.DURABILITY, Enchantment.DAMAGE_ALL),
									Arrays.asList(3, 3), (byte) 0, 4);
							break;
						case IRON_AXE:
							GameManager.checkGoldIngots(player, Material.GOLD_INGOT, "Hache en fer", Material.IRON_AXE,
									1, true, Arrays.asList(Enchantment.DURABILITY, Enchantment.DAMAGE_ALL),
									Arrays.asList(3, 3), (byte) 0, 5);
							break;
						case GOLD_SWORD:
							GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Épée en or", Material.GOLD_SWORD,
									1, true, Arrays.asList(Enchantment.DURABILITY, Enchantment.DAMAGE_ALL),
									Arrays.asList(3, 4), (byte) 0, 1);
							break;
						case GOLD_AXE:
							GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Hache en or", Material.GOLD_AXE,
									1, true, Arrays.asList(Enchantment.DURABILITY, Enchantment.DAMAGE_ALL),
									Arrays.asList(3, 4), (byte) 0, 1);
							break;
						case DIAMOND_SWORD:
							GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Épée en diamant",
									Material.DIAMOND_SWORD, 1, true,
									Arrays.asList(Enchantment.DURABILITY, Enchantment.FIRE_ASPECT,
											Enchantment.DAMAGE_ALL, Enchantment.KNOCKBACK),
									Arrays.asList(3, 1, 5, 1), (byte) 0, 2);
							break;
						case DIAMOND_AXE:
							GameManager.checkGoldBlocks(player, Material.GOLD_BLOCK, "Hache en diamant",
									Material.DIAMOND_AXE, 1, true,
									Arrays.asList(Enchantment.DURABILITY, Enchantment.FIRE_ASPECT,
											Enchantment.DAMAGE_ALL, Enchantment.KNOCKBACK),
									Arrays.asList(3, 2, 5, 2), (byte) 0, 3);
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
