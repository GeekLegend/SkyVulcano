package fr.geeklegend.vylaria.skyvulcano.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;
import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.schedulers.WinScheduler;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class PlayerDeathListener implements Listener
{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Player victim = event.getEntity();
		Player killer = victim.getKiller();

		event.setDeathMessage(null);

		if (!WinScheduler.isRunning())
		{
			WinScheduler winScheduler = new WinScheduler();
			winScheduler.setRunning(true);
			winScheduler.runTaskTimer(SkyVulcano.getInstance(), 20L, 20L);
		}

		Teams victimTeam = TeamsManager.getTeam(victim);
		Teams killerTeam = TeamsManager.getTeam(killer);

		if (killer != null)
		{
			if (killer == victim)
			{
				Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.game.deathnokiller")
						.replace("%victimTeamColor%", "" + victimTeam.getNameColor())
						.replace("%victimName%", victim.getName()));
			} else 
			{
				Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.game.deathbykiller")
						.replace("%victimTeamColor%", "" + victimTeam.getNameColor())
						.replace("%victimName%", victim.getName())
						.replace("%killerTeamColor%", "" + killerTeam.getNameColor())
						.replace("%killerName%", killer.getName()));
			}
		} else
		{
			Bukkit.broadcastMessage(Config.getDefaultConfig().getString("messages.game.deathnokiller")
					.replace("%victimTeamColor%", "" + victimTeam.getNameColor())
					.replace("%victimName%", victim.getName()));
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(SkyVulcano.getInstance(), new Runnable()
		{
			public void run()
			{
				((CraftPlayer) victim).getHandle().playerConnection
						.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			}
		}, 1L);

		killer.setHealth(20.0D);
		killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
	}

}
