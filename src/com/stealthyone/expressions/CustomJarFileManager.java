package com.stealthyone.expressions;

import java.io.File;
import java.io.InputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomJarFileManager {

	private ExpressionsPlugin plugin;
	
	private String fileName;
	private File customFile;
	private FileConfiguration customConfig;
	
	public CustomJarFileManager(ExpressionsPlugin plugin, String fileName) {
		this.plugin = plugin;
		this.fileName = fileName;
	}
	
	public final void reloadFile() {
		plugin.log.debug(customFile + " - reloadFile()");
		/*if (customFile == null) {
			plugin.log.debug(customFile + " - customFile == null");
			customFile = new File(plugin.getDataFolder(), fileName);
		}
		customConfig = YamlConfiguration.loadConfiguration(customFile);*/
		
		InputStream defStream = plugin.getResource(fileName + ".yml");
		if (defStream != null) {
			plugin.log.debug("defStream != null");
			customConfig = YamlConfiguration.loadConfiguration(defStream);
		}
	}
	
	public final FileConfiguration getConfig() {
		plugin.log.debug(customFile + ".yml - getConfig()");
		if (customConfig == null) {
			plugin.log.debug(customFile + ".yml - customConfig == null");
			this.reloadFile();
		}
		return customConfig;
	}
	
}
