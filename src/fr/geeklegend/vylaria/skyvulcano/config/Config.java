package fr.geeklegend.vylaria.skyvulcano.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.geeklegend.vylaria.skyvulcano.SkyVulcano;

public class Config {

	private static FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(getFile("config"));

	public static void create(String fileName) {
		if (!SkyVulcano.getInstance().getDataFolder().exists()) {
			SkyVulcano.getInstance().getDataFolder().mkdir();
		}
		File file = new File(SkyVulcano.getInstance().getDataFolder(), fileName + ".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void load(String fileName) {
		YamlConfiguration.loadConfiguration(getFile(fileName));
	}

	public static void saveDefaultConfig() {
		try {
			defaultConfig.save(getFile("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getFile(String fileName) {
		return new File(SkyVulcano.getInstance().getDataFolder(), fileName + ".yml");
	}

	public static FileConfiguration getDefaultConfig() {
		return defaultConfig;
	}

}