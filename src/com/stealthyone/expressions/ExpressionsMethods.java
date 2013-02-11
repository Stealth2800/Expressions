package com.stealthyone.expressions;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class ExpressionsMethods {

	private ExpressionsPlugin plugin;
	
	public ExpressionsMethods(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	//Sends a help message to the player
	public void showHelp(String playerName, String command) {
		this.showHelp(plugin.getServer().getPlayer(playerName), command);
	}
	
	public void showHelp(Player player, String command) {
		FileConfiguration config = plugin.cmdHelp.getConfig();
		plugin.log.debug("command = " + command);
		plugin.log.debug("Current configuration section = Commands." + command);
		plugin.log.debug("Size of: Commands." + command + " = " + config.getConfigurationSection("Commands." + command).getValues(false).size());
		plugin.log.debug("Attempt to get: Commands." + command + "." + 1);
		player.sendMessage(config.getString("Commands." + command + "." + 1));
		/*if (plugin.cmdHelp.getConfig().getConfigurationSection("Commands." + command).getValues(false).size() > 1) {
			for (int i = 1; i <= plugin.cmdHelp.getConfig().getConfigurationSection("Commands." + command).getValues(false).size(); i++) {
				player.sendMessage(plugin.cmdHelp.getConfig().getString("Commands." + command + "." + i));
			}
		} else {
			player.sendMessage(plugin.cmdHelp.getConfig().getString("Commands." + command));
		}*/
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
}
