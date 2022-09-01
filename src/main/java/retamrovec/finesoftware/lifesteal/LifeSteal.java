package retamrovec.finesoftware.lifesteal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.Command.HealthManager;
import retamrovec.finesoftware.lifesteal.Command.HealthManagerTab;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.Listeners.InventoryClickListener;
import retamrovec.finesoftware.lifesteal.Listeners.PlayerDeathListener;
import retamrovec.finesoftware.lifesteal.Listeners.PlayerItemConsumeListener;
import retamrovec.finesoftware.lifesteal.Listeners.PlayerJoinListener;
import retamrovec.finesoftware.lifesteal.Manager.*;

public class LifeSteal extends JavaPlugin implements Listener {

	public final PluginDescriptionFile pdf = this.getDescription();
	public final String version = pdf.getVersion();
	private final String configVersion = getConfig().getString("plugin.version");
	public void onEnable() {
		CustomCraftingGUI CustomCraftingGUI = new CustomCraftingGUI(this);
		Message Message = new Message();
		DebugHandler debug = new DebugHandler(this);
		Heart heart = new Heart(this);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerItemConsumeListener(heart, this, debug), this);
		pm.registerEvents(new PlayerDeathListener(this, debug), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new InventoryClickListener(this, new CustomCraftingGUI(this), heart, debug), this);
		pm.registerEvents(this, this);
		getCommand("lifesteal").setExecutor(new HealthManager(this, CustomCraftingGUI, Message, debug, heart));
		getCommand("lifesteal").setTabCompleter(new HealthManagerTab(this));
		if (getConfig().getString("plugin.version") == null || getConfig().getString("plugin.version").isEmpty()) {
			Bukkit.getPluginManager().disablePlugin(this);
			getLogger().info("Restart server for functionality.");
		}
		registerConfig();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		saveConfig();
		getConfig().set("plugin.version", version);
		saveConfig();
		getLogger().info("Config.yml was generated..");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', "Version (" + configVersion + ") has been enabled."));
		heart.init(this);
		PAPI.init();

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		new UpdateChecker(this, 102599).getVersion(version -> {
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
		if (getConfig().getBoolean("config.bStats")) {
			int pluginId = 16131;
			Metrics metrics = new Metrics(this, pluginId);
		}
		debug.info(" ");
		debug.info("|-------------------------------------------|");
		debug.info("|                                           |");
		debug.info("| Fine LifeSteal initialization is done!    |");
		Message.isAllowed(getConfig().getBoolean("config.bStats"),
				   "| bStats are enabled.                       |", "| bStats are disabled.                      |", debug);
		Message.isAllowed(getConfig().getBoolean("developer.enable"),
				   "| Developer mode is enabled.                |", "| Developer mode is disabled.               |", debug);
		debug.info("|                                           |");
		debug.info("|-------------------------------------------|");
		debug.info(" ");
	}
	
	public void onDisable() {
		DebugHandler debug = new DebugHandler(this);
		String configVersion = getConfig().getString("plugin.version");
		debug.info(" ");
		debug.info("|-------------------------------------------|");
		debug.info("|                                           |");
		debug.info("| Fine LifeSteal un-initialization is done! |");
		debug.info("|                                           |");
		debug.info("|-------------------------------------------|");
		debug.info(" ");
		saveConfig();
	}

	public List<Material> getRecipe() {
		List<Material> recipe = new ArrayList<>();
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.first")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.second")));
		recipe.add(Material.matchMaterial(getConfig().getString("recipe.ingredients.third")));
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
	        new UpdateChecker(this, 102599).getVersion(version -> {
	            if (!configVersion.equals(version)) {
	                if (this.getConfig().getBoolean("config.notify_op_updates") == true) {
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.update_available_notify")));
	                }
	            }
	        });
		}
	}

}
