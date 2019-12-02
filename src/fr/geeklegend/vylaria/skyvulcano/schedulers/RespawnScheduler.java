package fr.geeklegend.vylaria.skyvulcano.schedulers;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class RespawnScheduler extends BukkitRunnable
{

	private static Player player;
	private static int timer = 4;
	private static boolean running;

	@Override
	public void run()
	{
		timer--;

		if (timer == 3 || timer == 2 || timer == 1)
		{
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 18000, 2));
			player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 18000, 2));
			player.sendMessage(Config.getDefaultConfig().getString("messages.game.respawn").replace("&", "§")
					.replace("%timer%", "" + timer));
		} else if (timer == 0)
		{
			stop();
			
			Teams team = TeamsManager.getTeam(player);

			GameManager.removeRespawn(player);
			GameManager.respawn(player);
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = 4;
	}

	public static void setPlayer(Player player)
	{
		RespawnScheduler.player = player;
	}

	public static int getTimer()
	{
		return timer;
	}

	public static boolean isRunning()
	{
		return running;
	}

	public static void setRunning(boolean running)
	{
		RespawnScheduler.running = running;
	}

}
