package retamrovec.finesoftware.lifesteal.Runnables;

import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.HologramHandler;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

public class HologramRunnable extends BukkitRunnable {

    private final LifeSteal lifeSteal = LifeSteal.getInstance();
    private final ReviveRunnable runnable;
    private final Hologram hologram;
    private final String n1 = lifeSteal.getConfig().getString("hologram.1");
    private final String n2 = lifeSteal.getConfig().getString("hologram.2");
    private final String n3 = lifeSteal.getConfig().getString("hologram.3");
    private final String name;
    private final Block block;
    public HologramRunnable(String name, Block block, ReviveRunnable runnable, Hologram hologram) {
        this.block = block;
        this.name = name;
        this.runnable = runnable;
        this.hologram = hologram;
    }

    public void start() {
        runTaskTimer(LifeSteal.getInstance(), 0, 20);
    }

    public void stop() { cancel(); }

    @Override
    public void run() {
        HologramHandler.removeHologram(name, block.getLocation(), lifeSteal);
        for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 3, 3, 3)) {
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand = (ArmorStand) entity;
                armorStand.remove();
            }
        }
        String format1 = n1
                .replace("%lifesteal_revive_count%", "3 seconds")
                .replace("%lifesteal_beacon_timer%", String.valueOf(runnable.i));
        String format2 = n2
                .replace("%lifesteal_revive_count%", "3 seconds")
                .replace("%lifesteal_beacon_timer%", String.valueOf(runnable.i));
        String format3 = n3
                .replace("%lifesteal_revive_count%", "3 seconds")
                .replace("%lifesteal_beacon_timer%", String.valueOf(runnable.i));
        String[] hologram = new String[]{
                format1,
                format2,
                format3
        };
        HologramHandler.newHologram(hologram, name, block.getLocation());
    }
}
