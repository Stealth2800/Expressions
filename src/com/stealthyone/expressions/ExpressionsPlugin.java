package com.stealthyone.expressions;

import java.text.SimpleDateFormat;

import com.stealthyone.expressions.commands.*;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 
 * Expressions
 * ExpressionsPlugin.java
 *
 * Main class + logic of the plugin
 *
 * @author Austin T./Stealth2800
 * @website http://stealthyone.com/
 */
public class ExpressionsPlugin extends JavaPlugin {
	
	public ExpressionsLogger log;
	public ExpressionsConfig config;
	public ExpressionsMethods methods;
	
	public PermissionHandler perm;
	
	public CustomJarFileManager cmdHelp;
	public CooldownManager cdManager;
	
	public SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@Override
	public void onEnable() {
		//Setup Logger
		log = new ExpressionsLogger(this);
		
		//Setup config
		config = new ExpressionsConfig(this);
		config.load();
		
		//Setup methods
		methods = new ExpressionsMethods(this);
		
		//Setup permission handler
		perm = new PermissionHandler(this);
		
		//Setup custom files
		cmdHelp = new CustomJarFileManager(this, "cmdHelp");
		cmdHelp.reloadFile();
		cdManager = new CooldownManager(this, "cooldowns.yml");
		cdManager.reloadFile();
		
		//Register Commands
		getCommand("expressions").setExecutor(new Expressions(this));
		getCommand("slap").setExecutor(new Slap(this));
		//getCommand("slapban").setExecutor(new SlapBan(this));
		getCommand("facepalm").setExecutor(new Facepalm(this));
		getCommand("facedesk").setExecutor(new Facedesk(this));
		
		//Output to console
		log.info(getPlName() + " v" + getPlVersion() + " by Stealth2800 enabled!");
	}
	
	@Override
	public void onDisable() {
		//Output to console
		log.info(getPlName() + " disabled!");
	}
	
	public final boolean isDebug() {
		return this.getConfig().getBoolean("Debug");
	}
	
	public String getPlName() {
		return this.getDescription().getName();
	}
	
	public String getPlVersion() {
		return this.getDescription().getVersion();
	}
}
