package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Bukkit;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class DebugHandler {

    private final LifeSteal plugin;
    public DebugHandler(LifeSteal plugin) {
        this.plugin = plugin;
    }

    public void init(String message){
        if (plugin.getConfig().getBoolean("developer.enable")) {
            Bukkit.getLogger().info("[DEBUG] " + message);
        }
    }

    public void error(String error){
        if (plugin.getConfig().getBoolean("developer.enable")) {
            Bukkit.getLogger().severe("[DEBUG] " + error);
        }
    }

    public void warning(String warning){
        if (plugin.getConfig().getBoolean("developer.enable")) {
            Bukkit.getLogger().warning("[DEBUG] " + warning);
        }
    }

}
