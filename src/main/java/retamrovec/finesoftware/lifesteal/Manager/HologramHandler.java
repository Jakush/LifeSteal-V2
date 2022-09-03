package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

public class HologramHandler {

    private static ArmorStand stand;

    public static void newHologram(String @NotNull [] hologram, String name, @NotNull Location location, LifeSteal lifeSteal, DebugHandler debug) {
        for (String line : hologram) {
            stand = (ArmorStand) location.getWorld().spawnEntity(location.subtract(0, 0.3, 0), EntityType.ARMOR_STAND);
            stand.setInvisible(true);
            stand.setGravity(false);
            stand.setInvulnerable(false);

            stand.setCustomNameVisible(true);
            stand.setCustomName(Message.colour(line));
            new Hologram("reviveBeacon" + name, location);
            ConfigManager.set("beacons." + name, location,  lifeSteal);
        }
    }

    public static void removeHologram(String name, Location location, LifeSteal lifeSteal) {
        if (lifeSteal.getConfig().getString("beacons." + name) != null) {
            new Hologram("reviveBeacon" + name, location);
            if (Hologram.getLocation().equals(location)) {
                stand.remove();
            }
        }
    }
}
