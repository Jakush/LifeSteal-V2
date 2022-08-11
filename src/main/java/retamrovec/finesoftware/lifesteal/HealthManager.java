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
		// If args doesn't exist
		if (args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7HELP"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You can use /ls, /lifeSteal or /lfs to use plugin commands."));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal reload &a(Reload config.yml)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal author &a(Shows who is author)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal send &a(You can send some of your hearts to other player)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal help &a(Send all available commands)"));
			return true;
		}

		// If sender is not player
		if (!(sender instanceof Player)) {
			lifesteal.getLogger().severe("You are not player!");
			return false;
		}

		// If sender has one of the permissions lifeSteal.admin, lifeSteal.send or lifeSteal.showRecipe
		if (!sender.hasPermission("lifeSteal.admin") || !sender.hasPermission("lifeSteal.send") || !sender.hasPermission("lifeSteal.showRecipe")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.without_perm")));
			return false;
		}

		// If sender has permission lifeSteal.admin
		if (sender.hasPermission("lifeSteal.admin")) {
			// If args length one, not enough arguments
			if (args.length == 1) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid use. &c/lifeSteal help &7for more."));
				return false;
			}
			// If arg[0] are "set"
			if (args[0].equals("set")) {
				final Player target = Bukkit.getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Target was cannot be found."));
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
			// If args[0] are "reload"
			if (args[0].equalsIgnoreCase("reload")) {
				// Loops through every online player, command is deprecated because, this can make massive lags if you have many people joined on server.
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
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.too_much_hearts")));
					}
					else {
						// Set amount of hearts to new amount of hearts in config
						player.setMaxHealth(amount);
					}
				}
				// Remove recipe (important, removing this cause bugs)
				Bukkit.getServer().removeRecipe(ccm.getRecipe(lifesteal));
				// Register recipe "lifeSteal" is main class because this method needs main class
				ccm.registerRecipe(lifesteal);
				// Reloads configuration (that's what should /lifeSteal reload do)
				lifesteal.reloadConfig();
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.config_reloaded")));
				return true;
			}
			// If args[0] are "author"
			if (args[0].equalsIgnoreCase("author")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7Author of this plugin is &aDiskotekaSTARM&7. InGame nick is &aRETAMROVEC&7."));
				return true;
			}
			// If args[0] are "spigotMC"
			if (args[0].equalsIgnoreCase("spigotMC")) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7This plugin is on &aSpigotMC&7 and link is: &9https://www.spigotmc.org/resources/lifesteal.102599/&7."));
				return true;
			}
			// If args[0] are "help"
			if (args[0].equalsIgnoreCase("help")) {
				// Help messages
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLS &a>> &7HELP"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You can use /ls, /lifeSteal or /lfs to use plugin commands."));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal reload &a(Reload config.yml)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal author &a(Shows who is author)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal send &a(You can send some of your hearts to other player)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)"));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/lifeSteal help &a(Send all available commands)"));
				return true;
			}
		}
		// If sender has permission lifeSteal.send
		if (sender.hasPermission("lifeSteal.send")) {
			// If args[0] are sent
			if (args[0].equalsIgnoreCase("send")) {
				// Target from args[1]
				final Player target = Bukkit.getPlayer(args[1]);
				// senderPlayer (casting to have access to new methods)
				Player senderPlayer = (Player) sender;
				// Getter for name of target (not important)
				String name = target != null ? target.getName() : null;
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
				// Messages with placeholders {target}, {sender} and {amount}
				target.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{target}", "{amount}", target.getName(), args[2], "messages.hearts_sent", lifesteal)));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message.replace("{sender}", "{amount}", sender.getName(), args[2], "messages.hearts_sent", lifesteal)));
				return true;
			}
		}
		// If sender has permission lifeSteal.showRecipe
		if (sender.hasPermission("lifeSteal.showRecipe")) {
			// If sender is not player
			if (!(sender instanceof Player)) {
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("error.cannot_use_command")));
				return false;
			}
			// If args[0] are showRecipe or recipe
			if (args[0].equalsIgnoreCase("showRecipe") || args[0].equalsIgnoreCase("recipe")) {
				// Making and casting sender to player
				Player player = (Player) sender;
				// Creating inventory and then opening
				ccg.CreateInventory();
				ccg.OpenInventory(player);
				// Message
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', lifesteal.getConfig().getString("messages.recipe_showed")));
				return true;
			}
		}
		return false;
	}
}
