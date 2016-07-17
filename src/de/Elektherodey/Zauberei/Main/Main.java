package de.Elektherodey.Zauberei.Main;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.Elektherodey.Zauberei.Listener.PortalListener;
import de.Elektherodey.Zauberei.Listener.ZauberstabListener;
import de.Elektherodey.Zauberei.Utils.Messages;

public class Main extends JavaPlugin {
	
	public static String prefix;
	public static Main plugin;
	
	@Override
	public void onEnable(){
		plugin = this;
		
		Messages.getMSG.addDefault("Prefix", "[&6Zauberei&r]");
		Messages.getMSG.addDefault("NoPermission", "&4Du hast nicht genug Permissions!");
		Messages.getMSG.addDefault("Besen", "&7Mit Linksclick und Shift entfernst du den Besen und kannst wieder Zaubern!");
		
		Messages.getMSG.options().copyDefaults(true);
		
		try {
			Messages.getMSG.save(Messages.messages);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		prefix = Messages.getMessage("Prefix");
		
		Bukkit.getPluginManager().registerEvents(new PortalListener(), this);
		Bukkit.getPluginManager().registerEvents(new ZauberstabListener(), this);
		
	}
	
	@Override
	public void onDisable(){
		
	}

}
