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
		/**
		 * Loads the file and gets config values from it
		 */
		plugin.log.debug(customFile + " - reloadFile()");
		
		InputStream defStream = plugin.getResource(fileName + ".yml");
		if (defStream != null) {
			plugin.log.debug("defStream != null");
			customConfig = YamlConfiguration.loadConfiguration(defStream);
		}
	}
	
	public final FileConfiguration getConfig() {
		/**
		 * Returns the fileConfig for referencing objects in file
		 */
		plugin.log.debug(customFile + ".yml - getConfig()");
		if (customConfig == null) {
			plugin.log.debug(customFile + ".yml - customConfig == null");
			this.reloadFile();
		}
		return customConfig;
	}
	
}
