package de.Elektherodey.Zauberei.Listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;

import de.Elektherodey.Zauberei.Main.Main;
import de.Elektherodey.Zauberei.Utils.ItemCreator;
import de.Elektherodey.Zauberei.Utils.Maps;
import de.Elektherodey.Zauberei.Utils.Messages;


public class ZauberstabListener implements Listener {
	
	List<Snowball> Avada_snowballs = new ArrayList<>();
	List<Snowball> Expelliarmus_snowballs = new ArrayList<>();
	List<Snowball> WL_snowballs = new ArrayList<>();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(e.getAction() == Action.LEFT_CLICK_BLOCK | e.getAction() == Action.LEFT_CLICK_AIR){
			
			if(Maps.mybesen.containsKey(e.getPlayer().getName())){
				
				for(org.bukkit.entity.Entity ent : e.getPlayer().getWorld().getEntities()){
					if(ent instanceof ArmorStand){
						
						if(Maps.mybesen.get(e.getPlayer().getName()).getLocation().equals(ent.getLocation())){
							if(e.getPlayer().isSneaking()){
								ent.remove();
								Maps.mybesen.remove(e.getPlayer().getName());
							}
							break;
						}
						
					}
				}
				
				return;
			}
			
			if(e.getItem().getType().equals(Material.STICK)){
				
				
				
				
				if(e.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§bEXPELLIARMUS")){
					
					Snowball ball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Snowball.class);
					Expelliarmus_snowballs.add(ball);
					ball.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.6));
					ball.setShooter(e.getPlayer());
					
				}else if(e.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§8Avada Kedavra")){
					
					Snowball ball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Snowball.class);
					Avada_snowballs.add(ball);
					ball.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.6));
					ball.setShooter(e.getPlayer());
				
				}else if(e.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§1Avis")){
					
					e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(0, 2, 1), EntityType.CHICKEN);
					e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(1, 2, 0), EntityType.CHICKEN);
					e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(1, 2, 1), EntityType.CHICKEN);
					e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(0, 2, 0), EntityType.CHICKEN);
				
				}else if(e.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§aWingardium Leviosa")){
					
					Snowball ball = e.getPlayer().getWorld().spawn(e.getPlayer().getEyeLocation(), Snowball.class);
					WL_snowballs.add(ball);
					ball.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.6));
					ball.setShooter(e.getPlayer());
					
				}else if(e.getItem().getItemMeta().getLore().get(0).equalsIgnoreCase("§4Accio Feuerblitz")){
					
					ArmorStand besen = (ArmorStand) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation().add(0, 0, 1), EntityType.ARMOR_STAND);
					
					besen.setArms(true);
					besen.setGravity(false);
					besen.setInvulnerable(true);
					besen.setVisible(false);
					besen.setSmall(false);
					
					besen.setHeadPose(new EulerAngle(300f,360f,23f));
					besen.setRightArmPose(new EulerAngle(160f,0f,120f));
					besen.setBodyPose(new EulerAngle(340, 0, 300));
					
					besen.setHelmet(new ItemStack(Material.WHEAT));
					besen.getEquipment().setItemInMainHand(new ItemStack(Material.STICK));
					
					besen.setPassenger(e.getPlayer());
					
					e.getPlayer().sendMessage(Main.prefix+Messages.getMessage("Besen"));
					
					
					Maps.mybesen.put(e.getPlayer().getName(), besen);
					
				}
			}	
		}if (e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§r§bZauberstab")){
				
				if(Maps.mybesen.containsKey(e.getPlayer().getName())){
					
					for(org.bukkit.entity.Entity ent : e.getPlayer().getWorld().getEntities()){
						if(ent instanceof ArmorStand){
							
							if(Maps.mybesen.get(e.getPlayer().getName()).getLocation().equals(ent.getLocation())){
								ent.teleport(ent.getLocation().multiply(1.3));
								break;
							}
							
						}
					}
					
					return;
				}
				
				if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§r§bZauberstab")){
					Inventory inv = Bukkit.createInventory(null, 9, "§bZauberstab");
					
					inv.setItem(0, ItemCreator.getItem(Material.SKULL_ITEM, "§8Avada Kedavra", "§6Tötet deinen Gegner", ""));
					inv.setItem(1, ItemCreator.getItem(Material.IRON_SWORD, "§bEXPELLIARMUS", "§6Entwaffnet deinen Gegner", ""));
					inv.setItem(2, ItemCreator.getItem(Material.FEATHER, "§1Avis", "§6Spawnt Vögel", ""));
					inv.setItem(3, ItemCreator.getItem(Material.ELYTRA, "§aWingardium Leviosa", "§6Lässt deinen Gegner für 1 Minute scheweben", ""));
					inv.setItem(4, ItemCreator.getItem(Material.STICK, "§4Accio Feuerblitz", "§6Ruft deinen Besen", ""));
					
					
					e.getPlayer().openInventory(inv);
				}
			}
		}
				
	}
	
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		
		if(e.getInventory().getTitle().equalsIgnoreCase("§bZauberstab")){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8Avada Kedavra")){
				e.setCancelled(true);
				
				List<String> lore = new ArrayList<>();
				lore.add("§8Avada Kedavra");
				ItemMeta meta = e.getWhoClicked().getInventory().getItem(0).getItemMeta();
				meta.setLore(lore);
				e.getWhoClicked().getInventory().getItem(0).setItemMeta(meta);
				
				e.getView().close();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bEXPELLIARMUS")){
				e.setCancelled(true);
				
				List<String> lore = new ArrayList<>();
				lore.add("§bEXPELLIARMUS");
				ItemMeta meta = e.getWhoClicked().getInventory().getItem(0).getItemMeta();
				meta.setLore(lore);
				e.getWhoClicked().getInventory().getItem(0).setItemMeta(meta);
				
				e.getView().close();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§1Avis")){
				e.setCancelled(true);
				
				
				
				List<String> lore = new ArrayList<>();
				lore.add("§1Avis");
				ItemMeta meta = e.getWhoClicked().getInventory().getItem(0).getItemMeta();
				meta.setLore(lore);
				e.getWhoClicked().getInventory().getItem(0).setItemMeta(meta);
				
				e.getView().close(); 
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aWingardium Leviosa")){
				e.setCancelled(true);
				
				List<String> lore = new ArrayList<>();
				lore.add("§aWingardium Leviosa");
				ItemMeta meta = e.getWhoClicked().getInventory().getItem(0).getItemMeta();
				meta.setLore(lore);
				e.getWhoClicked().getInventory().getItem(0).setItemMeta(meta);
				
				e.getView().close();
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Accio Feuerblitz")){
				e.setCancelled(true);
				
				List<String> lore = new ArrayList<>();
				lore.add("§4Accio Feuerblitz");
				ItemMeta meta = e.getWhoClicked().getInventory().getItem(0).getItemMeta();
				meta.setLore(lore);
				e.getWhoClicked().getInventory().getItem(0).setItemMeta(meta);
				
				e.getView().close();
			}
		}
		
		
		
		if(e.getCurrentItem().getType().equals(Material.STICK)){
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§r§bZauberstab")){
				
				//Immer schön den Zauberstab in der Hand halten Junge! -- (HarryPotter 5; Mrs Figg)
				
				e.setCancelled(true);
			}
		}
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		if(e.getDamager().getType().equals(EntityType.SNOWBALL)){
			if(Avada_snowballs.contains(((Snowball) e.getDamager()))){
				
				e.getEntity().getWorld().playEffect(e.getEntity().getLocation(), Effect.SLIME, 1);
				e.getEntity().getWorld().strikeLightningEffect(e.getEntity().getLocation());
				
				if(e.getEntity().getType().equals(EntityType.PLAYER)){
					
					Player p = (Player)e.getEntity();
					p.setHealth(0.0);
					
				}else{
					
					e.getEntity().remove();
					
				}
			}else if(Expelliarmus_snowballs.contains(((Snowball) e.getDamager()))){
				if(e.getEntityType().equals(EntityType.PLAYER)){
					Player player = (Player)e.getEntity();
					
					player.getWorld().dropItem(player.getLocation(), player.getInventory().getItem(0));
					
					
					player.getInventory().clear();
					
				}
				
			}else if(WL_snowballs.contains(((Snowball) e.getDamager()))){
				if(e.getEntityType().equals(EntityType.PLAYER)){
					e.setCancelled(true);
					
					Player player = (Player)e.getEntity();
					
					player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 1200, 0, true));
					
				}
				
			}
		}
	}

}
