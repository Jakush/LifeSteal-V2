package retamrovec.finesoftware.lifesteal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.Command.HealthManager;
import retamrovec.finesoftware.lifesteal.Command.HealthManagerTab;
import retamrovec.finesoftware.lifesteal.Listeners.InventoryClickListener;
import retamrovec.finesoftware.lifesteal.Listeners.PlayerDeathListener;
import retamrovec.finesoftware.lifesteal.Listeners.PlayerJoinListener;
import retamrovec.finesoftware.lifesteal.Manager.*;

public class LifeSteal extends JavaPlugin implements Listener {

	private static final Logger log = Logger.getLogger("Minecraft");
	private static Economy econ = null;
	private static Permission perms = null;
	private static Chat chat = null;
	public final PluginDescriptionFile pdf = this.getDescription();
	public final String version = pdf.getVersion();
	private final String configVersion = getConfig().getString("plugin.version");
	public void onEnable() {
		CustomCraftingGUI CustomCraftingGUI = new CustomCraftingGUI(this);
		Message Message = new Message();
		DebugHandler debug = new DebugHandler(this);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerDeathListener(this), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new InventoryClickListener(this, new CustomCraftingGUI(this)), this);
		pm.registerEvents(this, this);
		getCommand("lifesteal").setExecutor(new HealthManager(this, CustomCraftingGUI, Message, debug));
		getCommand("lifesteal").setTabCompleter(new HealthManagerTab(this));
		if (configVersion == null || getConfig().getString("plugin.version").isEmpty()) {
			Bukkit.getPluginManager().disablePlugin(this);
			getLogger().info("Restart server for functionality.");
		}
		setupChat();
		setupPermissions();
		registerConfig();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		saveConfig();
		getConfig().set("plugin.version", version);
		saveConfig();
		getLogger().info("Config.yml was generated..");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', "Version (" + configVersion + ") has been enabled."));

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		new UpdateChecker(this, 102599, this).getVersion(version -> {
			if (configVersion.equals(version)) {
				getLogger().info("Plugin version is the latest");
			} else {
				getLogger().info("Plugin version is not latest. Please update this plugin.");
			}
			if (this.getConfig().getBoolean("developer.enable")) {
				getLogger().info("Latest version is " + version);
				getLogger().info("Your version is " + configVersion);
			}
		});

		int pluginId = 16131;
		Metrics metrics = new Metrics(this, pluginId);
	}
	
	public void onDisable() {
		String configVersion = getConfig().getString("plugin.version");
		getLogger().severe("Version (" + configVersion + ") has been disabled.");
		saveConfig();
	}

	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		if (rsp == null) {
			getLogger().warning("Chat plugin was not found! Disabling {prefix} and {suffix} features.");
			return false;
		}
		chat = rsp.getProvider();
		return chat != null;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}
	public Chat getChat() {
		return chat;
	}

	public Permission getPermissions() {
		return perms;
	}


	public List<Material> getRecipe() {
		List<Material> recipe = new ArrayList<>();
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.first")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.second")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.thirst")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.fourth")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.fifth")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.sixth")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.seventh")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.eighth")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.ninth")));
		return recipe;
	}
	
	private void registerConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
    		getLogger().info("Generating config.yml... It can take a while.");
            saveDefaultConfig();
        }
    }
	
	@EventHandler (priority = EventPriority.HIGH)
	private void onJoin(@NotNull PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String configVersion = getConfig().getString("plugin.version");
		if (player.isOp() || player.hasPermission("lifesteal.admin")) {
	        new UpdateChecker(this, 102599, this).getVersion(version -> {
	            if (!configVersion.equals(version)) {
	                if (this.getConfig().getBoolean("config.notify_op_updates") == true) {
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.update_available_notify")));
	                }
	            }
	        });
		}
	}

}
