package com.stealthyone.expressions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpressionsLogger {
	
	private ExpressionsPlugin plugin;
	
	private static final Logger logger = Logger.getLogger("minecraft");
	
	public ExpressionsLogger(ExpressionsPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void info(String msg) {
		logger.log(Level.INFO, "[" + plugin.getPlName() + "] " + msg);
	}
	
	public void warning(String msg) {
		logger.log(Level.WARNING, "[" + plugin.getPlName() + "] " + msg);
	}
	
	public void severe(String msg) {
		logger.log(Level.SEVERE, "[" + plugin.getPlName() + "] " + msg);
	}
	
	public void debug(String msg) {
		if (plugin.isDebug() == true) {
			logger.log(Level.INFO, "[" + plugin.getPlName() + " DEBUG] " + msg);
		}
	}
}