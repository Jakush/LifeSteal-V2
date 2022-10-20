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
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.CustomCraftingGUI;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;

public class InventoryClickListener implements Listener {
	
	LifeSteal l;
	CustomCraftingGUI ccg;
	Heart heart;
	Beacon beacon;
	DebugHandler debug;
	public InventoryClickListener (LifeSteal l, CustomCraftingGUI ccg, Heart heart, DebugHandler debug, Beacon beacon) {
		this.l = l;
		this.ccg = ccg;
		this.heart = heart;
		this.debug = debug;
		this.beacon = beacon;
	}	
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onInteract(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		boolean bool = LifeSteal.getInstance().getEdit().contains(((Player) e.getWhoClicked()).getPlayer());
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"))) {
			if (e.getClickedInventory() == null) {
				e.setCancelled(false);
				p.closeInventory();
			} else e.setCancelled(!bool);
		}
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&9Beacon &arecipe"))) {
			if (e.getCurrentItem() == null) {
				e.setCancelled(false);
				p.closeInventory();
			} else e.setCancelled(!bool);
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"))) {
			boolean bool = LifeSteal.getInstance().getEdit().contains((Player) e.getPlayer());
				if (bool) {
					String first = l.getConfig().getString("recipe.ingredients.first");
					String second = l.getConfig().getString("recipe.ingredients.second");
					String third = l.getConfig().getString("recipe.ingredients.third");
					String fourth = l.getConfig().getString("recipe.ingredients.fourth");
					String fifth = l.getConfig().getString("recipe.ingredients.fifth");
					String sixth = l.getConfig().getString("recipe.ingredients.sixth");
					String seventh = l.getConfig().getString("recipe.ingredients.seventh");
					String eighth = l.getConfig().getString("recipe.ingredients.eighth");
					String ninth = l.getConfig().getString("recipe.ingredients.ninth");
					assert first != null;
					if (Material.matchMaterial(first) != isNullItem(e.getView().getItem(1)).getType()) {
						l.getConfig().set("recipe.ingredients.first", nullCheck(e.getView().getItem(1).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert second != null;
					if (Material.matchMaterial(second) != isNullItem(e.getView().getItem(2)).getType()) {
						l.getConfig().set("recipe.ingredients.second", nullCheck(e.getView().getItem(2).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert third != null;
					if (Material.matchMaterial(third) != isNullItem(e.getView().getItem(3)).getType()) {
						l.getConfig().set("recipe.ingredients.third", nullCheck(e.getView().getItem(3).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert fourth != null;
					if (Material.matchMaterial(fourth) != isNullItem(e.getView().getItem(4)).getType()) {
						l.getConfig().set("recipe.ingredients.fourth", nullCheck(e.getView().getItem(4).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert fifth != null;
					if (Material.matchMaterial(fifth) != isNullItem(e.getView().getItem(5)).getType()) {
						l.getConfig().set("recipe.ingredients.fifth", nullCheck(e.getView().getItem(5).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert sixth != null;
					if (Material.matchMaterial(sixth) != isNullItem(e.getView().getItem(6)).getType()) {
						l.getConfig().set("recipe.ingredients.sixth", nullCheck(e.getView().getItem(6).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert seventh != null;
					if (Material.matchMaterial(seventh) != isNullItem(e.getView().getItem(7)).getType()) {
						l.getConfig().set("recipe.ingredients.seventh", nullCheck(e.getView().getItem(7).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert eighth != null;
					if (Material.matchMaterial(eighth) != isNullItem(e.getView().getItem(8)).getType()) {
						l.getConfig().set("recipe.ingredients.eighth", nullCheck(e.getView().getItem(8).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					assert ninth != null;
					if (Material.matchMaterial(ninth) != isNullItem(e.getView().getItem(9)).getType()) {
						l.getConfig().set("recipe.ingredients.ninth", nullCheck(e.getView().getItem(9).getType()).toString());
						l.saveConfig();
						heart.init(l);
					}
					Player player = (Player) e.getPlayer();
					if (LifeSteal.getInstance().getEdit().contains(player.getName())) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', l.getConfig().getString("messages.recipe_changed")));
					}
					debug.init("Applying changes.");
					LifeSteal.getInstance().getEdit().add(player);
				}
			}
		if (e.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&9Beacon &arecipe"))) {
			boolean bool = LifeSteal.getInstance().getEdit().contains((Player) e.getPlayer());
			if (bool) {
				String first = l.getConfig().getString("recipe.revive.beacon.ingredients.first");
				String second = l.getConfig().getString("recipe.revive.beacon.ingredients.second");
				String third = l.getConfig().getString("recipe.revive.beacon.ingredients.third");
				String fourth = l.getConfig().getString("recipe.revive.beacon.ingredients.fourth");
				String fifth = l.getConfig().getString("recipe.revive.beacon.ingredients.fifth");
				String sixth = l.getConfig().getString("recipe.revive.beacon.ingredients.sixth");
				String seventh = l.getConfig().getString("recipe.revive.beacon.ingredients.seventh");
				String eighth = l.getConfig().getString("recipe.revive.beacon.ingredients.eighth");
				String ninth = l.getConfig().getString("recipe.revive.beacon.ingredients.ninth");
				assert first != null;
				if (Material.matchMaterial(first) != isNullItem(e.getView().getItem(1)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.first", nullCheck(e.getView().getItem(1).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert second != null;
				if (Material.matchMaterial(second) != isNullItem(e.getView().getItem(2)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.second", nullCheck(e.getView().getItem(2).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert third != null;
				if (Material.matchMaterial(third) != isNullItem(e.getView().getItem(3)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.third", nullCheck(e.getView().getItem(3).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert fourth != null;
				if (Material.matchMaterial(fourth) != isNullItem(e.getView().getItem(4)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.fourth", nullCheck(e.getView().getItem(4).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert fifth != null;
				if (Material.matchMaterial(fifth) != isNullItem(e.getView().getItem(5)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.fifth", nullCheck(e.getView().getItem(5).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert sixth != null;
				if (Material.matchMaterial(sixth) != isNullItem(e.getView().getItem(6)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.sixth", nullCheck(e.getView().getItem(6).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert seventh != null;
				if (Material.matchMaterial(seventh) != isNullItem(e.getView().getItem(7)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.seventh", nullCheck(e.getView().getItem(7).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert eighth != null;
				if (Material.matchMaterial(eighth) != isNullItem(e.getView().getItem(8)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.eighth", nullCheck(e.getView().getItem(8).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				assert ninth != null;
				if (Material.matchMaterial(ninth) != isNullItem(e.getView().getItem(9)).getType()) {
					l.getConfig().set("recipe.revive.beacon.ingredients.ninth", nullCheck(e.getView().getItem(9).getType()).toString());
					l.saveConfig();
					beacon.init(l);
				}
				Player player = (Player) e.getPlayer();
				if (LifeSteal.getInstance().getEdit().contains(player.getName())) {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', l.getConfig().getString("messages.recipe_changed")));
				}
				debug.init("Applying changes.");
				LifeSteal.getInstance().getEdit().add(player);
			}
		}
	}

	public Material nullCheck(Material m) {
		if (m == null) {
			return Material.AIR;
		}
		return m;
	}

	public ItemStack isNullItem(ItemStack is) {
		if (is == null) {
			return new ItemStack(Material.AIR, 1);
		}
		return is;
	}

}
