package com.stealthyone.expressions;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

/**
 * 
 * Expressions
 * ExpressionsMethods.java
 *
 * Main methods for the plugin
 *
 * @author Austin T./Stealth2800
 * @website http://stealthyone.com/
 */
public class ExpressionsMethods {

	private ExpressionsPlugin plugin;
	
	public ExpressionsMethods(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void showHelp(CommandSender sender, String command) {
		showHelp(sender, command, 1);
	}
	
	//Sends a help message to the player
	public void showHelp(CommandSender sender, String command, int page) {
		//For easy reference
		FileConfiguration config = plugin.cmdHelp.getConfig();
		
		plugin.log.debug("Player: " + sender.getName());
		plugin.log.debug("Command: " + command);
		
		plugin.log.debug("Size of command's help: " + config.getConfigurationSection("Help." + command).getValues(false).size());
		plugin.log.debug("Command: " + command + "'s help = " + config.get("Help." + command).toString());
		plugin.log.debug("Command help 1: " + config.getString("Help.expressions.help.1"));
		
		//Get the title of the help page
		//String helpTitle = java.util.Arrays.toString(command.split("\\.")).replace(Pattern.quote("["), Pattern.quote("/")).replace(Pattern.quote(","), "").replace(Pattern.quote("]"), "");
		String helpTitle = java.util.Arrays.toString(command.split(Pattern.quote("."))).replaceAll(Pattern.quote("["), "\\/").replaceAll(Pattern.quote(","), "").replaceAll(Pattern.quote("]"), "");
		plugin.log.debug("helpTitle: " + helpTitle);
		
		//Show 5 items per page
		int itemAmt = config.getConfigurationSection("Help." + command).getValues(false).size();
		plugin.log.debug("ItemAmt: " + itemAmt);
		
		sender.sendMessage(ChatColor.DARK_AQUA + "Help: " + ChatColor.AQUA + helpTitle);
		sender.sendMessage(ChatColor.AQUA + "================");
		
		for (int i = 1; i <= itemAmt; i++) {
			plugin.log.debug("i: " + i);
			try {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Help." + command + "." + Integer.toString(i))));
			} catch (NullPointerException e) {
				plugin.log.debug("Null help item, caught NPE, stopping 'for' loop");
				break;
			}
		}
	}
	
	//Sends the usage of a command to the player
	public void showUsage(CommandSender sender, String command) {
		FileConfiguration config = plugin.cmdHelp.getConfig();
		plugin.log.debug("command = " + command);
		plugin.log.debug("Current configuration section = Commands." + command);
		
		int itemAmt = plugin.cmdHelp.getConfig().getConfigurationSection("Usage." + command).getValues(false).size();
		
		for (int i = 1; i <= itemAmt; i++) {
			try {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Usage." + command + "." + Integer.toString(i)).replace("{PLNAME}", plugin.getPlName())));
			} catch (NullPointerException e) {
				plugin.log.debug("Null usage item, caught NPE, stopping 'for' loop");
				break;
			}
		}
	}
	
	//Returns if the player is vanished or not
	public boolean isVanished(String playerName) {
		return isVanished(plugin.getServer().getPlayer(playerName));
	}
	
	public boolean isVanished(Player player) {
		List <MetadataValue> values = player.getMetadata("vanished");
		boolean valueCast;
		
		for (MetadataValue value : values) {
			valueCast = (boolean)value.value();
			return valueCast;
		}
		return false;
	}
	
	//Slap player
	public final void slapPlayer(String slapperName, String victimName, int force) {
		slapPlayer(slapperName, plugin.getServer().getPlayer(victimName), force);
	}
	
	public final void slapPlayer(String slapperName, Player victim, int force) {
		Random randomGen = new Random();
		Vector slapVel = new Vector(((double)randomGen.nextFloat() * 1.5D - 0.75D) * (double)force, (double)randomGen.nextFloat() / 2.5D + 0.40000000002D * (double)force, ((double)randomGen.nextFloat() * 1.5D - 0.75D) * (double)force);
		victim.setVelocity(slapVel);
		plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getSlapMessage(force).replace("{SENDER}", slapperName).replace("{PLAYER}", victim.getName())));
	}
}