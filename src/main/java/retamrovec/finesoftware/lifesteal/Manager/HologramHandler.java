package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;

import java.util.Random;

public class HologramHandler {

    public static void newHologram(String @NotNull [] hologram, @NotNull Location location, LifeSteal lifeSteal) {
        for (String line : hologram) {
            ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location.subtract(0, 0.3, 0), EntityType.ARMOR_STAND);
            stand.setInvisible(true);
            stand.setGravity(false);
            stand.setInvulnerable(true);

            stand.setCustomNameVisible(true);
            stand.setCustomName(Message.colour(line));
            Random random = new Random();
            int randomID = random.nextInt(1, 1000);
            new Hologram("reviveBeacon", location);
            lifeSteal.getConfig().set("beacons." + randomID + ".world", location.getWorld());
            lifeSteal.getConfig().set("beacons." + randomID + ".x", location.getX());
            lifeSteal.getConfig().set("beacons." + randomID + ".y", location.getY());
            lifeSteal.getConfig().set("beacons." + randomID + ".z", location.getZ());
            lifeSteal.saveConfig();
        }
    }

    public static void removeHologram(@NotNull String id, LifeSteal lifeSteal) {
        if (lifeSteal.getConfig().getString("beacons." + id) != null) {
            World world = Bukkit.getWorld(lifeSteal.getConfig().getString("beacons." + id + ".world"));
            double x = lifeSteal.getConfig().getDouble("beacons." + id + ".x");
            double y = lifeSteal.getConfig().getDouble("beacons." + id + ".y");
            double z = lifeSteal.getConfig().getDouble("beacons." + id + ".z");
            Location location = new Location(world, x, y, z);
        }
    }
}
