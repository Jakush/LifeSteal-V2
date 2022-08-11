package retamrovec.finesoftware.lifesteal;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomCraftingManager {
	
	LifeSteal l;
	public CustomCraftingManager(LifeSteal l) {
		this.l = l;
	}
		
	public void registerRecipe(Plugin plugin) {
		ItemStack enchgoldenapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		ItemMeta enchgoldenapplemeta = enchgoldenapple.getItemMeta();
		enchgoldenapplemeta.setDisplayName("Life");
		enchgoldenapplemeta.setLore(List.of(ChatColor.GRAY + "Get one more heart."));
		enchgoldenapple.setItemMeta(enchgoldenapplemeta); 
		{
            NamespacedKey key = new NamespacedKey(plugin, "enchgoldenapple");
            ShapedRecipe recipe = new ShapedRecipe(key, enchgoldenapple);
            recipe.shape(
                    "fst",
                    "oix",
                    "egn");
            recipe.setIngredient('f', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.first")));
            recipe.setIngredient('s', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.second")));
            recipe.setIngredient('t', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.thirst")));
            recipe.setIngredient('o', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fourth")));
            recipe.setIngredient('i', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.fifth")));
            recipe.setIngredient('x', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.sixth")));
            recipe.setIngredient('e', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.seventh")));
            recipe.setIngredient('g', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.eighth")));
            recipe.setIngredient('n', Material.matchMaterial(l.getConfig().getString("recipe.ingredients.ninth")));

            Bukkit.addRecipe(recipe);
		}
	}
	
	public NamespacedKey getRecipe(Plugin plugin) {
		ItemStack enchgoldenapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
		ItemMeta enchgoldenapplemeta = enchgoldenapple.getItemMeta();
		enchgoldenapplemeta.setDisplayName("Life");
		enchgoldenapplemeta.setLore(List.of(ChatColor.GRAY + "Get one more heart."));
		enchgoldenapple.setItemMeta(enchgoldenapplemeta); 
		{
            NamespacedKey key = new NamespacedKey(plugin, "enchgoldenapple");
            
            return key;
		}
	}

}
