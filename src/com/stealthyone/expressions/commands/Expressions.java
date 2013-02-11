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
		String cmdArg;
		if (args.length == 1) {
			cmdArg = args[0];
		} else if (args.length > 1) {
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
			return true;
		} else {
			cmdArg = null;
		}
		
		plugin.log.debug("Expressions cmdArg = " + cmdArg);
		
		if (cmdArg == null || cmdArg.equalsIgnoreCase("version")) {
			/**
			 * Plugin version command
			 */
			if (!plugin.perm.checkPermission(sender, "expressions.version")) {
				return true;
			}
			sender.sendMessage(ChatColor.DARK_AQUA + plugin.getPlName() + ChatColor.AQUA + " version " + ChatColor.DARK_AQUA + plugin.getPlVersion() + ChatColor.AQUA + " by Stealth2800");
			sender.sendMessage(ChatColor.AQUA + "BukkitDev: " + ChatColor.DARK_AQUA + "http://google.com/");
			sender.sendMessage(ChatColor.AQUA + "For help, type " + ChatColor.DARK_AQUA + "/expressions help");
			return true;
		} else if (cmdArg.equalsIgnoreCase("reload")) {
			/**
			 * Reload config command
			 */
			if (!plugin.perm.checkPermission(sender, "expressions.reload")) {
				return true;
			}
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.RED + plugin.getPlName() + " reloaded.");
			return true;
		} else if (cmdArg.equalsIgnoreCase("help")) {
			/**
			 * Help command
			 */
			plugin.cmdHelp.getConfig();
			plugin.log.debug("Just getConfig()");
			plugin.cmdHelp.getConfig().getConfigurationSection("Commands").getValues(false).size();
			plugin.log.debug("getConfigurationSection 'Commands' size");
			//sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.cmdHelp.getConfig().getString("Commands.expressions.help.1")));
			plugin.methods.showHelp(sender.getName(), "expressions.help");
			return true;
		} else {
			return false;
		}
	}

}
