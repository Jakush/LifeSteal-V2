package retamrovec.finesoftware.lifesteal.Manager;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
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

    public static void init() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            Bukkit.getLogger().warning("Could not find PlaceholderAPI! Please install this plugin so you can use placeholders in config.yml. You probably get");
        }
    }

    public static String usePlaceholder(Player player, String string) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            String message = string;
            message = PlaceholderAPI.setPlaceholders(player, message);

            return message;
        }
        return null;
    }
}
