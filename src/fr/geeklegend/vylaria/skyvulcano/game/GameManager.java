package fr.geeklegend.vylaria.skyvulcano.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.geeklegend.vylaria.api.utils.builder.ItemBuilder;
import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.schedulers.NuggetScheduler;
import fr.geeklegend.vylaria.skyvulcano.tablist.Tablist;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;
import fr.geeklegend.vylaria.skyvulcano.utils.NPC;

public class GameManager
{

	private static List<Player> spectators = new ArrayList<Player>();
	private static List<Player> respawn = new ArrayList<Player>();
	private static Location nuggetLocation = new Location(
			Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
			Config.getDefaultConfig().getDouble("game.nuggets.spawn.x"),
			Config.getDefaultConfig().getDouble("game.nuggets.spawn.y"),
			Config.getDefaultConfig().getDouble("game.nuggets.spawn.z"));
	private static int magicianLevel = 1, armorerLevel = 1, fletcherLevel = 1, weaponSmithLevel = 1;

	public static void load()
	{
		World world = Bukkit.getWorld(Config.getDefaultConfig().getString("game.world"));

		world.setStorm(false);
		world.setThundering(false);
		world.setDifficulty(Difficulty.NORMAL);
		world.setTime(13000L);
		world.setGameRuleValue("doDaylightCycle", "false");
		world.setGameRuleValue("doFireTick", "false");
		world.setGameRuleValue("doMobSpawning", "true");

		clearEntities(world);
		regenHearts();
	}

	public static void clearEntities(World world)
	{
		for (Entity entity : world.getEntities())
		{
			entity.remove();
		}
	}

	public static void addEntities(World world, Teams team, int level)
	{
		switch (level)
		{
		case 1:
			NPC.create(
					new Location(world, Config.getDefaultConfig().getDouble("game.entities.magician.lvl1.spawn.x"),
							Config.getDefaultConfig().getDouble("game.entities.magician.lvl1.spawn.y"),
							Config.getDefaultConfig().getDouble("game.entities.magician.lvl1.spawn.z"),
							Config.getDefaultConfig().getInt("game.entities.magician.lvl1.spawn.yaw"),
							Config.getDefaultConfig().getInt("game.entities.magician.lvl1.spawn.pitch")),
					"§e" + Config.getDefaultConfig().getString("game.entities.magician.name") + " (Lvl.1)");
			NPC.create(
					new Location(world, Config.getDefaultConfig().getDouble("game.entities.armorer.lvl1.spawn.x"),
							Config.getDefaultConfig().getDouble("game.entities.armorer.lvl1.spawn.y"),
							Config.getDefaultConfig().getDouble("game.entities.armorer.lvl1.spawn.z"),
							Config.getDefaultConfig().getInt("game.entities.armorer.lvl1.spawn.yaw"),
							Config.getDefaultConfig().getInt("game.entities.armorer.lvl1.spawn.pitch")),
					"§e" + Config.getDefaultConfig().getString("game.entities.armorer.name") + " (Lvl.1)");
			NPC.create(
					new Location(world, Config.getDefaultConfig().getDouble("game.entities.fletcher.lvl1.spawn.x"),
							Config.getDefaultConfig().getDouble("game.entities.fletcher.lvl1.spawn.y"),
							Config.getDefaultConfig().getDouble("game.entities.fletcher.lvl1.spawn.z"),
							Config.getDefaultConfig().getInt("game.entities.fletcher.lvl1.spawn.yaw"),
							Config.getDefaultConfig().getInt("game.entities.fletcher.lvl1.spawn.pitch")),
					"§e" + Config.getDefaultConfig().getString("game.entities.fletcher.name") + " (Lvl.1)");
			NPC.create(
					new Location(world, Config.getDefaultConfig().getDouble("game.entities.weaponsmith.lvl1.spawn.x"),
							Config.getDefaultConfig().getDouble("game.entities.weaponsmith.lvl1.spawn.y"),
							Config.getDefaultConfig().getDouble("game.entities.weaponsmith.lvl1.spawn.z"),
							Config.getDefaultConfig().getInt("game.entities.weaponsmith.lvl1.spawn.yaw"),
							Config.getDefaultConfig().getInt("game.entities.weaponsmith.lvl1.spawn.pitch")),
					"§e" + Config.getDefaultConfig().getString("game.entities.weaponsmith.name") + " (Lvl.1)");
			break;
		case 2:
			if (team == Teams.GREEN)
			{
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.magician.green.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.magician.green.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.magician.green.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.magician.green.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.magician.green.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.magician.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.armorer.green.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.armorer.green.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.armorer.green.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.armorer.green.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.armorer.green.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.armorer.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.fletcher.green.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.fletcher.green.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.fletcher.green.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.fletcher.green.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.fletcher.green.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.fletcher.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.green.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.green.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.green.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.weaponsmith.green.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.weaponsmith.green.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.weaponsmith.name") + " (Lvl.2)");
			} else
			{
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.magician.magenta.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.magician.magenta.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.magician.magenta.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.magician.magenta.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.magician.magenta.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.magician.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.armorer.magenta.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.armorer.magenta.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.armorer.magenta.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.armorer.magenta.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.armorer.magenta.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.armorer.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.fletcher.magenta.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.fletcher.magenta.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.fletcher.magenta.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.fletcher.magenta.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.fletcher.magenta.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.fletcher.name") + " (Lvl.2)");
				NPC.create(
						new Location(world,
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.magenta.lvl2.spawn.x"),
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.magenta.lvl2.spawn.y"),
								Config.getDefaultConfig().getDouble("game.entities.weaponsmith.magenta.lvl2.spawn.z"),
								Config.getDefaultConfig().getInt("game.entities.weaponsmith.magenta.lvl2.spawn.yaw"),
								Config.getDefaultConfig().getInt("game.entities.weaponsmith.magenta.lvl2.spawn.pitch")),
						"§e" + Config.getDefaultConfig().getString("game.entities.weaponsmith.name") + " (Lvl.2)");
			}
			break;
		default:
			break;
		}
	}

	public static void regenHearts()
	{
		for (Block ghb : Teams.GREEN.getHeartCuboid())
		{
			ghb.setType(Material.WOOL);
			ghb.setData((byte) 5);
		}

		for (Block mhb : Teams.MAGENTA.getHeartCuboid())
		{
			mhb.setType(Material.WOOL);
			mhb.setData((byte) 2);
		}
	}

	public static void setup()
	{
		TeamsManager.random();

		Tablist.setScoreboard();

		for (Player players : Bukkit.getOnlinePlayers())
		{
			Teams team = TeamsManager.getTeam(players);

			players.setLevel(0);
			players.setGameMode(
					GameMode.valueOf(Config.getDefaultConfig().getString("setups.game.gamemode").toUpperCase()));
			players.teleport(team.getSpawnLocation());
			players.closeInventory();

			giveItems(players);
		}

		addEntities(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")), null, 1);

		new NuggetScheduler().runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
	}

	public static void respawn(Player player)
	{
		Teams team = TeamsManager.getTeam(player);

		player.setLevel(0);
		player.setGameMode(GameMode.valueOf(Config.getDefaultConfig().getString("setups.game.gamemode").toUpperCase()));
		for (PotionEffect effects : player.getActivePotionEffects())
		{
			player.removePotionEffect(effects.getType());
		}

		giveItems(player);
	}

	public static void giveItems(Player player)
	{
		player.getInventory().clear();
		player.getInventory().addItem(new ItemBuilder(Material.WOOD_SWORD).toItemStack());
		player.getInventory().addItem(new ItemBuilder(Material.WOOD_AXE).toItemStack());
		player.getInventory().addItem(new ItemBuilder(Material.WOOD_PICKAXE).toItemStack());
		player.getInventory().addItem(new ItemBuilder(Material.COOKED_BEEF, 16).toItemStack());

		Teams team = TeamsManager.getTeam(player);

		byte data = 0;

		if (team == Teams.GREEN)
		{
			data = 13;
		} else if (team == Teams.MAGENTA)
		{
			data = 2;
		}

		player.getInventory().addItem(new ItemBuilder(Material.STAINED_CLAY, 64, data).toItemStack());
	}

	public static void checkNuggets(Player player, Material nuggets, String itemName, Material item, int itemAmount,
			boolean enchanted, List<Enchantment> enchantments, List<Integer> enchantmentsLevel, byte itemData,
			int nuggetsNeed)
	{
		if (player.getInventory().contains(nuggets, nuggetsNeed))
		{
			ItemBuilder i = new ItemBuilder(item, itemAmount);
			i.setDurability(itemData);

			if (enchanted)
			{
				for (Enchantment e : enchantments)
				{
					for (Integer index : enchantmentsLevel)
					{
						i.addEnchant(e, index);
					}
				}
			}

			ItemBuilder i2 = new ItemBuilder(nuggets, nuggetsNeed);

			player.getInventory().removeItem(i2.toItemStack());
			player.getInventory().addItem(i.toItemStack());
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.buy").replace("&", "§")
					.replace("%itemName%", itemName));
		} else
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.nuggets.noenought").replace("&", "§")
					.replace("%nuggetsAmount%", "" + nuggetsNeed));
		}
	}

	public static void checkGoldIngots(Player player, Material goldIngots, String itemName, Material item,
			int itemAmount, boolean enchanted, List<Enchantment> enchantments, List<Integer> enchantmentsLevel,
			byte itemData, int goldIngotsNeed)
	{
		if (player.getInventory().contains(goldIngots, goldIngotsNeed))
		{
			ItemBuilder i = new ItemBuilder(item, itemAmount);

			i.setDurability(itemData);
			if (enchanted)
			{
				for (Enchantment e : enchantments)
				{
					for (Integer index : enchantmentsLevel)
					{
						i.addEnchant(e, index);
					}
				}
			}

			ItemBuilder i2 = new ItemBuilder(goldIngots, goldIngotsNeed);

			player.getInventory().removeItem(i2.toItemStack());
			player.getInventory().addItem(i.toItemStack());
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.buy").replace("&", "§")
					.replace("%itemName%", itemName));
		} else
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.goldingots.noenought")
					.replace("&", "§").replace("%goldIngotsAmount%", "" + goldIngotsNeed));
		}
	}

	public static void checkGoldBlocks(Player player, Material goldBlocks, String itemName, Material item,
			int itemAmount, boolean enchanted, List<Enchantment> enchantments, List<Integer> enchantmentsLevel,
			byte itemData, int goldBlocksNeed)
	{
		if (player.getInventory().contains(goldBlocks, goldBlocksNeed))
		{
			ItemBuilder i = new ItemBuilder(item, itemAmount);
			i.setDurability(itemData);

			if (enchanted)
			{
				for (Enchantment e : enchantments)
				{
					for (Integer index : enchantmentsLevel)
					{
						i.addEnchant(e, index);
					}
				}
			}

			ItemBuilder i2 = new ItemBuilder(goldBlocks, goldBlocksNeed);

			player.getInventory().removeItem(i2.toItemStack());
			player.getInventory().addItem(i.toItemStack());
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.buy").replace("&", "§")
					.replace("%itemName%", itemName));
		} else
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.goldblocks.noenought")
					.replace("&", "§").replace("%goldBlocksAmount%", "" + goldBlocksNeed));
		}
	}

	public static void spawnVillagerLvl2(Player player, Material goldBlocks, String itemName, int goldBlocksNeed)
	{
		if (player.getInventory().contains(goldBlocks, goldBlocksNeed))
		{
			ItemBuilder i2 = new ItemBuilder(goldBlocks, goldBlocksNeed);

			player.getInventory().removeItem(i2.toItemStack());

			Teams team = TeamsManager.getTeam(player);

			addEntities(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")), team, 2);

			team.setVillagerLvl2(true);

			player.playSound(player.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.buy").replace("&", "§")
					.replace("%itemName%", itemName));

			for (Player players : Bukkit.getOnlinePlayers())
			{
				Teams pt = TeamsManager.getTeam(players);

				if (team == pt)
				{
					players.playSound(player.getLocation(), Sound.ANVIL_USE, 20.0f, 20.0f);
					players.sendMessage(Config.getDefaultConfig().getString("messages.game.villagerlvl2")
							.replace("&", "§").replace("%teamcolor%", "" + team.getNameColor())
							.replace("%playername%", player.getName()).replace("%itemname%", itemName));
				}
			}
		} else
		{
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.goldblocks.noenought")
					.replace("&", "§").replace("%goldBlocksAmount%", "" + goldBlocksNeed));
		}
	}

	public static void addSpectator(Player player)
	{
		spectators.add(player);
	}

	public static void removeSpectator(Player player)
	{
		spectators.remove(player);
	}

	public static boolean spectatorsContains(Player player)
	{
		return spectators.contains(player);
	}

	public static void addRespawn(Player player)
	{
		respawn.add(player);
	}

	public static void removeRespawn(Player player)
	{
		respawn.remove(player);
	}

	public static boolean respawnContains(Player player)
	{
		return respawn.contains(player);
	}

	public static Location getNuggetLocation()
	{
		return nuggetLocation;
	}

	public static int getMagicianLevel()
	{
		return magicianLevel;
	}

	public static void setMagicianLevel(int magicianLevel)
	{
		GameManager.magicianLevel = magicianLevel;
	}

	public static int getArmorerLevel()
	{
		return armorerLevel;
	}

	public static void setArmorerLevel(int armorerLevel)
	{
		GameManager.armorerLevel = armorerLevel;
	}

	public static int getFletcherLevel()
	{
		return fletcherLevel;
	}

	public static void setFletcherLevel(int fletcherLevel)
	{
		GameManager.fletcherLevel = fletcherLevel;
	}

	public static int getWeaponSmithLevel()
	{
		return weaponSmithLevel;
	}

	public static void setWeaponSmithLevel(int weaponSmithLevel)
	{
		GameManager.weaponSmithLevel = weaponSmithLevel;
	}

}
