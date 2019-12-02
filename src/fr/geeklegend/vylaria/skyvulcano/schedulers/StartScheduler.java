package fr.geeklegend.vylaria.skyvulcano.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.skyvulcano.config.Config;
import fr.geeklegend.vylaria.skyvulcano.game.GameManager;
import fr.geeklegend.vylaria.skyvulcano.game.GameState;

public class StartScheduler extends BukkitRunnable
{

	private static int timer;
	private static boolean running;

	public StartScheduler()
	{
		this.timer = Config.getDefaultConfig().getInt("schedulers.start.timer");
	}

	@Override
	public void run()
	{
		timer--;

		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(timer);
		}

		if (Bukkit.getOnlinePlayers().size() < Config.getDefaultConfig().getInt("schedulers.start.minplayers"))
		{
			stop();

			GameState.setState(GameState.WAITING);

			Bukkit.broadcastMessage(
					Config.getDefaultConfig().getString("messages.schedulers.start.noenought").replace("&", "§"));
		}

		if (timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.NOTE_PLING, 20.0f, 20.0f);
				players.sendMessage(Config.getDefaultConfig().getString("messages.schedulers.start.while")
						.replace("%timer%", "" + timer).replace("&", "§"));
			}
		} else if (timer == 0)
		{
			stop();

			GameState.setState(GameState.GAME);

			GameManager.setup();

			for (Player players : Bukkit.getOnlinePlayers())
			{
				players.playSound(players.getLocation(), Sound.LEVEL_UP, 20.0f, 20.0f);
				players.sendMessage(
						Config.getDefaultConfig().getString("messages.schedulers.start.helpchat").replace("&", "§"));
			}
		}
	}

	public void stop()
	{
		cancel();
		running = false;
		timer = Config.getDefaultConfig().getInt("schedulers.start.timer");
		for (Player players : Bukkit.getOnlinePlayers())
		{
			players.setLevel(0);
		}
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
		StartScheduler.running = running;
	}

}
