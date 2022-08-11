package retamrovec.finesoftware.lifesteal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class HealthManager implements CommandExecutor {
	
	LifeSteal lifesteal;
	CustomCraftingGUI ccg;
	CustomCraftingManager ccm;
	public HealthManager (LifeSteal lifesteal, CustomCraftingGUI ccg, CustomCraftingManager ccm) {
		this.lifesteal = lifesteal;
		this.ccg = ccg;
		this.ccm = ccm;
	}

	@SuppressWarnings({ "deprecation", "unlikely-arg-type" })
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7HELP"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You can use /ls, /lifesteal or /lfs to use plugin commands."));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal reload &a(Reload config.yml)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal author &a(Shows who is author)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal spigotmc &a(Sends where plugin can be downloaded)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal send &a(You can send some of your hearts to other player)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal recipe&c/&7showrecipe &a(Show recipe ingame)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal help &a(Send all available commands)"));
			return true;
		}

		if (!(sender instanceof Player)) {
			lifesteal.getLogger().severe("You are not player!");
			return false;
		}

		if (sender.hasPermission("lifesteal.admin")) {
			// If args length one, not enough arguments
			if (args.length == 1) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid use. &c/lifesteal help &7for more."));
				return false;
			}
			// If arg[1] is "set"
			if (args[0].equals("set")) {
				final Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be finded."));
					return false;
				}
				// If args.length is bigger or smaller than 2
				if (!(args.length == 2)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.amount_not_exist")));
					return false;
				}
				// If args[2] are not under 40 hearts (arg[2] are bigger)
				if (!((Integer.parseInt(args[2])) < 41)) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_big_amount_of_hearts")));
					return false;
				}
				// Get amount of hearts in command
				Double amount = Double.valueOf(args[2]);
				// Set amount of hearts
				target.setMaxHealth(amount);
				// Type it in config new amount and save config (saveConfig is very important)!
				lifesteal.getConfig().set("player." + target.getName(), amount);
				lifesteal.saveConfig();
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.changed_amount_of_health")));
				return true;
			}
			if (args[0].equalsIgnoreCase("reload")) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					Double amount = Double.valueOf(lifesteal.getConfig().getInt("player." + player.getName()));
					if (amount > 40) {
						lifesteal.getConfig().set("player." + player.getName(), 20);
						lifesteal.saveConfig();
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_much_hearts")));
					}
					else {
						player.setMaxHealth(amount);
					}
				}
				Bukkit.getServer().removeRecipe(ccm.getRecipe(lifesteal));
				ccm.registerRecipe(lifesteal);
				lifesteal.reloadConfig();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.config_reloaded")));
				return true;
			}
			else if (args[0].equalsIgnoreCase("author")) {
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Author of this plugin is &aDiskotekaSTARM&7. Ingame nick is &aRETAMROVEC&7."));
				
			}
			else if (args[0].equalsIgnoreCase("spigotmc")) {
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7This plugin is on &aSPIGOTMC&7 and link is: &9https://www.spigotmc.org/resources/lifesteal.102599/&7."));
				
			}
			else if (args[0].equalsIgnoreCase("send")) {
		        final Player target = Bukkit.getPlayer(args[1]);
		        String name = sender.getName();
				if (target != null && !target.equals(name)) {
		        	if (args[2] != null) {
		        		
		        		Player senderplayer = (Player) sender;
		        		
		        		if (Integer.valueOf(args[2]) < senderplayer.getMaxHealth()) {
		        			
			        		Double amount = Double.valueOf(args[2]);
			        		
			        		Double senderplayerhealth = Double.valueOf(lifesteal.getConfig().getInt("player." + name));
			        		Double targetplayerhealth = Double.valueOf(lifesteal.getConfig().getInt("player." + target.getName()));
			        					        		
			        		target.setMaxHealth(targetplayerhealth + amount);
			        					        		
			        		senderplayer.setMaxHealth(senderplayerhealth - amount);
			        		
			        		lifesteal.getConfig().set("player." + target.getName(), target.getMaxHealth() + amount);
			        		
			        		lifesteal.saveConfig();
			        		
			        		lifesteal.getConfig().set("player." + name, senderplayer.getMaxHealth() - amount);
			        		
			        		lifesteal.saveConfig();
			        		
			        		String formatfirst = lifesteal.getConfig().getString("messages.hearts_sent");
			        		
			        		String hearts_sent = formatfirst
			        	
			        		.replace("{target}", target.getName())
			        		
			        		.replace("{amount}", args[2]);
			        				
			        		target.sendMessage(ChatColor.translateAlternateColorCodes('&', hearts_sent));		
			        		
			        		String formatsecond = lifesteal.getConfig().getString("messages.hearts_get");
			        		
			        		String hearts_get = formatsecond
			        				        		
			        		.replace("{sender}", name)
			        		
			        		.replace("{amount}", args[2]);
			        		
			        		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hearts_get));		        			
		        			
		        		} else {
		        			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_big_amount_of_hearts")));
		        		}
		        		
		        	} else {
		        		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.amount_not_exist")));
		        	}
		        } else {
		        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be finded."));
		        } 	        
		        
			}
			else if (args[0].equalsIgnoreCase("help")) {
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7HELP"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You can use /ls, /lifesteal or /lfs to use plugin commands."));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal reload &a(Reload config.yml)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal author &a(Shows who is author)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal spigotmc &a(Sends where plugin can be downloaded)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal send &a(You can send some of your hearts to other player)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal recipe&c/&7showrecipe &a(Show recipe ingame)"));
				
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifesteal help &a(Send all available commands)"));
								
			}
		} if (sender.hasPermission("lifesteal.send"))  {
			if (args[0].equalsIgnoreCase("send")) {
		        final Player target = Bukkit.getPlayer(args[1]);
		        String name = sender.getName();
				if (target != null && !target.equals(name)) {
		        	if (args[2] != null) {
		        		
		        		Player senderplayer = (Player) sender;
		        		
		        		if (Integer.valueOf(args[2]) < senderplayer.getMaxHealth()) {
		        			
			        		Double amount = Double.valueOf(args[2]);
			        		
			        		Double senderplayerhealth = Double.valueOf(lifesteal.getConfig().getInt("player." + name));
			        		Double targetplayerhealth = Double.valueOf(lifesteal.getConfig().getInt("player." + target.getName()));
			        					        		
			        		target.setMaxHealth(targetplayerhealth + amount);
			        					        		
			        		senderplayer.setMaxHealth(senderplayerhealth - amount);
			        		
			        		lifesteal.getConfig().set("player." + target.getName(), target.getMaxHealth() + amount);
			        		
			        		lifesteal.saveConfig();
			        		
			        		lifesteal.getConfig().set("player." + name, senderplayer.getMaxHealth() - amount);
			        		
			        		lifesteal.saveConfig();
			        		
			        		String formatfirst = lifesteal.getConfig().getString("messages.hearts_sent");
			        		
			        		String hearts_sent = formatfirst
			        	
			        		.replace("{target}", target.getName())
			        		
			        		.replace("{amount}", args[2]);
			        				
			        		target.sendMessage(ChatColor.translateAlternateColorCodes('&', hearts_sent));		
			        		
			        		String formatsecond = lifesteal.getConfig().getString("messages.hearts_get");
			        		
			        		String hearts_get = formatsecond
			        				        		
			        		.replace("{sender}", name)
			        		
			        		.replace("{amount}", args[2]);
			        		
			        		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hearts_get));		        			
		        			
		        		} else {
		        			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_big_amount_of_hearts")));
		        		}
		        		
		        	} else {
		        		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.amount_not_exist")));
		        	}
		        } else {
		        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be finded."));
		        } 	        
		        
			  }		
			} if (sender.hasPermission("lifesteal.showrecipe")) {
				
				if (args[0].equalsIgnoreCase("showrecipe") || args[0].equalsIgnoreCase("recipe")) {       
					
					if (sender instanceof Player) {
						
						Player player = (Player) sender;
												
						ccg.CreateInventory();
						
						ccg.OpenInventory(player);
						
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.recipe_showed")));
					} 
					else {
						
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.cannot_use_command")));
						
					}
			    }	
			}
		else {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
		}
	return false;
	}
}
