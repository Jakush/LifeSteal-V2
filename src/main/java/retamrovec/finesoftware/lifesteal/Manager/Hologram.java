package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Location;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class Hologram {

    static String indentifier;
    static Location location;
    public Hologram(String indentifier, Location location) {}

    public static String getIndentifier() {
        return indentifier;
    }

    public static void setIndentifier(String id, LifeSteal lifeSteal) {
        indentifier = id;
    }

    public static Location getLocation() {
        return location;
    }
}
