package com.stealthyone.expressions.commands;

import com.stealthyone.expressions.ExpressionsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Expressions implements CommandExecutor {
	
	private ExpressionsPlugin plugin;
	
	public Expressions(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			plugin.methods.showUsage(sender, "expressions.self");
			return true;
		} else if (args[0].equalsIgnoreCase("version")) {
			/**
			 * Plugin version command
			 */
			if (!plugin.perm.checkPermission(sender, "expressions.version")) {
				return true;
			}
			sender.sendMessage(ChatColor.DARK_AQUA + plugin.getPlName() + ChatColor.DARK_AQUA + " v" + plugin.getPlVersion() + ChatColor.AQUA + " by Stealth2800");
			sender.sendMessage(ChatColor.AQUA + "BukkitDev: " + ChatColor.DARK_AQUA + "http://google.com/");
			sender.sendMessage(ChatColor.AQUA + "For help, type " + ChatColor.DARK_AQUA + "/expressions help");
			return true;
		} else if (args[0].equalsIgnoreCase("reload")) {
			/**
			 * Reload config command
			 */
			if (!plugin.perm.checkPermission(sender, "expressions.reload")) {
				return true;
			}
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.RED + plugin.getPlName() + " reloaded.");
			return true;
		} else if (args[0].equalsIgnoreCase("help")) {
			/**
			 * Help command
			 */
			//Permission check
			if (!plugin.perm.checkPermission(sender, "expressions.help")) {
				//No permission
				return true;
			}
			
			//Checks to see if there are too many arguments
			if (args.length > 2) {
				//Too many arguments, shows usage
				plugin.methods.showUsage(sender, "expressions.help");
				return true;
			} else if (args.length == 2) {
				//If page number in arguments, check if it's an integer
				int pageNum;
				try {
					pageNum = Integer.valueOf(args[1]);
					if (pageNum <= 0) {
						sender.sendMessage(ChatColor.RED + "Page number must be greater than 0!");
						return true;
					}
				} catch (NumberFormatException e) {
					//pageNum not an integer
					sender.sendMessage(ChatColor.RED + "Page number must be an integer!");
					return true;
				}
			}
			
			plugin.methods.showHelp(sender, "expressions.help");
			return true;
		} else if (args[0].equalsIgnoreCase("commands")) {
			/**
			 * Shows available commands in the plugin
			 */
			if (!plugin.perm.checkPermission(sender, "expressions.commands")) {
				return true;
			}
			plugin.methods.showHelp(sender, "expressions.commands");
			return true;
		} else {
			return false;
		}
	}

}
