package retamrovec.finesoftware.lifesteal.Manager;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class PAPI extends PlaceholderExpansion {

    private final LifeSteal lifeSteal;
    public PAPI(LifeSteal lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    @Override
    public @NotNull String getAuthor() {
        return "RETAMROVEC";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "lifesteal";
    }

    @Override
    public @NotNull String getVersion() {
        return lifeSteal.version;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.equalsIgnoreCase("newAmount")) {
            double newAmount = LifeSteal.getInstance().getSendAmountStorage().get(player.getName());
            LifeSteal.getInstance().getSendAmountStorage().clear();
            return String.valueOf(newAmount);
        }

        return null;
    }

    public static void init() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getLogger().warning("Could not find PlaceholderAPI! Please install this plugin so you can use placeholders in config.yml. You probably get");
            return;
        }
        new PAPI(LifeSteal.getInstance());
    }

    public static @NotNull String usePlaceholder(Player player, String string) {
        String message = string;
        message = PlaceholderAPI.setPlaceholders(player, message);

        return message;
    }

    public static @NotNull String usePlaceholder(OfflinePlayer player, String string) {
        String message = string;
        message = PlaceholderAPI.setPlaceholders(player, message);

        return message;
    }
}
