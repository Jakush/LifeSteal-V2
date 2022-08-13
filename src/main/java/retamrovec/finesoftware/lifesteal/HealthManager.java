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
	Message message;
	CustomCraftingGUI ccg;
	CustomCraftingManager ccm;
	public HealthManager (LifeSteal lifesteal, CustomCraftingGUI ccg, CustomCraftingManager ccm, Message message) {
		this.lifesteal = lifesteal;
		this.ccg = ccg;
		this.ccm = ccm;
		this.message = message;
	}

	@Override
	@Deprecated
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		// If sender is not player
		if (!(sender instanceof Player)) {
			lifesteal.getLogger().severe("You are not a player!");
			return false;
		}

		// Command /lifesteal
		if (args.length == 0) {
			/* @Deprecated
			 (replacing [ and ] with air, and making \n with new line, doesn't work, and in development status!
			 message.colorCodes(sender, message.replace("[", "]", "\n", "", "", " ", "messages.help", lifesteal));
			 */
			message.colorCodes(sender, "&cLS &a>> &7HELP");
			message.colorCodes(sender, "&7You can use /ls, /lifesteal or /lfs to use plugin commands.");
			message.colorCodes(sender, "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)");
			message.colorCodes(sender, "&7/lifesteal reload &a(Reload config.yml)");
			message.colorCodes(sender, "&7/lifesteal author &a(Shows who is author)");
			message.colorCodes(sender, "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)");
			message.colorCodes(sender, "&7/lifeSteal send &a(You can send some of your hearts to other player)");
			message.colorCodes(sender, "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)");
			message.colorCodes(sender, "&7/lifeSteal help &a(Send all available commands)");
			return true;
		}

		// Command /lifesteal help
		if (args[0].equalsIgnoreCase("help")) {
			// If sender has permission lifesteal.admin
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(lifesteal.getConfig().getStringList("messages.help"))));
			return true;
		}
		// Command /lifesteal set
		if (args[0].equalsIgnoreCase("set")) {
			// If sender has permission lifesteal.admin
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			// If args are bigger or smaller than 2
			if (!(args.length == 2)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.amount_not_exist")));
				return false;
			}
			// target
			final Player target = Bukkit.getPlayer(args[1]);
			// Checks if target is null
			if (target == null) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be found."));
				return false;
			}
			// If args[2] are not under 40 hearts (arg[2] are bigger)
			if (!((Integer.parseInt(args[2])) < 41)) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_big_amount_of_hearts")));
				return false;
			}
			// Get amount of hearts in command
			double amount = Double.parseDouble(args[2]);
			// Set amount of hearts
			target.setMaxHealth(amount);
			// Type it in config new amount and save config (saveConfig is very important)!
			lifesteal.getConfig().set("player." + target.getName(), amount);
			lifesteal.saveConfig();
			// Message
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.changed_amount_of_health")));
			return true;
		}
		// Command /lifesteal reload
		if (args[0].equalsIgnoreCase("reload")) {
			// If sender has permission lifesteal.admin
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				// Get amount of hearts in config
				double amount;
				amount = lifesteal.getConfig().getInt("player." + player.getName());
				// If hearts are bigger than 40
				if (amount > 40) {
					// Set amount of hearts to 20
					player.setMaxHealth(20);
					// Reset amount of hearts in config (saveConfig is very important)!
					lifesteal.getConfig().set("player." + player.getName(), 20);
					lifesteal.saveConfig();
					message.colorCodes(player, lifesteal.getConfig().getString("error.too_much_hearts"));
				} else {
					// Set amount of hearts to new amount of hearts in config
					player.setMaxHealth(amount);
				}
			}
			// Remove recipe (important, removing this cause bugs)
			Bukkit.getServer().removeRecipe(ccm.getRecipe(lifesteal));
			// Register recipe, "lifeSteal" is main class because this method needs main class
			ccm.registerRecipe(lifesteal);
			// Reloads configuration (that's what should /lifeSteal reload do)
			lifesteal.reloadConfig();
			// Message
			message.colorCodes(sender, lifesteal.getConfig().getString("messages.config_reloaded"));
			return true;
		}
		// Command /lifesteal author
		if (args[0].equalsIgnoreCase("author")) {
			// If sender has permission lifesteal.admin
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				message.colorCodes(sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			// Message
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Author of this plugin is &aDiskotekaSTARM&7. InGame nick is &aRETAMROVEC&7."));
			return true;
		}
		// Command /lifesteal spigotmc
		if (args[0].equalsIgnoreCase("spigotMC")) {
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				message.colorCodes(sender, lifesteal.getConfig().getString("error.without_perm"));
            	return false;
			}
			// Message
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7This plugin is on &aSpigotMC&7 and link is: &9https://www.spigotmc.org/resources/lifesteal.102599/&7."));
			return true;
		}
		// If args[0] are "version" or "ver"
		if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
			// If sender has permission lifesteal.admin
			if (!lifesteal.getPermissions().has(sender, "lifesteal.admin")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			// UpdateChecker (for latest version by spigot api)
			new UpdateChecker(lifesteal, 102599, lifesteal).getVersion(version -> {
				// Messages
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Latest version is &6" + version + "&7."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your plugin version is &6" + lifesteal.version + "&7."));
			});
			return true;
		}
		// Command /lifesteal send
		if (args[0].equalsIgnoreCase("send")) {
			// If sender has permission lifesteal.send
			if (!lifesteal.getPermissions().has(sender, "lifesteal.send")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			// Target from args[1]
			final Player target = Bukkit.getPlayer(args[1]);
			// senderPlayer (casting to have access to new methods)
			Player senderPlayer = (Player) sender;
			// Getter for name of target (not important)
			String name = sender.getName();
			// If target is null
			if (target == null) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be found."));
				return false;
			}
			// If target have same named as sender
			if (target.getName().equals(name)) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7You can't send your hearts to you."));
				return false;
			}
			// If args[2] are smaller than health of senderPlayer (sender)
			if (!(Integer.parseInt(args[2]) < senderPlayer.getMaxHealth())) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_big_amount_of_hearts")));
				return false;
			}
			// Making args[2] to double (because setMaxHealth supports only double)
			double amount = Double.parseDouble(args[2]);
			// Making sender's health to double
			double senderHealth = lifesteal.getConfig().getInt("player." + name);
			// Making target's health to double
			double targetHealth = lifesteal.getConfig().getInt("player." + target.getName());
			// Setting max health for target (+ amount)
			target.setMaxHealth(targetHealth + amount);
			// Setting max health for sender (- amount)
			senderPlayer.setMaxHealth(senderHealth - amount);
			// Setting amount of hearts in config for sender and target (saveConfig is very important)!
			lifesteal.getConfig().set("player." + target.getName(), target.getMaxHealth() + amount);
			lifesteal.saveConfig();
			lifesteal.getConfig().set("player." + name, senderPlayer.getMaxHealth() - amount);
			lifesteal.saveConfig();
			// Messages with placeholders {target}, {sender}, {amount}, {prefix} and {suffix}
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{target}", "{amount}", "{suffix}", "{prefix}", target.getName(), args[2],lifesteal.getChat().getPlayerPrefix(target),lifesteal.getChat().getPlayerSuffix(target), "messages.hearts_sent", lifesteal)));
			target.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{sender}", "{amount}", "{suffix}", "{prefix}", sender.getName(), args[2],lifesteal.getChat().getPlayerPrefix(senderPlayer),lifesteal.getChat().getPlayerSuffix(senderPlayer), "messages.hearts_sent", lifesteal)));
			return true;
		}
		if (args[0].equalsIgnoreCase("recipe") || args[0].equalsIgnoreCase("showrecipe")) {
			// If sender has permission lifesteal.send
			if (!lifesteal.getPermissions().has(sender, "lifesteal.send")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			// Making and casting sender to player
			Player player = (Player) sender;
			// Creating inventory and then opening
			ccg.CreateInventory();
			ccg.OpenInventory(player);
			// Message
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.recipe_showed")));
			return true;
		}
			/* @Deprecated
			 (replacing [ and ] with air, and making \n with new line, doesn't work, and in development status!
			 message.colorCodes(sender, message.replace("[", "]", "\n", "", "", " ", "messages.help", lifesteal));
			 */
		message.colorCodes(sender, "&cLS &a>> &7HELP");
		message.colorCodes(sender, "&7You can use /ls, /lifesteal or /lfs to use plugin commands.");
		message.colorCodes(sender, "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)");
		message.colorCodes(sender, "&7/lifesteal reload &a(Reload config.yml)");
		message.colorCodes(sender, "&7/lifesteal author &a(Shows who is author)");
		message.colorCodes(sender, "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)");
		message.colorCodes(sender, "&7/lifeSteal send &a(You can send some of your hearts to other player)");
		message.colorCodes(sender, "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)");
		message.colorCodes(sender, "&7/lifeSteal help &a(Send all available commands)");
		return false;
	}
}
