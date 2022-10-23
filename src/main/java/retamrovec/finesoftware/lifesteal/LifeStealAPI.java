package retamrovec.finesoftware.lifesteal;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LifeStealAPI {

    private List<String> eliminatedPlayers = LifeSteal.getInstance().getEliminatedPlayers();
    private List<Hologram> hologramStorage = LifeSteal.getInstance().getHologramStorage();
    private List<Player> edit = LifeSteal.getInstance().getEdit();
    private HashMap<String, Double> sendAmountStorage = LifeSteal.getInstance().getSendAmountStorage();

    public List<Material> getRecipe(@NotNull String itemStack) {
        if (itemStack == null) {
            throw new NullPointerException("Itemstack is null");
        }
        if (itemStack.equalsIgnoreCase("heart")) {
            List<Material> recipe = new ArrayList<>();
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.first")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.second")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.third")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.fourth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.fifth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.sixth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.seventh")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.eighth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.ingredients.ninth")));
            return recipe;
        }
        if (itemStack.equalsIgnoreCase("beacon")) {
            List<Material> recipe = new ArrayList<>();
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.first")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.second")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.third")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.fourth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.fifth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.sixth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.seventh")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.eighth")));
            recipe.add(Material.matchMaterial(LifeSteal.getInstance().getConfig().getString("recipe.revive.beacon.ingredients.ninth")));
            return recipe;
        }
        throw new IllegalArgumentException("ItemStack with name " + itemStack + " was not found! Available itemstacks are 'beacon' or 'heart'");
    }
    public List<String> getEliminatedPlayers() {
        return eliminatedPlayers;
    }
    public List<Hologram> getHologramStorage() {
        return hologramStorage;
    }
    public List<Player> getEditors() {
        return edit;
    }
    public HashMap<String, Double> getSendAmountStorage() {
        return sendAmountStorage;
    }
    public String getPluginVersion() {
        return LifeSteal.getInstance().version;
    }
    public String getPluginAuthor() {
        String author = "";
        for (String str : LifeSteal.getInstance().pdf.getAuthors()) {
            author = str;
        }
        return author;
    }
    public String getPluginName() {
        return LifeSteal.getInstance().pdf.getName();
    }
    public String getAPIVersion() {
        return LifeSteal.getInstance().pdf.getAPIVersion();
    }
}
