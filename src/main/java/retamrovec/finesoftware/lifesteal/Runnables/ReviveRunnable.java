package retamrovec.finesoftware.lifesteal.Runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.Message;
import retamrovec.finesoftware.lifesteal.Manager.SettingsHandler;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

public class ReviveRunnable extends BukkitRunnable {

    private final Hologram hologram;
    private final World world;
    private final Location location;
    public int i = 0;
    public ReviveRunnable(@NotNull Location location, Hologram hologram) {
        this.world = location.getWorld();
        this.location = location;
        this.hologram = hologram;
    }

    public void start() {
        runTaskTimer(LifeSteal.getInstance(), 10, 30);
    }

    public void stop() {
        cancel();
    }

    @Override
    public void run() {
        i++;
        if (i > 30) {
            for (Entity entity : world.getNearbyEntities(location, 10, 10, 10)) {
                if (entity instanceof ArmorStand) {
                    ArmorStand armorStand = (ArmorStand) entity;
                    LifeSteal.getInstance().getHologramStorage().remove(hologram);
                    world.getBlockAt(hologram.getLocation()).setType(Material.AIR);
                    armorStand.remove();
                    hologram.cancelHologramRunnable();
                    Player owner = Bukkit.getPlayer(hologram.getIndentifier());
                    if (owner != null) {
                        owner.sendMessage(Message.colour(LifeSteal.getInstance().getConfig().getString("messages.beacon_expired")));
                    }
                }
            }
            cancel();
            return;
        }
        for (Entity entity : world.getNearbyEntities(location, 10, 10, 10)) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                if (LifeSteal.getInstance().getEliminatedPlayers().contains(player.getName())) {
                    SettingsHandler.runRevivingStatus(player.getName(), LifeSteal.getInstance());
                }
            }
        }
    }
}
