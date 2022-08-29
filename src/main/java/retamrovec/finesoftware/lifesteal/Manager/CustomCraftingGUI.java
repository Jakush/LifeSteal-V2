package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class CustomCraftingGUI {
	
	LifeSteal l;
	public CustomCraftingGUI(LifeSteal l) {
		this.l = l;
	}
	
	public Inventory CreateInventory() {
		Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, ChatColor.translateAlternateColorCodes('&', "&cHeart &arecipe"));
		
		// Gold_Ingot | Diamond         | Gold_Ingot
		// Diamond    | Netherite_Ingot | Diamond
		// Gold_Ingot | Diamond         | Gold_Ingot
		
		inv.setItem(1, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.first"))));
		inv.setItem(2, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.second"))));
		inv.setItem(3, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.third"))));
		inv.setItem(4, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fourth"))));
		inv.setItem(5, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fifth"))));
		inv.setItem(6, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.sixth"))));
		inv.setItem(7, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.seventh"))));
		inv.setItem(8, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.eighth"))));
		inv.setItem(9, new ItemStack(Material.matchMaterial(l.getConfig().getString("recipe.ingredients.ninth"))));
		
		return inv;
	}
	
	public void OpenInventory(Player p) {
		p.openInventory(CreateInventory());
	}

}
