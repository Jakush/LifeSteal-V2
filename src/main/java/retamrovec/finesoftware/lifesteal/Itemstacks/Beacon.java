package retamrovec.finesoftware.lifesteal.Itemstacks;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.Message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Beacon {

    private ItemStack itemStack;
    private final LifeSteal l;
    public Beacon(LifeSteal l) {
        this.l = l;
    }

    public NamespacedKey key(JavaPlugin plugin){
        return new NamespacedKey(plugin, "Revive Beacon");
    }
    public void register(JavaPlugin plugin){
        itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.fromString("ff1654b0-10f2-48b6-9c05-483b75f6549e"), null);
        profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzY3ZWFmMTgyY2VmMDFhN2VjYmEyMjZkNGJjZTlhY2U3N2E1MjUzYWM5NGFhMTY2YTliMzFjZTdmYmNhODI0NSJ9fX0="));
        Field field;
        try {
            field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bRevive Beacon"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Place this and setup revive for player!"));
        meta.setLore(lore);
        itemStack.setItemMeta(meta);

        {
            ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "Revive Beacon"), itemStack);
            recipe.shape(
                    "fst",
                    "oix",
                    "egn");
            recipe.setIngredient('f', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.first")));
            recipe.setIngredient('s', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.second")));
            recipe.setIngredient('t', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.third")));
            recipe.setIngredient('o', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.fourth")));
            recipe.setIngredient('i', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.fifth")));
            recipe.setIngredient('x', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.sixth")));
            recipe.setIngredient('e', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.seventh")));
            recipe.setIngredient('g', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.eighth")));
            recipe.setIngredient('n', Material.matchMaterial(l.getConfig().getString("revive.beacon.ingredients.ninth")));

            Bukkit.addRecipe(recipe);
        }
    }

    public void init(JavaPlugin plugin) {
        Bukkit.getServer().removeRecipe(this.key(plugin));
        register(plugin);
    }

    public ItemStack getReviveBeacon() {
        return itemStack;
    }

    public static boolean isReviveBeacon(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().hasLore() && item.getItemMeta().getLore().contains(Message.colour("&7Place this and setup revive for player!"));
    }

    @SuppressWarnings("deprecated")
    public static boolean isReviveBeacon(Block block) {
        if (!(block.getType() == Material.PLAYER_HEAD)) return false;
        Skull sk = (Skull) block.getState();
        if (!(sk.getSkullType().equals(SkullType.PLAYER))) return false;
        UUID profile = sk.getOwningPlayer().getUniqueId();
        return profile.equals(UUID.fromString("ff1654b0-10f2-48b6-9c05-483b75f6549e"));
    }

}
