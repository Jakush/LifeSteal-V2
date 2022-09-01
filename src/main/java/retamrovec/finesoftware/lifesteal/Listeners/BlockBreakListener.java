package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.Manager.HologramHandler;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (Beacon.isReviveBeacon(block)) {
            HologramHandler.removeHologram(e.getPlayer().getName());
        }
    }
}
