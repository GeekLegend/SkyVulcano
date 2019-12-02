package fr.geeklegend.vylaria.skyvulcano.listeners.other;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.geeklegend.vylaria.skyvulcano.game.GameState;
import fr.geeklegend.vylaria.skyvulcano.teams.Teams;
import fr.geeklegend.vylaria.skyvulcano.teams.manager.TeamsManager;

public class EntityDamageByEntityListener implements Listener
{

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
	{
		Entity damaged = event.getEntity();
		Entity damager = event.getDamager();
		DamageCause damageCause = event.getCause();

		if (GameState.isState(GameState.GAME))
		{
			if (damager instanceof Arrow)
			{
				Arrow arrow = (Arrow) damager;

				if (arrow.getShooter() instanceof Player)
				{
					Entity entityHit = event.getEntity();

					if (entityHit instanceof Player)
					{
						Player shooter = (Player) arrow.getShooter();
						Player playerHit = (Player) entityHit;

						Teams shooterTeam = TeamsManager.getTeam(shooter);
						Teams playerHitTeam = TeamsManager.getTeam(playerHit);

						if (shooterTeam == playerHitTeam)
						{
							event.setCancelled(true);
						}
					}
				}
			}
			
			Player victim = (Player) damaged;
			Player killer = (Player) damager;

			Teams victimTeam = TeamsManager.getTeam(victim);
			Teams killerTeam = TeamsManager.getTeam(killer);

			if (damager instanceof Player)
			{
				if (damaged instanceof Player)
				{
					if (victimTeam == killerTeam)
					{
						event.setCancelled(true);
					}
				}
			}
		}
	}

}
