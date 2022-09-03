package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

public class HologramHandler {

    public static void newHologram(String @NotNull [] hologram, String name, @NotNull Location location, LifeSteal lifeSteal) {
        for (String line : hologram) {
            ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location.subtract(0, 0.3, 0), EntityType.ARMOR_STAND);
            stand.setInvisible(true);
            stand.setGravity(false);
            stand.setInvulnerable(false);

            stand.setCustomNameVisible(true);
            stand.setCustomName(Message.colour(line));
            new Hologram("reviveBeacon" + name, location);
            lifeSteal.getConfig().set("beacons." + name, location);
        }
    }

    public static void removeHologram(String name, Location location, LifeSteal lifeSteal) {
        if (lifeSteal.getConfig().getString("beacons." + name) != null) {
            new Hologram("reviveBeacon" + name, location);
            for (Entity entity : Hologram.getLocation().getWorld().getNearbyEntities(location, 2, 4, 2)) {
                if (entity instanceof ArmorStand) {
                    ArmorStand as = (ArmorStand) entity;
                    as.remove();
                }
            }
        }
    }
}
