package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;
import retamrovec.finesoftware.lifesteal.Events.PlayerEatHeartEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;

public class PlayerItemConsumeListener implements Listener {

    private final Heart heart;
    private final DebugHandler debug;
    private final LifeSteal lifeSteal;
    public PlayerItemConsumeListener(Heart heart, LifeSteal lifeSteal, DebugHandler debug) {
        this.heart = heart;
        this.lifeSteal = lifeSteal;
        this.debug = debug;
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        debug.init("Player " + e.getPlayer().getName() + " is eating. Checking if item is enchanted_golden_apple.");
        if (heart.isHeart(e.getItem())) {
            debug.init("Statement is " + heart.isHeart(e.getItem()));
            debug.init("Checking if player is in config.yml.");
            if (!lifeSteal.getConfig().contains("player." + e.getPlayer().getName())) {
                debug.error("Player " + e.getPlayer().getName() + " is not in config.yml.");
                debug.error("Cancelling event. To fix this problem, make sure player will leave and join the server.");
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', lifeSteal.getConfig().getString("error.unknown-error")));
                e.setCancelled(true);
                return;
            }
            debug.init("Checking if player is not already at limit.");
            if (e.getPlayer().getMaxHealth() >= 40) {
                debug.warning("Player " + e.getPlayer().getName() + " can't get more hearts because he is at limit!");
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', lifeSteal.getConfig().getString("too_big_amount_of_hearts")));
                e.setCancelled(true);
                return;
            }
            PlayerEatHeartEvent playerEatHeartEvent = new PlayerEatHeartEvent(e.getPlayer());
            if (playerEatHeartEvent.isCancelled()) {
                debug.init("Cancelling PlayerEatHeartEvent because plugin requested it!");
                return;
            }
            Bukkit.getPluginManager().callEvent(playerEatHeartEvent);
            debug.init("Removing REGENERATION potion effect.");
            e.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
            debug.init("Removed.");
            debug.init("Removing DAMAGE_RESISTANCE potion effect.");
            e.getPlayer().removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            debug.init("Removed.");
            debug.init("Removing FIRE_RESISTANCE potion effect.");
            e.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            debug.init("Removed.");
            debug.init("Setting up new values in config.yml");
            lifeSteal.getConfig().set("player." + e.getPlayer().getName(), lifeSteal.getConfig().getInt("player." + e.getPlayer().getName()) + 2);
            debug.init("New values " + "player." + e.getPlayer().getName() + ":" + lifeSteal.getConfig().getInt("player." + e.getPlayer().getName()));
            lifeSteal.saveConfig();
            debug.init("Saving values in config.yml.");
            e.getPlayer().setMaxHealth(lifeSteal.getConfig().getInt("player." + e.getPlayer().getName()));
            debug.init("Setting new values in-game.");
        }
    }

}
