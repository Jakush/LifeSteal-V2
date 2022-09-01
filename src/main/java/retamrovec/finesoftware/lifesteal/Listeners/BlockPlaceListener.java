package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.HologramHandler;

public class BlockPlaceListener implements Listener {

    private final LifeSteal lifeSteal;
    public BlockPlaceListener(LifeSteal lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Block block = e.getBlockPlaced();
        if (Beacon.isReviveBeacon(block)) {
            String[] hologram = new String[]{
                    lifeSteal.getConfig().getString("hologram.1"),
                    lifeSteal.getConfig().getString("hologram.2"),
                    lifeSteal.getConfig().getString("hologram.3")
            };
            HologramHandler.newHologram(hologram, e.getPlayer().getName(), block.getLocation());
        }
    }

}
