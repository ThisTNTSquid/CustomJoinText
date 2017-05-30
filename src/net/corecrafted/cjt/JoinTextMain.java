package net.corecrafted.cjt;

import java.util.logging.Logger;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinTextMain extends JavaPlugin {
	
	Logger logger = this.getLogger();
	PluginDescriptionFile pdf = this.getDescription();
	ConsoleCommandSender console = this.getServer().getConsoleSender();
	
	public void onEnable(){
		logger.info("CustomJoinText "+pdf.getVersion()+" has been enabled");
		this.getServer().getPluginManager().registerEvents(new JoinLeaveListener(this), this);
	}
	
	public void onDisable(){
		logger.info("CustomJoinText "+pdf.getVersion()+" has been unloaded");
	}
	
	public ConsoleCommandSender getConsole(){
		return this.console;
	}
}
