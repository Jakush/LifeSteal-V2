package retamrovec.finesoftware.lifesteal;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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
								
								else if (lifesteal.getConfig().getInt("player." + player.getName()) < 4) {
									
									Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.zero_health_ban")), null, null);
									player.kickPlayer(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.zero_health_ban")));
								}
							} else {
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.player_isnt_registered")));
								killer.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.player_isnt_registered")));
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
						if (lifesteal.getConfig().getInt("player." + player.getName()) < 4) {

							lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) - 2);

							lifesteal.saveConfig();

							Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.zero_health_ban")), null, null);
							player.kickPlayer(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.zero_health_ban")));
						} else {
							if (!(lifesteal.getConfig().getInt("player." + player.getName()) == 2)) {

								lifesteal.getConfig().set("player." + player.getName(), lifesteal.getConfig().getInt("player." + player.getName()) - 2);

								lifesteal.saveConfig();

								player.setMaxHealth(lifesteal.getConfig().getInt("player." + player.getName()));

							}
						}

					}
					else {
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.player_isnt_registered")));
					}
				}

			}
		
	}

}
