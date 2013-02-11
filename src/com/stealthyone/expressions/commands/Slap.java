package com.stealthyone.expressions.commands;

import java.util.List;

import com.stealthyone.expressions.ExpressionsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Slap implements CommandExecutor {

	private ExpressionsPlugin plugin;
	
	public Slap(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/***
		 * Makes sure the command is enabled in the config file
		 */
		if (plugin.config.isEnabled("Slap")) {
			String senderName;
			/**
			 * Check to see if sender is the console or a player
			 */
			if (sender instanceof Player) {
				if (!(sender.hasPermission("expressions.slap"))) {
					sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
					return true;
				} else {
					senderName = sender.getName();
				}
			} else {
				senderName = "CONSOLE";
			}
			
			/**
			 * Checks if the amount of arguments is correct
			 */
			if (args.length == 1 || args.length == 2) {
				List<Player> matchedPlayers = plugin.getServer().matchPlayer(args[0]);
				Player target;
				/**
				 * Attempts to match the player name in the argument
				 */
				if (matchedPlayers.size() > 1) {
					//Argument matches multiple players
					sender.sendMessage(ChatColor.RED + "Player '" + ChatColor.DARK_RED + args[0] + ChatColor.RED + "' matches multiple online players!");
					return true;
				} else if (matchedPlayers.size() == 0) {
					//Argument matches no players
					sender.sendMessage(ChatColor.RED + "Unable to find player '" + ChatColor.DARK_RED + args[0] + ChatColor.RED + "'");
					return true;
				} else {
					//Player found, sets 'target' variable to that player
					target = plugin.getServer().getPlayer(args[0]);
					if (plugin.methods.isVanished(target)) {
						plugin.log.debug("Attempt to slap vanished player.");
						sender.sendMessage(ChatColor.RED + "Unable to find player '" + ChatColor.DARK_RED + args[0] + ChatColor.RED + "'");
						return true;
					}
				}
				
				int power;
				
				if (args.length != 1) {
					try {
						power = Integer.valueOf(args[1]);
						plugin.log.debug("Slap power = " + power);
						if (power <= 0) {
							sender.sendMessage(ChatColor.RED + "Power must be greater than 0!");
							return true;
						}
					} catch (NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "Power must be an integer!");
						return true;
					}
				} else {
					power = plugin.config.getDefaultSlapPower();
				}
				
				plugin.log.debug("Checking for permission: expressions.slap." + power + " for player: " + sender.getName());
				
				if (sender.hasPermission("expressions.slap." + power)) {
					String slapMessage = plugin.config.getSlapMessage(power);
					if (slapMessage != null) {
						plugin.log.debug("Slap message = " + ChatColor.translateAlternateColorCodes('&', plugin.config.getSlapMessage(power).replace("{SENDER}", senderName).replace("{PLAYER}", target.getName())));
						plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getSlapMessage(power).replace("{SENDER}", senderName).replace("{PLAYER}", target.getName()))); 
					} else {
						sender.sendMessage(ChatColor.RED + "Power is too high!");
					}
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permission to slap with a power of " + ChatColor.DARK_RED + power);
					return true;
				}
			} else {
				//Not enough arguments, returns usage
				return false;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "This command is disabled!");
			return true;
		}
	}

}
