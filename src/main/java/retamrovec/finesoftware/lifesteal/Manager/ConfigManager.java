package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class ConfigManager {

    private static LifeSteal lifeSteal;
    private static DebugHandler debug;
    public ConfigManager(LifeSteal lifeSteal, DebugHandler debug) {
        this.lifeSteal = lifeSteal;
        this.debug = debug;
    }

    public static @Nullable String getString(String path) {
        if (lifeSteal.getConfig().getString(path) != null) {
            return lifeSteal.getConfig().getString(path);
        }
        debug.error("ERROR! THIS IS NOT SPIGOT-SIDE PROBLEM.");
        debug.error("There is string (" + path + ") throwing null.");
        debug.error("Its highly recommended to fix this problem.");
        debug.error("For future versions, this can be big problem,");
        debug.error("because this can be already not supported.");
        debug.error("If issue is there still even after fixing issue,");
        debug.error("report it to our discord server (link at spigotmc page).");
        return null;
    }

    public static boolean getBoolean(String path) {
        debug.error("ERROR! THIS IS NOT SPIGOT-SIDE PROBLEM.");
        debug.error("There is boolean (" + path + ") throwing null.");
        debug.error("Its highly recommended to fix this problem.");
        debug.error("For future versions, this can be big problem,");
        debug.error("because this can be already not supported.");
        debug.error("If issue is there still even after fixing issue,");
        debug.error("report it to our discord server (link at spigotmc page).");
        return lifeSteal.getConfig().getBoolean(path);
    }

    public static @Nullable double getDouble(String path) {
        if (lifeSteal.getConfig().getDouble(path) != 0.0) {
            return lifeSteal.getConfig().getDouble(path);
        }
        debug.error("ERROR! THIS IS NOT SPIGOT-SIDE PROBLEM.");
        debug.error("There is double (" + path + ") throwing null.");
        debug.error("Its highly recommended to fix this problem.");
        debug.error("For future versions, this can be big problem,");
        debug.error("because this can be already not supported.");
        debug.error("If issue is there still even after fixing issue,");
        debug.error("report it to our discord server (link at spigotmc page).");
        return 0.0;
    }

    public static @Nullable int getInt(String path) {
        if (lifeSteal.getConfig().getInt(path) != 0) {
            return lifeSteal.getConfig().getInt(path);
        }
        debug.error("ERROR! THIS IS NOT SPIGOT-SIDE PROBLEM.");
        debug.error("There is int (" + path + ") throwing null.");
        debug.error("Its highly recommended to fix this problem.");
        debug.error("For future versions, this can be big problem,");
        debug.error("because this can be already not supported.");
        debug.error("If issue is there still even after fixing issue,");
        debug.error("report it to our discord server (link at spigotmc page).");
        return 0;
    }

    public static @Nullable Location getLocation(String path) {
        if (lifeSteal.getConfig().getLocation(path) != null) {
            return lifeSteal.getConfig().getLocation(path);
        }
        debug.error("ERROR! THIS IS NOT SPIGOT-SIDE PROBLEM.");
        debug.error("There is int (" + path + ") throwing null.");
        debug.error("Its highly recommended to fix this problem.");
        debug.error("For future versions, this can be big problem,");
        debug.error("because this can be already not supported.");
        debug.error("If issue is there still even after fixing issue,");
        debug.error("report it to our discord server (link at spigotmc page).");
        return null;
    }

    public static void set(String path, Object value) {
        lifeSteal.getConfig().set(path, value);
        lifeSteal.saveConfig();
    }
}
