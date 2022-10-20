package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Manager.HologramHandler;
import retamrovec.finesoftware.lifesteal.Manager.SettingsHandler;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

import java.util.List;
import java.util.Random;

public class BlockBreakListener implements Listener {

    private final LifeSteal lifeSteal;
    private final DebugHandler debug;
    private Hologram h;
    public BlockBreakListener(LifeSteal lifeSteal, DebugHandler debug) {
        this.lifeSteal = lifeSteal;
        this.debug = debug;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (Beacon.isReviveBeacon(block, debug)) {
            List<Hologram> hs = LifeSteal.getInstance().getHologramStorage();
            for (Hologram hologram : hs) {
                if (hologram.getIndentifier().equals(e.getPlayer().getName())) {
                    h = hologram;
                    break;
                }
            }
            Bukkit.getLogger().info("1");
            World world = e.getBlock().getWorld();
            for (Entity entity : world.getNearbyEntities(e.getBlock().getLocation(), 3, 3, 3)) {
                if (entity instanceof ArmorStand) {
                    ArmorStand armorStand = (ArmorStand) entity;
                    armorStand.remove();
                }
            }
            h.setBoolean(false);
            h.cancelRunnables();
            LifeSteal.getInstance().getHologramStorage().remove(h);
            HologramHandler.removeHologram(e.getPlayer().getName(), block.getLocation(), lifeSteal);
            Bukkit.getServer().getScheduler().cancelTask(SettingsHandler.getTaskID());
        }
    }


}
