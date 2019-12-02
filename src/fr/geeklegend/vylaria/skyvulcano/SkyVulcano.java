package fr.geeklegend.vylaria.skyvulcano;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.guis.manager.GuisManager;
import fr.geeklegend.vylaria.skyvulcano.listeners.manager.ListenersManager;
import fr.geeklegend.vylaria.skyvulcano.scoreboard.manager.ScoreboardManager;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;
import fr.geeklegend.vylaria.skyvulcano.utils.WorldUtils;

public class SkyVulcano extends JavaPlugin
{

	private static SkyVulcano instance;

	private ScheduledExecutorService executorMonoThread;
	private ScheduledExecutorService scheduledExecutorService;
	private ScoreboardManager scoreboardManager;

	@Override
	public void onEnable()
	{
		instance = this;

		executorMonoThread = Executors.newScheduledThreadPool(16);
		scheduledExecutorService = Executors.newScheduledThreadPool(1);
		scoreboardManager = new ScoreboardManager();

		Config.create("config");
		Config.getDefaultConfig().options().copyDefaults(true);
		Config.getDefaultConfig().addDefault("messages.join",
				"&e%playername% &7a rejoint la partie. &e(%online%/%maxonline%)");
		Config.getDefaultConfig().addDefault("messages.quit",
				"&e%playername% &7a quitté la partie. &e(%online%/%maxonline%)");
		Config.getDefaultConfig().addDefault("messages.game.deathbykiller",
				"%victimTeamColor%%victimName% §7a été tué par %killerTeamColor%%killerName%");
		Config.getDefaultConfig().addDefault("messages.game.deathnokiller",
				"%victimTeamColor%%victimName% §7est mort.");
		Config.getDefaultConfig().addDefault("messages.game.respawn", "&7Réapparition dans &e%timer% seconde(s).");
		Config.getDefaultConfig().addDefault("messages.game.win", "§eL'équipe %teamName% §ea remporter la victoire !");
		Config.getDefaultConfig().addDefault("messages.game.buy", "&7Vous avez acheter &e%itemName%");
		Config.getDefaultConfig().addDefault("messages.game.villagerlvl2",
				"%teamcolor%%playername% &7a débloquer &e%itemname%");
		Config.getDefaultConfig().addDefault("messages.game.nuggets.noenought",
				"&7Il vous manque &e%nuggetsAmount% pépites");
		Config.getDefaultConfig().addDefault("messages.game.goldingots.noenought",
				"&7Il vous manque &e%goldIngotsAmount% lingots d'or");
		Config.getDefaultConfig().addDefault("messages.game.goldblocks.noenought",
				"&7Il vous manque &e%goldBlocksAmount% blocs d'or");
		Config.getDefaultConfig().addDefault("messages.game.hearts.attacked",
				"&7Il reste &e%heartblocks%% &7à l'équipe %teamcolor%%teamname% ");
		Config.getDefaultConfig().addDefault("setups.join.gamemode", "adventure");
		Config.getDefaultConfig().addDefault("setups.join.spawn.x", 20.5);
		Config.getDefaultConfig().addDefault("setups.join.spawn.y", 102.0);
		Config.getDefaultConfig().addDefault("setups.join.spawn.z", 2014.5);
		Config.getDefaultConfig().addDefault("setups.join.spawn.yaw", -180);
		Config.getDefaultConfig().addDefault("setups.join.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("setups.join.items.teams.slot", 0);
		Config.getDefaultConfig().addDefault("setups.join.items.teams.material", "nether star");
		Config.getDefaultConfig().addDefault("setups.join.items.teams.name", "&6Sélectionner une équipe");
		Config.getDefaultConfig().addDefault("setups.join.items.leave.slot", 8);
		Config.getDefaultConfig().addDefault("setups.join.items.leave.material", "bed");
		Config.getDefaultConfig().addDefault("setups.join.items.leave.name", "&6Quitter");
		Config.getDefaultConfig().addDefault("setups.game.gamemode", "survival");
		Config.getDefaultConfig().addDefault("game.world", "world");
		Config.getDefaultConfig().addDefault("game.entities.magician.name", "Magicien");
		Config.getDefaultConfig().addDefault("game.entities.magician.lvl1.spawn.x", 57.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.lvl1.spawn.y", 128.0);
		Config.getDefaultConfig().addDefault("game.entities.magician.lvl1.spawn.z", 1.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.lvl1.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.magician.lvl1.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.magician.green.lvl2.spawn.x", -38.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.green.lvl2.spawn.y", 120.0);
		Config.getDefaultConfig().addDefault("game.entities.magician.green.lvl2.spawn.z", -199.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.green.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.magician.green.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.magician.magenta.lvl2.spawn.x", -37.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.magenta.lvl2.spawn.y", 119.0);
		Config.getDefaultConfig().addDefault("game.entities.magician.magenta.lvl2.spawn.z", 175.5);
		Config.getDefaultConfig().addDefault("game.entities.magician.magenta.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.magician.magenta.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.name", "Armurier");
		Config.getDefaultConfig().addDefault("game.entities.armorer.lvl1.spawn.x", 62.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.lvl1.spawn.y", 129.0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.lvl1.spawn.z", -3.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.lvl1.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.armorer.lvl1.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.green.lvl2.spawn.x", -37.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.green.lvl2.spawn.y", 120.0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.green.lvl2.spawn.z", -197.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.green.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.armorer.green.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.magenta.lvl2.spawn.x", -37.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.magenta.lvl2.spawn.y", 120.0);
		Config.getDefaultConfig().addDefault("game.entities.armorer.magenta.lvl2.spawn.z", 178.5);
		Config.getDefaultConfig().addDefault("game.entities.armorer.magenta.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.armorer.magenta.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.name", "Archerie");
		Config.getDefaultConfig().addDefault("game.entities.fletcher.lvl1.spawn.x", -59.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.lvl1.spawn.y", 132.0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.lvl1.spawn.z", -6.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.lvl1.spawn.yaw", 0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.lvl1.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.green.lvl2.spawn.x", -37.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.green.lvl2.spawn.y", 119.0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.green.lvl2.spawn.z", -194.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.green.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.green.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.magenta.lvl2.spawn.x", -38.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.magenta.lvl2.spawn.y", 120.0);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.magenta.lvl2.spawn.z", 180.5);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.magenta.lvl2.spawn.yaw", 90);
		Config.getDefaultConfig().addDefault("game.entities.fletcher.magenta.lvl2.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.name", "Forgeron");
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.lvl1.spawn.x", -61.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.lvl1.spawn.y", 132.0);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.lvl1.spawn.z", -3.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.lvl1.spawn.yaw", -90);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.lvl1.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.green.lvl2.spawn.x", -46.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.green.lvl2.spawn.y", 118.0);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.green.lvl2.spawn.z", -191.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.green.lvl2.spawn.yaw", -132);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.green.lvl2.spawn.pitch", 1);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.magenta.lvl2.spawn.x", -46.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.magenta.lvl2.spawn.y", 118.0);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.magenta.lvl2.spawn.z", 172.5);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.magenta.lvl2.spawn.yaw", -44);
		Config.getDefaultConfig().addDefault("game.entities.weaponsmith.magenta.lvl2.spawn.pitch", 1);
		Config.getDefaultConfig().addDefault("game.nuggets.material", "gold nugget");
		Config.getDefaultConfig().addDefault("game.nuggets.spawn.x", 0.5);
		Config.getDefaultConfig().addDefault("game.nuggets.spawn.y", 150.0);
		Config.getDefaultConfig().addDefault("game.nuggets.spawn.z", 0.5);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location1.x", -29.0);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location1.y", 129.0);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location1.z", -225.0);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location2.x", -25.0);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location2.y", 132.0);
		Config.getDefaultConfig().addDefault("game.cuboids.green.location2.z", -229.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location1.x", -25.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location1.y", 129.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location1.z", 205.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location2.x", -29.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location2.y", 132.0);
		Config.getDefaultConfig().addDefault("game.cuboids.magenta.location2.z", 209.0);
		Config.getDefaultConfig().addDefault("messages.schedulers.start.while",
				"&7La partie commence dans &e%timer% seconde(s).");
		Config.getDefaultConfig().addDefault("messages.schedulers.start.helpchat",
				"&7Utiliser &e!message &7pour parler dans le chat global");
		Config.getDefaultConfig().addDefault("messages.schedulers.start.noenought",
				"&cIl n'y a pas assez de joueur pour commencer la partie.");
		Config.getDefaultConfig().addDefault("messages.alreadystart",
				"&7La partie à déja commencer, vous avez rejoint en tant que spectateur.");
		Config.getDefaultConfig().addDefault("messages.teams.join",
				"&7Vous avez rejoint l'équipe %teamcolor%%teamname%");
		Config.getDefaultConfig().addDefault("messages.teams.already",
				"&cVous êtes déjà dans l'équipe %teamcolor%%teamname%");
		Config.getDefaultConfig().addDefault("messages.teams.full", "&cL'équipe %teamcolor%%teamname% &cest complete.");
		Config.getDefaultConfig().addDefault("guis.teams.size", 9);
		Config.getDefaultConfig().addDefault("guis.teams.name", "Sélectionner une équipe");
		Config.getDefaultConfig().addDefault("guis.teams.icons.material.type", "banner");
		Config.getDefaultConfig().addDefault("guis.teams.icons.material.vert.data", 10);
		Config.getDefaultConfig().addDefault("guis.teams.icons.material.magenta.data", 13);
		Config.getDefaultConfig().addDefault("teams.maxplayers", 5);
		Config.getDefaultConfig().addDefault("teams.vert.name", "Vert");
		Config.getDefaultConfig().addDefault("teams.vert.namecolor", "dark green");
		Config.getDefaultConfig().addDefault("teams.vert.spawn.x", 0.5);
		Config.getDefaultConfig().addDefault("teams.vert.spawn.y", 106.0);
		Config.getDefaultConfig().addDefault("teams.vert.spawn.z", -182.5);
		Config.getDefaultConfig().addDefault("teams.vert.spawn.yaw", 0);
		Config.getDefaultConfig().addDefault("teams.vert.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("teams.magenta.name", "Magenta");
		Config.getDefaultConfig().addDefault("teams.magenta.namecolor", "dark purple");
		Config.getDefaultConfig().addDefault("teams.magenta.spawn.x", 0.5);
		Config.getDefaultConfig().addDefault("teams.magenta.spawn.y", 106.0);
		Config.getDefaultConfig().addDefault("teams.magenta.spawn.z", 163.5);
		Config.getDefaultConfig().addDefault("teams.magenta.spawn.yaw", 180);
		Config.getDefaultConfig().addDefault("teams.magenta.spawn.pitch", 0);
		Config.getDefaultConfig().addDefault("schedulers.start.minplayers", 2);
		Config.getDefaultConfig().addDefault("schedulers.start.timer", 30);
		Config.saveDefaultConfig();

		GameState.setState(GameState.WAITING);

		new ListenersManager(this).register();
		new GuisManager(this).register();

		TeamsManager.load();
		GameManager.load();
	}

	@Override
	public void onDisable()
	{
		instance = null;

		scoreboardManager.onDisable();

		Bukkit.unloadWorld("world", false);

		WorldUtils.deleteWorld(new File("world"));

		File from = new File("worldcopy");
		File to = new File("world");

		try
		{
			WorldUtils.copyFolder(from, to);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static SkyVulcano getInstance()
	{
		return instance;
	}

	public ScheduledExecutorService getExecutorMonoThread()
	{
		return executorMonoThread;
	}

	public ScheduledExecutorService getScheduledExecutorService()
	{
		return scheduledExecutorService;
	}

	public ScoreboardManager getScoreboardManager()
	{
		return scoreboardManager;
	}

}
