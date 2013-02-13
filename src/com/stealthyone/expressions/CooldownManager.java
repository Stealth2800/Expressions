package com.stealthyone.expressions;

import java.text.ParseException;
import java.util.Date;

import org.bukkit.command.CommandSender;

public class CooldownManager extends CustomFileManager {
	
	ExpressionsPlugin plugin;
	
	public CooldownManager(ExpressionsPlugin plugin, String fileName) {
		super(plugin, fileName);
		this.plugin = plugin;
	}
	
	public final boolean canSlap(CommandSender sender) {
		/**
		 * Returns if the player can slap or not
		 */
		if (!this.getConfig().contains("Cooldowns." + sender.getName())) {
			plugin.log.debug("Cooldown in file doesn't exist for " + sender.getName() + ", creating");
			this.getConfig().set("Cooldowns." + sender.getName(), plugin.dateFormat.format(new Date()));
		}
		//Date currentTime = plugin.dateFormat.format(new Date());
		//Date currentTime = new Date();
		//Date cooldownTime = this.stringToDate(this.getConfig().getString("Cooldowns." + sender.getName()));
		//plugin.log.debug("Time between two dates: " + ((currentTime.getTime() - cooldownTime.getTime()) / 1000));
		//if ((currentTime.getTime() - cooldownTime.getTime()) / 1000 >= plugin.getConfig().getInt("Slap.Cooldown")) {
		if (getCooldownSeconds(sender) >= plugin.getConfig().getInt("Slap.Cooldown")) {
			this.getConfig().set("Cooldowns." + sender.getName(), plugin.dateFormat.format(new Date()));
			this.saveFile();
			return true;
		} else {
			return false;
		}
	}
	
	private final Date stringToDate(String date) {
		try {
			return plugin.dateFormat.parse(date);
		} catch (ParseException e) {
			plugin.log.severe("Unable to convert string to date!");
			return null;
		}
	}

	public final int getCooldownSeconds(CommandSender sender) {
		Date currentTime = new Date();
		Date cooldownTime = this.stringToDate(this.getConfig().getString("Cooldowns." + sender.getName()));
		return (int) (currentTime.getTime() - cooldownTime.getTime()) / 1000;
	}
}
