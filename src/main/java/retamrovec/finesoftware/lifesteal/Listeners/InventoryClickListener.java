package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.CustomCraftingGUI;

public class InventoryClickListener implements Listener {
	
	LifeSteal lifesteal;
	CustomCraftingGUI ccg;
	public InventoryClickListener (LifeSteal lifesteal, CustomCraftingGUI ccg) {
		this.lifesteal = lifesteal;
		this.ccg = ccg;
	}	
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onInteract(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		
		// Gold_Ingot | Diamond         | Gold_Ingot
		// Diamond    | Netherite_Ingot | Diamond
		// Gold_Ingot | Diamond         | Gold_Ingot
		

		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"))) {
			if (e.getClickedInventory() == null) {
				e.setCancelled(false);
				p.closeInventory();
			}
			else {
				e.setCancelled(true);
			}
		}
		else {
		} 
	}

}
