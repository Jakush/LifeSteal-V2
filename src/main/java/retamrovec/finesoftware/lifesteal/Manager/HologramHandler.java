package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class HologramHandler {

    private static ArmorStand stand;

    public static void newHologram(String @NotNull [] hologram, String name, @NotNull Location location) {
        for (String line : hologram) {
            stand = (ArmorStand) location.getWorld().spawnEntity(location.subtract(0, 0.3, 0), EntityType.ARMOR_STAND);
            stand.setInvisible(true);
            stand.setGravity(false);
            stand.setInvulnerable(true);

            stand.setCustomNameVisible(true);
            stand.setCustomName(Message.colour(line));
            new Hologram("reviveBeacon" + name, location);
            ConfigManager.set("beacons." + name, location);
        }
    }

    public static void removeHologram(String name) {
        if (ConfigManager.getString("beacons." + name) != null) {
            Location location = ConfigManager.getLocation("beacons." + name);
            new Hologram("reviveBeacon" + name, location);
            if (Hologram.getLocation().equals(location)) {
                stand.remove();
            }
        }
    }
}
