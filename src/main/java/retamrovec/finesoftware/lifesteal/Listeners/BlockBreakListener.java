package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Manager.HologramHandler;
import retamrovec.finesoftware.lifesteal.Manager.SettingsHandler;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

public class BlockBreakListener implements Listener {

    private final LifeSteal lifeSteal;
    private final DebugHandler debug;
    public BlockBreakListener(LifeSteal lifeSteal, DebugHandler debug) {
        this.lifeSteal = lifeSteal;
        this.debug = debug;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (Beacon.isReviveBeacon(block, debug)) {
            for (Hologram h : lifeSteal.getHologramStorage()) {
                if (h.getIndentifier().equals(e.getPlayer().getName())) {
                    h.setBoolean(false);
                    HologramHandler.removeHologram(e.getPlayer().getName(), block.getLocation(), lifeSteal);
                    Bukkit.getServer().getScheduler().cancelTask(SettingsHandler.getTaskID());
                }
            }
        }
    }


}
