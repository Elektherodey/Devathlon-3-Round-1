package de.Elektherodey.Zauberei.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import de.Elektherodey.Zauberei.Utils.ItemCreator;

public class PortalListener implements Listener {
	
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		if(p.getLocation().getBlock().getType().equals(Material.STONE_PLATE)){
			Block sign = p.getWorld().getBlockAt(p.getLocation().subtract(0, 2, 0));
			if(sign.getType().equals(Material.SIGN_POST) | sign.getType().equals(Material.WALL_SIGN)){
				Sign s = (Sign) sign.getState();
				if(s.getLine(0).equalsIgnoreCase("[DA]")){
					if(s.getLine(1).equalsIgnoreCase("Portal")){
						
						
						Location loc = Bukkit.getWorld("Hogwarts").getSpawnLocation();
						loc.setY(69);
						
							p.teleport(loc);
							p.getInventory().clear();
							p.getInventory().setItem(0, ItemCreator.getItem(Material.STICK, "§r§bZauberstab", "§8<leer>"));
						
						
					}
				}
			}
		}
	}
	
	
	

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		
		e.getPlayer().getInventory().clear();
		
		if(Bukkit.getWorld("Hogwarts") == null){
			Bukkit.createWorld(new WorldCreator("Hogwarts"));
		}
		
		
		e.getPlayer().teleport(new Location(Bukkit.getWorld("Hogwarts"), -123, 46, 75, -179, 3));
	}

}
