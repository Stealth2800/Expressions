package com.stealthyone.expressions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stealthyone.expressions.ExpressionsPlugin;

public class PermissionHandler {
	
	private ExpressionsPlugin plugin;
	
	public PermissionHandler(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Returns if a player has permission for a specified command
	 */
	public boolean checkPermission(CommandSender sender, String permission) {
		return checkPermission(sender, permission, true);
	}
	
	public boolean checkPermission(CommandSender sender, String permission, boolean alert) {
		if (sender instanceof Player) {
			if (sender.hasPermission(permission)) {
				return true;
			} else {
				if (alert) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}
				plugin.log.warning("Player " + sender.getName() + " was denied access to a command.");
				return false;
			}
		} else {
			return true;
		}		
	}
}
