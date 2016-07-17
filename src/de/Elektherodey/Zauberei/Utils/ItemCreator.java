package de.Elektherodey.Zauberei.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemCreator {

	
	public static ItemStack getItem(Material mat, String name, String type){
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
	
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lore.add(type);
		
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack getItem(Material mat, String name, String beschreibung1, String beschreibung2){
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		lore.add(beschreibung1);
		lore.add(beschreibung2);
		
		meta.setLore(lore);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		
		return item;
	}
}
