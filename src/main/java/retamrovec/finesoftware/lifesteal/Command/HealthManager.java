package retamrovec.finesoftware.lifesteal.Command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.*;
import retamrovec.finesoftware.lifesteal.Events.CommandUseEvent;
import retamrovec.finesoftware.lifesteal.Events.PlayerReviveEvent;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.Manager.*;
import retamrovec.finesoftware.lifesteal.Storage.Edit;
import retamrovec.finesoftware.lifesteal.Storage.Eliminate;

public class HealthManager implements CommandExecutor {

	LifeSteal lifesteal;
	DebugHandler debug;
	Message message;
	CustomCraftingGUI ccg;
	Heart heart;
	public HealthManager (LifeSteal lifesteal, CustomCraftingGUI ccg, Message message, DebugHandler debug, Heart heart) {
		this.lifesteal = lifesteal;
		this.ccg = ccg;
		this.message = message;
		this.debug = debug;
		this.heart = heart;
	}

	@Override
	@Deprecated
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
		// Command /lifesteal
		if (args.length == 0) {
			/* @Deprecated
			 (replacing [ and ] with air, and making \n with new line, doesn't work, and in development status!
			 message.colorCodes(sender, message.replace("[", "]", "\n", "", "", " ", "messages.help", lifesteal));
			 */
			Message.colorCodesPAPI((Player) sender, "&cLS &a>> &7HELP");
			Message.colorCodesPAPI((Player) sender, "&7You can use /ls, /lifesteal or /lfs to use plugin commands.");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal reload &a(Reload config.yml)");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal author &a(Shows who is author)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal send &a(You can send some of your hearts to other player)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal help &a(Send all available commands)");
			debug.init("Sending message.");
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				debug.init("Calling commandUseEvent.");
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal revive
		if (args[0].equalsIgnoreCase("revive")) {
			debug.init("Checking player permissions.");
			if (!sender.hasPermission("lifesteal.revive")) {
				// Message
				debug.init("Sending message.");
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			if (args.length < 2) {
				debug.init("Checking args.");
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.unknown-error"));
				return false;
			}
			OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(args[1]);
			new Eliminate(oPlayer.getName());
			if (!lifesteal.getConfig().contains("player." + oPlayer.getName())) {
				debug.error("Player " + oPlayer.getName() + " is not configuration. Cancelling event, if issue is there even after rejoin, please report it on support.");
				return false;
			}
			lifesteal.getConfig().set("player." + oPlayer.getName(), 20);
			lifesteal.saveConfig();
			if (!Eliminate.getStatus()) {
				debug.error("Sending message.");
				Message.colorCodesPAPI(sender, oPlayer, lifesteal.getConfig().getString("error.player-is-alive"));
				return false;
			}
			SettingsHandler.runReviveCommands(lifesteal, oPlayer);
			debug.init("Sending message.");
			Message.colorCodesPAPI(sender, oPlayer, lifesteal.getConfig().getString("messages.player-revived"));
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			PlayerReviveEvent playerReviveEvent = new PlayerReviveEvent(oPlayer, lifesteal.getConfig().getString("messages.player-revived"));
			if (!playerReviveEvent.isCancelled()) {
				debug.init("Calling playerReviveEvent.");
				Bukkit.getPluginManager().callEvent(playerReviveEvent);
			}
			return true;
		}

		// If sender is not player
		if (!(sender instanceof Player)) {
			debug.error("Command sender is not player.");
			lifesteal.getLogger().severe("You are not a player!");
			return false;
		}

		// Command /lifesteal help
		if (args[0].equalsIgnoreCase("help")) {
			debug.init("Checking player permissions.");
			// If sender has permission lifesteal.admin
			if (!sender.hasPermission("lifesteal.admin")) {
				debug.init("Sending message.");
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			/* @Deprecated
			 (replacing [ and ] with air, and making \n with new line, doesn't work, and in development status!
			 message.colorCodes(sender, message.replace("[", "]", "\n", "", "", " ", "messages.help", lifesteal));
			 */
			Message.colorCodesPAPI((Player) sender, "&cLS &a>> &7HELP");
			Message.colorCodesPAPI((Player) sender, "&7You can use /ls, /lifesteal or /lfs to use plugin commands.");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal reload &a(Reload config.yml)");
			Message.colorCodesPAPI((Player) sender, "&7/lifesteal author &a(Shows who is author)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal send &a(You can send some of your hearts to other player)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)");
			Message.colorCodesPAPI((Player) sender, "&7/lifeSteal help &a(Send all available commands)");
			debug.init("Sending message.");
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				debug.init("Calling commandUseEvent.");
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal set
		if (args[0].equalsIgnoreCase("set")) {
			debug.init("Checking player permissions.");
			// If sender has permission lifesteal.admin
			if (!sender.hasPermission("lifesteal.admin")) {
				debug.init("Sending message.");
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			// If args are bigger or smaller than 2
			if (!(args.length == 3)) {
				debug.init("Checking args.");
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.amount_not_exist"));
				return false;
			}
			// target
			final Player target = Bukkit.getPlayer(args[1]);
			// Checks if target is null
			if (target == null) {
				debug.error("Target is null.");
				Message.colorCodesPAPI(sender, target, lifesteal.getConfig().getString("player-is-null"));
				return false;
			}
			// If args[2] are not under 40 hearts (arg[2] are bigger)
			if (Integer.parseInt(args[2]) > 40) {
				debug.error("Invalid amount of hearts.");
				Message.colorCodesPAPI(sender, target, lifesteal.getConfig().getString("error.too_big_amount_of_hearts"));
				return false;
			}
			// Get amount of hearts in command
			double amount = Double.parseDouble(args[2]);
			// Set amount of hearts
			target.setMaxHealth(amount);
			// Type it in config new amount and save config (saveConfig is very important)!
			debug.init("Changing value of player." + target.getName() + " to " + amount);
			lifesteal.getConfig().set("player." + target.getName(), amount);
			debug.init("Saving config.yml");
			lifesteal.saveConfig();
			// Message
			Message.colorCodesPAPI(sender, target, lifesteal.getConfig().getString("messages.changed_amount_of_health"));
			debug.init("Sending message.");
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				debug.init("Calling commandUseEvent.");
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal reload
		if (args[0].equalsIgnoreCase("reload")) {
			// If sender has permission lifesteal.admin
			if (!sender.hasPermission("lifesteal.admin")) {
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
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
					Message.colorCodesPAPI(player, lifesteal.getConfig().getString("error.too_much_hearts"));
				} else {
					// Set amount of hearts to new amount of hearts in config
					player.setMaxHealth(amount);
				}
			}
			// Registering & unregistering recipe
			heart.init(lifesteal);
			// Reloads configuration (that's what should /lifeSteal reload do)
			lifesteal.reloadConfig();
			// Message
			Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("messages.config_reloaded"));
			debug.init("Sending message.");
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				debug.init("Calling commandUseEvent.");
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal author
		if (args[0].equalsIgnoreCase("author")) {
			// If sender has permission lifesteal.admin
			if (!sender.hasPermission("lifesteal.admin")) {
				// Message
				Message.colorCodes(sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			// Message
			sender.sendMessage(Message.colour("&cLS &a>> &7Author of this plugin is &aDiskotekaSTARM&7. InGame nick is &aRETAMROVEC&7."));
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal spigotmc
		if (args[0].equalsIgnoreCase("spigotMC")) {
			if (!sender.hasPermission("lifesteal.admin")) {
				// Message
				Message.colorCodes(sender, lifesteal.getConfig().getString("error.without_perm"));
            	return false;
			}
			// Message
			sender.sendMessage(Message.colour("&cLS &a>> &7This plugin is on &aSpigotMC&7 and link is: &9https://www.spigotmc.org/resources/lifesteal.102599/&7."));
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// If args[0] are "version" or "ver"
		if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
			// If sender has permission lifesteal.admin
			if (!sender.hasPermission("lifesteal.admin")) {
				// Message
				sender.sendMessage(Message.colour(lifesteal.getConfig().getString("error.without_perm")));
				return false;
			}
			// UpdateChecker (for latest version by spigot api)
			new UpdateChecker(lifesteal, 102599).getVersion(version -> {
				// Messages
				sender.sendMessage(Message.colour("&7Latest version is &6" + version + "&7."));
				sender.sendMessage(Message.colour("&7Your plugin version is &6" + lifesteal.version + "&7."));
			});
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal send
		if (args[0].equalsIgnoreCase("send")) {
			// If sender has permission lifesteal.send
			if (!sender.hasPermission("lifesteal.send")) {
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			if (args.length < 2) {
				debug.init("Args are smaller than 2.");
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.unknown-error"));
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
				Message.colorCodesPAPI(senderPlayer, lifesteal.getConfig().getString("player-is-null"));
				return false;
			}
			// If target have same named as sender
			if (target.getName().equals(name)) {
				// Message
				Message.colorCodesPAPI(senderPlayer, "&cLS &a>> &7You can't send your hearts to you.");
				return false;
			}
			// If args[2] are smaller than health of senderPlayer (sender)
			if (!(Integer.parseInt(args[2]) < senderPlayer.getMaxHealth())) {
				Message.colorCodesPAPI(senderPlayer, lifesteal.getConfig().getString("error.too_big_amount_of_hearts"));
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
			Message.colorCodesPAPI(senderPlayer, lifesteal.getConfig().getString("messages.hearts_sent"));
			Message.colorCodesPAPI(target, lifesteal.getConfig().getString("messages.hearts_get"));CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		// Command /lifesteal recipe or /lifesteal showrecipe
		if (args[0].equalsIgnoreCase("recipe") || args[0].equalsIgnoreCase("showrecipe")) {
			// If sender has permission lifesteal.send
			if (!sender.hasPermission("lifesteal.recipe")) {
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			// Making and casting sender to player
			Player player = (Player) sender;
			// Creating inventory and then opening
			ccg.CreateHeartInventory();
			ccg.OpenHeartInventory(player);
			// Message
			Message.colorCodesPAPI(player, lifesteal.getConfig().getString("messages.recipe_showed"));
			CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
			if (!commandUseEvent.isCancelled()) {
				Bukkit.getPluginManager().callEvent(commandUseEvent);
			}
			return true;
		}
		if (args[0].equalsIgnoreCase("editRecipe") || args[0].equalsIgnoreCase("edit")) {
			debug.init("Checking args[0].");
			debug.init("Checking permissions.");
			if (!sender.hasPermission("lifesteal.editRecipe")) {
				// Message
				Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("error.without_perm"));
				return false;
			}
			// Making and casting sender to player
			Player player = (Player) sender;
			// Creating option for editing recipe
			debug.init("Creating option to edit recipe.");
			new Edit(player);
			Edit.setStatus(true);
			debug.init("Opening inventory.");
			// Creating inventory and then opening
			ccg.CreateHeartInventory();
			ccg.OpenHeartInventory(player);
			// Message
			Message.colorCodesPAPI((Player) sender, lifesteal.getConfig().getString("messages.recipe_showed"));
			return true;
		}
			/* @Deprecated
			 (replacing [ and ] with air, and making \n with new line, doesn't work, and in development status!
			 message.colorCodes(sender, message.replace("[", "]", "\n", "", "", " ", "messages.help", lifesteal));
			 */
		Message.colorCodesPAPI((Player) sender, "&cLS &a>> &7HELP");
		Message.colorCodesPAPI((Player) sender, "&7You can use /ls, /lifesteal or /lfs to use plugin commands.");
		Message.colorCodesPAPI((Player) sender, "&7/lifesteal set <online player> <amount of hearts> &a(Set specific amount of hearts to player)");
		Message.colorCodesPAPI((Player) sender, "&7/lifesteal reload &a(Reload config.yml)");
		Message.colorCodesPAPI((Player) sender, "&7/lifesteal author &a(Shows who is author)");
		Message.colorCodesPAPI((Player) sender, "&7/lifeSteal spigotMC &a(Sends where plugin can be downloaded)");
		Message.colorCodesPAPI((Player) sender, "&7/lifeSteal send &a(You can send some of your hearts to other player)");
		Message.colorCodesPAPI((Player) sender, "&7/lifeSteal recipe&c/&7showRecipe &a(Show recipe inGame)");
		Message.colorCodesPAPI((Player) sender, "&7/lifeSteal help &a(Send all available commands)");
		CommandUseEvent commandUseEvent = new CommandUseEvent(sender, args);
		if (!commandUseEvent.isCancelled()) {
			Bukkit.getPluginManager().callEvent(commandUseEvent);
		}
		return false;
	}
}
