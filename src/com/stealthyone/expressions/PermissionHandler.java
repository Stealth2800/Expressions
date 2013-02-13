package com.stealthyone.expressions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.stealthyone.expressions.ExpressionsPlugin;

/**
 * 
 * Expressions
 * PermissionHandler.java
 *
 * Manages permissions for players
 *
 * @author Austin T./Stealth2800
 * @website http://stealthyone.com/
 */
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
		/**
		 * Checks the permission of a specified player
		 */
		if (sender instanceof Player) {
			plugin.log.debug("(CHECKPERMISSION) player: " + sender.getName() + " is a player!");
			if (sender.hasPermission(permission)) {
				plugin.log.debug("Player " + sender.getName() + " has permission for: " + permission);
				return true;
			} else {
				plugin.log.debug("Player " + sender.getName() + " doesn't have permission for: " + permission);
				if (alert) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}
				plugin.log.warning("Player " + sender.getName() + " was denied access to a command.");
				return false;
			}
		} else {
			plugin.log.debug("(CHECKPERMISSION) player: " + sender.getName() + " is NOT a player!");
			return true;
		}		
	}
}
