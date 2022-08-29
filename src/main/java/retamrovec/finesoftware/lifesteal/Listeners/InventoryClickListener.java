package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.CustomCraftingGUI;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Storage.Edit;

public class InventoryClickListener implements Listener {
	
	LifeSteal l;
	CustomCraftingGUI ccg;
	Heart heart;
	DebugHandler debug;
	public InventoryClickListener (LifeSteal l, CustomCraftingGUI ccg, Heart heart, DebugHandler debug) {
		this.l = l;
		this.ccg = ccg;
		this.heart = heart;
		this.debug = debug;
	}	
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"))) {
			if (e.getClickedInventory() == null) {
				e.setCancelled(false);
				p.closeInventory();
			}
			else e.setCancelled(!Edit.getStatus());
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"))) {
			if (Edit.getStatus()) {
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.first")) != e.getView().getItem(1).getType()) {
					l.getConfig().set("recipe.ingredients.first", e.getView().getItem(1).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.second")) != e.getView().getItem(2).getType()) {
					l.getConfig().set("recipe.ingredients.second", e.getView().getItem(2).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.third")) != e.getView().getItem(3).getType()) {
					l.getConfig().set("recipe.ingredients.third", e.getView().getItem(3).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fourth")) != e.getView().getItem(4).getType()) {
					l.getConfig().set("recipe.ingredients.fourth", e.getView().getItem(4).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fifth")) != e.getView().getItem(5).getType()) {
					l.getConfig().set("recipe.ingredients.fifth", e.getView().getItem(5).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.sixth")) != e.getView().getItem(6).getType()) {
					l.getConfig().set("recipe.ingredients.sixth", e.getView().getItem(6).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.seventh")) != e.getView().getItem(7).getType()) {
					l.getConfig().set("recipe.ingredients.seventh", e.getView().getItem(7).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.eighth")) != e.getView().getItem(8).getType()) {
					l.getConfig().set("recipe.ingredients.eighth", e.getView().getItem(8).getType().toString());
					l.saveConfig();
					heart.init(l);
				}
				if (Material.matchMaterial(l.getConfig().getString("recipe.ingredients.ninth")) != e.getView().getItem(9).getType()) {
					l.getConfig().set("recipe.ingredients.ninth", e.getView().getItem(9).getType().toString());

					l.saveConfig();
					heart.init(l);
				}
				Player player = (Player) e.getPlayer();
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', l.getConfig().getString("messages.recipe_changed")));
				debug.init("Applying changes.");
				Edit.setStatus(false);
			}
		}
	}

}
