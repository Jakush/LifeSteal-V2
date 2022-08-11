package retamrovec.finesoftware.lifesteal;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class CraftItemListener implements Listener {
	
	LifeSteal lifesteal;
	public CraftItemListener (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	@SuppressWarnings({ "deprecation" })
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onCraft(CraftItemEvent e){
		Player player = (Player) e.getWhoClicked();
						
			if (e.getRecipe().getResult().getItemMeta().getLore().contains(ChatColor.GRAY + "Get one more heart.") && !(e.isShiftClick())) {
				
				if (lifesteal.getConfig().contains("player." + player.getName())) {
					
					lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName() + 2));
					
					lifesteal.saveConfig();
					
					player.setMaxHealth(player.getMaxHealth() + 2);
					
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.heart_get")));
										
					player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 1);
					
				}
				
				else {
					
					lifesteal.getConfig().set("player." + player.getName(), player.getMaxHealth() + 2);
					
					lifesteal.saveConfig();
					
					player.setMaxHealth(player.getMaxHealth() + 2);
					
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.heart_get")));
					
					player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5, 1);
					
				}
					
			}
			
			else if (e.getRecipe().getResult().getItemMeta().getLore().contains(ChatColor.GRAY + "Get one more heart.") && e.isShiftClick()) {
				
				e.setCancelled(true);
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.cannot_use_shift")));
				
			}
		
	}

}
