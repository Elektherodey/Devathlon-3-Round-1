package de.Elektherodey.Zauberei.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.Elektherodey.Zauberei.Main.Main;

public class Messages {

	
	public static File messages = new File(Main.plugin.getDataFolder()+"", "Messages.yml");
	public static FileConfiguration getMSG = YamlConfiguration.loadConfiguration(messages);
	
	
	
	
	public static void reload(){
		messages = new File(Main.plugin.getDataFolder()+"", "Messages.yml");
		getMSG = YamlConfiguration.loadConfiguration(messages);
	}
	
	public static String getMessage(String name){
		if(getMSG.getString(name) != null){
			
			String Message = getMSG.getString(name);
			
			Message = Message.replace("&", "§");
					
			return Message;
		}
		return getMSG.getString("ERROR");
		
	}
	
}
