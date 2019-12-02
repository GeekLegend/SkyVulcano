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

public class MagicianGui extends Gui implements Listener
{

	private Player player;

	public MagicianGui()
	{
		super(9, "Boutique du " + Config.getDefaultConfig().getString("game.entities.magician.name"));

		contents();
	}

	@Override
	public void contents()
	{
		Teams team = TeamsManager.getTeam(player);

		switch (GameManager.getMagicianLevel())
		{
		case 1:
			inventory.addItem(new ItemBuilder(Material.FLINT_AND_STEEL).setName("§6Briquet")
					.setLore(Arrays.asList("§7Prix: 5 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.POTION).setDurability((byte) 16421)
					.setName("§6Potion de soin (Niv.2)").setLore(Arrays.asList("§7Prix: 10 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.ENDER_PEARL).setName("§6Ender pearl")
					.setLore(Arrays.asList("§7Prix: 10 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.STICK).setName("§6Baton")
					.setLore(Arrays.asList("§7Prix: 15 Pépites")).addEnchant(Enchantment.KNOCKBACK, 1).toItemStack());
			inventory.addItem(new ItemBuilder(Material.GOLDEN_APPLE).setName("§6Pomme dorée")
					.setLore(Arrays.asList("§7Prix: 30 Pépites")).toItemStack());
			if (!team.isVillagerLvl2())
			{
				inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setDurability((byte) 120)
						.setName("§6Villageois (Lvl.2)").setLore(Arrays.asList("§7Prix: 8 Blocs d'or")).toItemStack());
			}
			break;
		case 2:
			inventory.addItem(new ItemBuilder(Material.POTION).setDurability((byte) 16429)
					.setName("§6Potion de lévitation").setLore(Arrays.asList("§7Créer une zone de 3 blocs de rayon",
							"§7avec un éffet de lévitation", "§7Prix: 20 Pépites"))
					.toItemStack());
			inventory.addItem(new ItemBuilder(Material.ARROW, 2).setName("§6Fleches explosives")
					.setLore(Arrays.asList("§7Prix: 8 Pépites")).toItemStack());
			inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setName("§6Poulet voleur").setDurability((byte) 93)
					.setLore(Arrays.asList("§7Choisi un joueur adverse aléatoire", "§7et lui vole 50% de ses pépites",
							"§7Prix: 60 Pépites"))
					.toItemStack());
			inventory.addItem(new ItemBuilder(Material.MONSTER_EGG).setName("§6Cochon fumigène")
					.setDurability((byte) 90).setLore(Arrays.asList("§7Prix: 40 Pépites")).toItemStack());
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
						case FLINT_AND_STEEL:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Briquet", Material.FLINT_AND_STEEL,
									1, false, null, null, (byte) 0, 5);
							break;
						case POTION:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Potion de soin (Niv.2)",
									Material.POTION, 1, false, null, null, (byte) 37, 10);
							break;
						case ENDER_PEARL:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Ender pearl", Material.ENDER_PEARL,
									1, false, null, null, (byte) 0, 10);
							break;
						case STICK:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Baton", Material.STICK, 1, true,
									Arrays.asList(Enchantment.KNOCKBACK), Arrays.asList(1), (byte) 0, 15);
							break;
						case GOLDEN_APPLE:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Pomme dorée", Material.GOLDEN_APPLE,
									1, false, null, null, (byte) 0, 30);
							break;
						case MONSTER_EGG:
							if (inventory.getName().equalsIgnoreCase("Boutique du "
									+ Config.getDefaultConfig().getString("game.entities.magician.name")))
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
						case POTION:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Potion de lévitation",
									Material.POTION, 1, false, null, null, (byte) 16429, 20);
							break;
						case ARROW:
							GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Fleches explosives", Material.ARROW,
									2, false, null, null, (byte) 0, 8);
							break;
						case MONSTER_EGG:
							if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Poulet voleur"))
							{
								GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Poulet voleur",
										Material.MONSTER_EGG, 1, false, null, null, (byte) 93, 60);
							} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§6Cochon fumigène"))
							{
								GameManager.checkNuggets(player, Material.GOLD_NUGGET, "Ender pearl",
										Material.MONSTER_EGG, 1, false, null, null, (byte) 90, 40);
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
