package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class EntityDamageByEntityListener implements Listener {

    private final LifeSteal lifeSteal;
    public EntityDamageByEntityListener(LifeSteal lifeSteal) {
        this.lifeSteal = lifeSteal;
    }

    @EventHandler
    public void onArmorStandBreak(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof ArmorStand) {
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
