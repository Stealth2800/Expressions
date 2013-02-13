package com.stealthyone.expressions;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * 
 * Expressions
 * CustomFileManager.java
 *
 * Manages files that save to the /plugins/plugindirectory directory
 *
 * @author Austin T./Stealth2800
 * @website http://stealthyone.com/
 */
public class CustomFileManager {

	private ExpressionsPlugin plugin;
	
	private String fileName;
	private File customFile;
	private FileConfiguration customConfig;
	
	public CustomFileManager(ExpressionsPlugin plugin, String fileName) {
		this.plugin = plugin;
		this.fileName = fileName;
	}
	
	public final void reloadFile() {
		/**
		 * Reloads the file
		 */
		plugin.log.debug(customFile + " - reloadFile()");
		if (customFile == null) {
			plugin.log.debug(customFile + " - customFile == null");
			customFile = new File(plugin.getDataFolder(), fileName);
		}
		customConfig = YamlConfiguration.loadConfiguration(customFile);
	}
	
	public final FileConfiguration getConfig() {
		/**
		 * Returns the file
		 */
		plugin.log.debug(customFile + " - getConfig()");
		if (customConfig == null) {
			plugin.log.debug(customFile + ".yml - customConfig == null");
			this.reloadFile();
		}
		return customConfig;
	}
	
	public final void saveFile() {
		/**
		 * Save changes to file
		 */
		plugin.log.debug("Saving file");
        if (customConfig == null || customFile == null) {
            return;
        } else {
        	plugin.log.debug("Attempting to save to file");
            try {
            	plugin.log.debug("Config saved successfully");
                getConfig().save(customFile);
            } catch (IOException ex) {
                plugin.log.severe("Could not save config");
            }
        }
	}
}
