package fr.geeklegend.vylaria.skyvulcano.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.geeklegend.vylaria.api.cuboid.Cuboid;
import fr.geeklegend.vylaria.skyvulcano.config.Config;

public class Teams
{

	public static final Teams GREEN = new Teams(Config.getDefaultConfig().getString("teams.vert.name"),
			ChatColor.valueOf(
					Config.getDefaultConfig().getString("teams.vert.namecolor").toUpperCase().replace(" ", "_")),
			(byte) Config.getDefaultConfig().getInt("guis.teams.icons.material.vert.data"),
			new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
					Config.getDefaultConfig().getDouble("teams.vert.spawn.x"),
					Config.getDefaultConfig().getDouble("teams.vert.spawn.y"),
					Config.getDefaultConfig().getDouble("teams.vert.spawn.z"),
					Config.getDefaultConfig().getInt("teams.vert.spawn.yaw"),
					Config.getDefaultConfig().getInt("teams.vert.spawn.pitch")),
			new Cuboid(
					new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location1.x"),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location1.y"),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location1.z")),
					new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location2.x"),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location2.y"),
							Config.getDefaultConfig().getDouble("game.cuboids.green.location2.z"))));
	public static final Teams MAGENTA = new Teams(Config.getDefaultConfig().getString("teams.magenta.name"),
			ChatColor.valueOf(
					Config.getDefaultConfig().getString("teams.magenta.namecolor").toUpperCase().replace(" ", "_")),
			(byte) Config.getDefaultConfig().getInt("guis.teams.icons.material.magenta.data"),
			new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
					Config.getDefaultConfig().getDouble("teams.magenta.spawn.x"),
					Config.getDefaultConfig().getDouble("teams.magenta.spawn.y"),
					Config.getDefaultConfig().getDouble("teams.magenta.spawn.z"),
					Config.getDefaultConfig().getInt("teams.magenta.spawn.yaw"),
					Config.getDefaultConfig().getInt("teams.magenta.spawn.pitch")),
			new Cuboid(
					new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location1.x"),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location1.y"),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location1.z")),
					new Location(Bukkit.getWorld(Config.getDefaultConfig().getString("game.world")),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location2.x"),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location2.y"),
							Config.getDefaultConfig().getDouble("game.cuboids.magenta.location2.z"))));

	private String name;
	private ChatColor nameColor;
	private int size;
	private byte iconData;
	private Location spawnLocation;
	private Cuboid heartCuboid;
	private int hearthp;
	private boolean hearthBroken;
	private boolean villagerLvl2;
	private List<Player> players;

	public Teams(String name, ChatColor nameColor, byte iconData, Location spawnLocation, Cuboid heartCuboid)
	{
		this.name = name;
		this.nameColor = nameColor;
		this.iconData = iconData;
		this.size = 0;
		this.spawnLocation = spawnLocation;
		this.heartCuboid = heartCuboid;
		this.hearthp = 100;
		this.hearthBroken = false;
		this.villagerLvl2 = false;
		this.players = new ArrayList<Player>();
	}

	public void addPlayer(Player player)
	{
		players.add(player);
		size++;
	}

	public void removePlayer(Player player)
	{
		players.remove(player);
		size--;
	}

	public String getName()
	{
		return name;
	}

	public ChatColor getNameColor()
	{
		return nameColor;
	}

	public Location getSpawnLocation()
	{
		return spawnLocation;
	}

	public Cuboid getHeartCuboid()
	{
		return heartCuboid;
	}

	public void setHeartCuboid(Cuboid heartCuboid)
	{
		this.heartCuboid = heartCuboid;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public byte getIconData()
	{
		return iconData;
	}

	public int getHeartHp()
	{
		return hearthp;
	}
	
	public void setHeartHp(int hearthp)
	{
		this.hearthp = hearthp;
	}
	
	public void removeHeartHp(int hearthp)
	{
		this.hearthp -= hearthp;
	}
	
	public boolean isHearthBroken()
	{
		return hearthBroken;
	}
	
	public void setHearthBroken(boolean hearthBroken)
	{
		this.hearthBroken = hearthBroken;
	}
	
	public boolean isVillagerLvl2()
	{
		return villagerLvl2;
	}
	
	public void setVillagerLvl2(boolean villagerLvl2)
	{
		this.villagerLvl2 = villagerLvl2;
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}

}
