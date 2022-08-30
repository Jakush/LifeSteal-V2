package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Manager.PAPI;
import retamrovec.finesoftware.lifesteal.Storage.Eliminate;

public class PlayerDeathListener implements Listener {
	
	LifeSteal lifesteal;
	public PlayerDeathListener (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDeath(PlayerDeathEvent e){ 
			Player player = e.getEntity();
			Player killer = player.getKiller();

			if (!player.hasPermission("lifesteal.ignore")) {
				
				if (!(killer instanceof Player)) {
					
					if (lifesteal.getConfig().getBoolean("config.lose_hearts_on_mobs")) {
							
							if (lifesteal.getConfig().contains("player." + player.getName())) {
								
								if (!(lifesteal.getConfig().getInt("player." + player.getName()) == 2)) {
									
									lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) - 2);
									
									lifesteal.saveConfig();
									
									player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));
									
								}
								
								else if (lifesteal.getConfig().getInt("player." + player.getName()) < 3) {
									
									Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.zero_health_ban"))), null, null);
									player.kickPlayer(ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.zero_health_ban"))));
									new Eliminate(player.getName());
									Eliminate.setStatus(true);
								}
							} else {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.player_isnt_registered"))));
						}
					}					
				}
				else if (killer instanceof Player) {
					if (lifesteal.getConfig().contains("player." + killer.getName())) {
													
						if (lifesteal.getConfig().getInt("player." + killer.getName()) < 40) {

							int playerHealth = lifesteal.getConfig().getInt("player." + killer.getName());
							
							lifesteal.getConfig().set("player." + killer.getName(), playerHealth + 2);

							lifesteal.saveConfig();

							int playerHealth2 = lifesteal.getConfig().getInt("player." + killer.getName());

							killer.setMaxHealth(playerHealth2);
							
						}
						else {
							if (!(lifesteal.getConfig().getInt("player." + player.getName()) == 2)) {

								lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) - 2);

								lifesteal.saveConfig();

								player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));

							}
						}

					}
					else {
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', PAPI.usePlaceholder(player, lifesteal.getConfig().getString("error.player_isnt_registered"))));
					}
				}

			}
		
	}

}
