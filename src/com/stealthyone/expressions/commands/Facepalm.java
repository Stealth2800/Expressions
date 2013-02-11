package com.stealthyone.expressions.commands;

import com.stealthyone.expressions.ExpressionsPlugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Facepalm implements CommandExecutor {

	private ExpressionsPlugin plugin;
	
	public Facepalm(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (plugin.config.isEnabled("Facepalm")) {
			if (sender.hasPermission("expressions.facepalm")) {
				plugin.getServer().broadcastMessage(ChatColor.AQUA + "**" + ChatColor.DARK_AQUA + sender.getName() + ChatColor.AQUA + " facepalms**");
			} else {
				sender.sendMessage(ChatColor.RED + "You do not have permission for this command!");
			}
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "This command is disabled!");
			return true;
		}
	}
}
