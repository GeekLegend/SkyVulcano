package fr.geeklegend.vylaria.skyvulcano.schedulers;

import org.bukkit.scheduler.BukkitRunnable;

import fr.geeklegend.vylaria.skyvulcano.game.WinManager;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;

public class WinScheduler extends BukkitRunnable
{
	
	private static int timer = 1;
	private static boolean running;
	private static WinManager winManager;
	
	@Override
	public void run()
	{
		timer--;
		
		if (timer == 0)
		{
			if (Teams.GREEN.getSize() == 0)
			{
				winManager = new WinManager(Teams.MAGENTA.getNameColor() + Teams.MAGENTA.getName());
				winManager.check();
			} else if (Teams.MAGENTA.getSize() == 0)
			{
				winManager = new WinManager(Teams.GREEN.getNameColor() + Teams.GREEN.getName());
				winManager.check();
			}
			
			stop();
		}
	}
	
	public void stop()
	{
		cancel();
		running = false;
		timer = 1;
	}

	public static WinManager getWinManager()
	{
		return winManager;
	}
	
	public static int getTimer()
	{
		return timer;
	}
	
	public static void setTimer(int timer)
	{
		WinScheduler.timer = timer;
	}
	
	public static boolean isRunning()
	{
		return running;
	}
	
	public static void setRunning(boolean running)
	{
		WinScheduler.running = running;
	}
	
}
