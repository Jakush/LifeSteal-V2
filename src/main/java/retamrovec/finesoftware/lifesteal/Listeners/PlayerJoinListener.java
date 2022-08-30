package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.PAPI;
import retamrovec.finesoftware.lifesteal.Storage.Eliminate;

public class PlayerJoinListener implements Listener {
	
	LifeSteal lifesteal;
	public PlayerJoinListener (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
						
		if (!player.hasPlayedBefore()) {
			if (!lifesteal.getConfig().contains("player." + player.getName())) {
				lifesteal.getConfig().set("player." + player.getName(), 20);
				lifesteal.saveConfig();
			}
		}
		if (player.hasPlayedBefore()) {
			if (lifesteal.getConfig().contains("player." + player.getName())) {
				if (lifesteal.getConfig().getInt("player." + player.getName()) == 2 || lifesteal.getConfig().getInt("player." + player.getName()) == 1) {
					if (player.isBanned() == false) {
						Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.zero_health_ban"))), null, null);
						player.kickPlayer(ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.zero_health_ban"))));
						new Eliminate(player.getName());
						Eliminate.setStatus(true);
					}
				} 
				else {
					double playerMaxHealth = lifesteal.getConfig().getDouble("player." + player.getName());
					
					player.setMaxHealth(playerMaxHealth);
					new Eliminate(player.getName());
					if (Eliminate.getStatus()) {
						OfflinePlayer target = e.getPlayer();
						Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());
						new Eliminate(player.getName());
						Eliminate.setStatus(false);
					}
				}
			}
			if (!lifesteal.getConfig().contains("player." + player.getName())) {
				lifesteal.getConfig().set("player." + player.getName(), 20);
				lifesteal.saveConfig();
			}
		}
		
		player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));
		
	}


}
