package com.stealthyone.expressions;

import org.bukkit.configuration.file.FileConfiguration;

public class ExpressionsConfig {

	private ExpressionsPlugin plugin;
	private FileConfiguration config;
	
	public ExpressionsConfig(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void load() {
		plugin.reloadConfig();
		config = plugin.getConfig();
		
		//General Plugin Config
		config.addDefault("Debug", true);
		
		//Facepalm Messages
		config.addDefault("Facepalm.Enabled", true);
		
		//Facedesk Messages
		config.addDefault("Facedesk.Enabled", true);		
		
		//Slap Messages
		config.addDefault("Slap.Enabled", true);
		config.addDefault("Slap.Default_Power", 2);
		config.addDefault("Slap.Messages.1", "&3**&b{SENDER} &3poked &b{PLAYER}&3!**");
		config.addDefault("Slap.Messages.2", "&3**&b{SENDER} &3slapped &b{PLAYER}&3!**");
		config.addDefault("Slap.Messages.3", "&3**&b{SENDER} &3slapped &b{PLAYER} &3hard!**");
		config.addDefault("Slap.Messages.4", "&3**&b{SENDER} &3backhanded &b{PLAYER}&3!**");
		config.addDefault("Slap.Messages.5", "&3**&b{SENDER} &3backhanded &b{PLAYER} &3hard!**");
		config.addDefault("Slap.Messages.6", "&3**&b{SENDER} &3launched &b{PLAYER} &3into the air!**");
		config.addDefault("Slap.Messages.7", "&3**&b{SENDER} &3launched &b{PLAYER} &3into orbit!**");
		
		config.options().copyDefaults(true);
		
		//Set isDebug to option defined in config file
		plugin.saveConfig();
	}
	
	public boolean isEnabled(String s) {
		return plugin.getConfig().getBoolean(s + ".Enabled");
	}
	
	public int getDefaultSlapPower() {
		plugin.log.debug("Default slap power = " + plugin.getConfig().getInt("Slap.Default_Power"));
		return plugin.getConfig().getInt("Slap.Default_Power");
	}
	
	public String getSlapMessage(int power) {
		plugin.log.debug("Slap message = " + plugin.getConfig().get("Slap.Messages." + Integer.toString(power)));
		try {
			//return config.get("Slap.Messages." + Integer.toString(power)).toString();
			return plugin.getConfig().get("Slap.Messages." + Integer.toString(power)).toString();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
}
