package retamrovec.finesoftware.lifesteal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class LifeSteal extends JavaPlugin implements Listener {

	private final String version = getDescription().getVersion();
	public void onEnable() {
		CustomCraftingGUI CustomCraftingGUI = new CustomCraftingGUI(this);
		CustomCraftingManager CustomCraftingManager = new CustomCraftingManager(this);
		Message Message = new Message();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerDeathListener(this), this);
		pm.registerEvents(new CraftItemListener(this), this);
		pm.registerEvents(new PlayerJoinListener(this), this);
		pm.registerEvents(new InventoryClickListener(this, new CustomCraftingGUI(this)), this);
		pm.registerEvents(this, this);
		getCommand("lifesteal").setExecutor(new HealthManager(this, CustomCraftingGUI, CustomCraftingManager, Message));
		getCommand("lifesteal").setTabCompleter(new HealthManagerTab(this));
		registerConfig();
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		saveConfig();
		getConfig().set("plugin.version", this.version);
		saveConfig();
		getLogger().info("Config.yml was generated..");
		String configVersion = getConfig().getString("plugin.version");
		getLogger().info(ChatColor.translateAlternateColorCodes('&', "Version (" + configVersion + ") has been enabled."));

		CustomCraftingManager ccm = new CustomCraftingManager(this);
		ccm.registerRecipe(this);

        new UpdateChecker(this, 102599, this).getVersion(version -> {
            if (configVersion.equals(version)) {
                getLogger().info("Plugin version is the latest");
                if (this.getConfig().getBoolean("developer.enable")) {
                	getLogger().info("Latest version is " + version);
                	getLogger().info("Your version is " + configVersion);
                }
            } else {
                getLogger().info("Plugin version is not latest. Please update this plugin.");
                if (this.getConfig().getBoolean("developer.enable")) {
                	getLogger().info("Latest version is " + version);
                	getLogger().info("Your version is " + configVersion);
                }
            }
        });
	}
	
	public void onDisable() {
		String configVersion = getConfig().getString("plugin.version");
		getLogger().severe("Version (" + configVersion + ") has been disabled.");
		saveConfig();
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
	private void onJoin(PlayerJoinEvent e) {
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
