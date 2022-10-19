package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;

public class EntityDamageByEntityListener implements Listener {

    private final LifeSteal lifeSteal;
    private final DebugHandler debug;
    public EntityDamageByEntityListener(LifeSteal lifeSteal, DebugHandler debug) {
        this.lifeSteal = lifeSteal;
        this.debug = debug;
    }

    @EventHandler
    public void onArmorStandBreak(EntityDamageByEntityEvent e) {
        debug.init("Entity damaging was registered.");
        debug.init("EntityType is " + e.getEntityType());
        debug.init("Entity is " + e.getEntity());
        if (e.getEntityType() == EntityType.ARMOR_STAND) {
            ArmorStand armorStand = (ArmorStand) e.getEntity();
            String[] hologram = new String[]{
                    lifeSteal.getConfig().getString("hologram.1"),
                    lifeSteal.getConfig().getString("hologram.2"),
                    lifeSteal.getConfig().getString("hologram.3")
            };
            for (String line : hologram) {
                if (armorStand.getCustomName().equals(line)) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

}
