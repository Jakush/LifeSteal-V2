package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.DebugHandler;
import retamrovec.finesoftware.lifesteal.Manager.SettingsHandler;

public class PlayerDeathListener implements Listener {
	
	LifeSteal lifesteal;
	DebugHandler debug;
	public PlayerDeathListener (LifeSteal lifesteal, DebugHandler debug) {
		this.lifesteal = lifesteal;
		this.debug = debug;
	}
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		Player killer = e.getEntity().getKiller();
		// If player don't have permission lifesteal.ignore
		if (!player.hasPermission("lifesteal.ignore")) {
			// Check if player is in config.
			if (!lifesteal.getConfig().contains("player." + player.getName())) {
				debug.error("Player " + player.getName() + " is not configuration. Cancelling event, if issue is there even after rejoin, please report it on support.");
				return;
			}
			// If killer is player, give him some hearts
			if (killer != null) {
				// Check if killer is in config.
				if (!lifesteal.getConfig().contains("player." + killer.getName())) {
					debug.error("Player " + killer.getName() + " is not configuration. Cancelling event, if issue is there even after rejoin, please report it on support.");
					return;
				}
				if (killer.getMaxHealth() > 40) {
					// Changing and saving value on config
					lifesteal.getConfig().set("player." + killer.getName(), lifesteal.getConfig().getInt("player." + killer.getName()) + 2);
					lifesteal.saveConfig();
					// Changing value in-game
					killer.setMaxHealth(killer.getMaxHealth());
				}
				if (player.getMaxHealth() <= 2) {
					// Run all commands in config
					SettingsHandler.runEliminateCommands(lifesteal, player);
					return;
				}
				// Changing and saving value on config
				lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) -2);
				lifesteal.saveConfig();
				// Changing value in-game
				player.setMaxHealth(killer.getMaxHealth());
				return;
			}
			if (SettingsHandler.loseHeartsOnMobs(lifesteal)) {
				if (player.getMaxHealth() <= 2) {
					// Run all commands in config
					SettingsHandler.runEliminateCommands(lifesteal, player);
					return;
				}
				// Changing and saving value on config
				lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) -2);
				lifesteal.saveConfig();
				// Changing value in-game
				player.setMaxHealth(killer.getMaxHealth());
				return;
			}
		}
	}

}
