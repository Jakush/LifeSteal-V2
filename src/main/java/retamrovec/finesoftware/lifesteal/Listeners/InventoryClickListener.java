package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.CustomCraftingGUI;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Storage.Edit;

import java.util.Objects;

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
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.first"))))).equals(e.getInventory().getItem(1)))) {
					l.getConfig().set("recipe.ingredients.first", Objects.requireNonNull(e.getInventory().getItem(1)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.second"))))).equals(e.getInventory().getItem(2)))) {
					l.getConfig().set("recipe.ingredients.second", Objects.requireNonNull(e.getInventory().getItem(2)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.third"))))).equals(e.getInventory().getItem(3)))) {
					l.getConfig().set("recipe.ingredients.third", Objects.requireNonNull(e.getInventory().getItem(3)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.fourth"))))).equals(e.getInventory().getItem(4)))) {
					l.getConfig().set("recipe.ingredients.fourth", Objects.requireNonNull(e.getInventory().getItem(4)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.fifth"))))).equals(e.getInventory().getItem(5)))) {
					l.getConfig().set("recipe.ingredients.fifth", Objects.requireNonNull(e.getInventory().getItem(5)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.sixth"))))).equals(e.getInventory().getItem(6)))) {
					l.getConfig().set("recipe.ingredients.sixth", Objects.requireNonNull(e.getInventory().getItem(6)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.seventh"))))).equals(e.getInventory().getItem(7)))) {
					l.getConfig().set("recipe.ingredients.seventh", Objects.requireNonNull(e.getInventory().getItem(7)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.eighth"))))).equals(e.getInventory().getItem(8)))) {
					l.getConfig().set("recipe.ingredients.eighth", Objects.requireNonNull(e.getInventory().getItem(8)).getType());
					l.saveConfig();
					heart.init(l);
				}
				if (!(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(l.getConfig().getString("recipe.ingredients.ninth"))))).equals(e.getInventory().getItem(9)))) {
					l.getConfig().set("recipe.ingredients.ninth", Objects.requireNonNull(e.getInventory().getItem(9)).getType());
					l.saveConfig();
					heart.init(l);
				}
				Edit.setStatus(false);
			}
		}
	}

}
