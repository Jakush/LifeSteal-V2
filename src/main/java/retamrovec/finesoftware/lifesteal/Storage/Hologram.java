package retamrovec.finesoftware.lifesteal.Storage;

import org.bukkit.Location;

public class Hologram {

    private static String indentifier;
    private static Location location;
    public Hologram(String indentifier, Location location) {}

    public static String getIndentifier() {
        return indentifier;
    }

    public static void setIndentifier(String id) {
        Hologram.indentifier = id;
    }

    public static Location getLocation() {
        return location;
    }
}
