package retamrovec.finesoftware.lifesteal;

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
import retamrovec.finesoftware.lifesteal.Itemstacks.Beacon;
import retamrovec.finesoftware.lifesteal.Itemstacks.Heart;
import retamrovec.finesoftware.lifesteal.Listeners.*;
import retamrovec.finesoftware.lifesteal.Manager.*;
import retamrovec.finesoftware.lifesteal.Storage.Edit;
import retamrovec.finesoftware.lifesteal.Storage.EliminatedPlayers;
import retamrovec.finesoftware.lifesteal.Storage.Hologram;
import retamrovec.finesoftware.lifesteal.Storage.HologramStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LifeSteal extends JavaPlugin implements Listener {

	public final PluginDescriptionFile pdf = this.getDescription();
	public final String version = pdf.getVersion();
	private final EliminatedPlayers eliminatedPlayers = new EliminatedPlayers();
	private final HologramStorage hologramStorage = new HologramStorage();
	private final Edit edit = new Edit();
	static LifeSteal lf;

	public void onEnable() {
		lf = this;
		CustomCraftingGUI CustomCraftingGUI = new CustomCraftingGUI(this);
		Message message = new Message();
		DebugHandler debug = new DebugHandler(this);
		Heart heart = new Heart(this);
		Beacon beacon = new Beacon(this, debug);
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EntityDamageByEntityListener(this, debug), this);
		pm.registerEvents(new BlockPlaceListener(debug), this);
		pm.registerEvents(new BlockBreakListener(this, debug), this);
		pm.registerEvents(new PlayerItemConsumeListener(heart, this, debug), this);
		pm.registerEvents(new PlayerDeathListener(this, debug), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new InventoryClickListener(this, new CustomCraftingGUI(this), heart, debug, beacon), this);
		pm.registerEvents(this, this);
		getCommand("lifesteal").setExecutor(new HealthManager(this, CustomCraftingGUI, message, debug, heart));
		getCommand("lifesteal").setTabCompleter(new HealthManagerTab(this));
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists() || file.length() < 2) {
			Bukkit.getPluginManager().disablePlugin(this);
			getLogger().info("Restart server for functionality.");
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		saveConfig();
		getLogger().info("Config.yml was generated..");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', "Version (" + version + ") has been enabled."));
		heart.init(this);
		beacon.init(this);
		PAPI.init();

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		/*
		new UpdateChecker(this, 102599).getVersion(version -> {
			if (this.version.equals(version)) {
				getLogger().info("Plugin version is the latest");
			} else {
				getLogger().info("Plugin version is not latest. Please update this plugin.");
			}
			if (this.getConfig().getBoolean("developer.enable")) {
				getLogger().info("Latest version is " + version);
				getLogger().info("Your version is " + this.version);
			}
		});
		 */
		if (getConfig().getBoolean("config.bStats")) {
			int pluginId = 16131;
			Metrics metrics = new Metrics(this, pluginId);
		}
		eliminatedPlayers.getEliminatedPlayers().clear();
		hologramStorage.getHologramStorage().clear();
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

	public List<String> getEliminatedPlayers() {
		return eliminatedPlayers.getEliminatedPlayers();
	}
	public List<Hologram> getHologramStorage() {
		return hologramStorage.getHologramStorage();
	}
	public List<Player> getEdit() {
		return edit.getEditors();
	}

	public static LifeSteal getInstance() {
		return lf;
	}

	@EventHandler (priority = EventPriority.HIGH)
	private void onJoin(@NotNull PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (player.isOp() || player.hasPermission("lifesteal.admin")) {
	        new UpdateChecker(this, 102599).getVersion(version -> {
	            if (!this.version.equals(version)) {
	                if (this.getConfig().getBoolean("config.notify_op_updates")) {
		                player.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages.update_available_notify")));
	                }
	            }
	        });
		}
	}

}
