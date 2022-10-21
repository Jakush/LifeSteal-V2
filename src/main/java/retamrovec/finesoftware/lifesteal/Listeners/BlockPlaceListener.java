package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;

import java.util.List;
import java.util.Objects;

public class BlockPlaceListener implements Listener {

    private final DebugHandler debug;
    public BlockPlaceListener(DebugHandler debug) {
        this.debug = debug;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        debug.init("Checking block place event.");
        debug.init("Block is " + e.getBlock());
        if (Beacon.isReviveBeacon(block, debug)) {
            List<Hologram> hs = LifeSteal.getInstance().getHologramStorage();
            for (Hologram hologram : hs) {
                if (hologram.getLocation() == e.getBlock().getLocation()) {
                    e.setCancelled(true);
                    return;
                }
                if (Objects.equals(hologram.getIndentifier(), e.getPlayer().getName())) {
                    e.setCancelled(true);
                    return;
                }
            }
            Hologram h = new Hologram(e.getPlayer().getName(), e.getBlock().getLocation(), e.getBlock());
            h.setBoolean(true);
            h.getHologramRunnable().start();
            h.getReviveRunnable().start();
            LifeSteal.getInstance().getHologramStorage().add(h);
        }
    }

}
