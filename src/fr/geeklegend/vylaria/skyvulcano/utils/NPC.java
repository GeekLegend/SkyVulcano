package fr.geeklegend.vylaria.skyvulcano.utils;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class NPC
{
	
	public static void create(Location location, String name)
	{
		location.getChunk().load();
		
		Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);       
        villager.setRemoveWhenFarAway(false);
		villager.setCustomNameVisible(true);
        villager.setCustomName(name);
       
        EntityVillager entityVillager = ((CraftVillager) villager).getHandle();
        entityVillager.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        NBTTagCompound nbttag = entityVillager.getNBTTag();
       
        if(nbttag == null) nbttag = new NBTTagCompound();
       
        entityVillager.c(nbttag);
        nbttag.setInt("NoAI", 1);
        entityVillager.f(nbttag);
	}

}
