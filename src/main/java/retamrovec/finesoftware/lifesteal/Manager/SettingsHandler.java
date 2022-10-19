package retamrovec.finesoftware.lifesteal.Manager;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;
import retamrovec.finesoftware.lifesteal.Storage.Eliminate;

import java.util.ArrayList;
import java.util.List;

public class SettingsHandler {

    private static int taskID;

    public static boolean loseHeartsOnMobs(@NotNull LifeSteal lifeSteal) {
        return lifeSteal.getConfig().getBoolean("config.lose_hearts_on_mobs");
    }

    public static void runEliminateCommands(@NotNull LifeSteal lifeSteal, Player player) {
        if (lifeSteal.getConfig().getString("config.eliminate.commands").equals("''") || lifeSteal.getConfig().getString("config.eliminate.commands") == null) {
            return;
        }
        if (lifeSteal.getConfig().getStringList("config.eliminate.commands").isEmpty()) {
            return;
        }
        if (lifeSteal.getConfig().getStringList("config.eliminate.commands").size() < 1) {
            return;
        }
        for (int i = 0; i < lifeSteal.getConfig().getStringList("config.eliminate.commands").size(); i++) {
            List<String> commands = new ArrayList<>(lifeSteal.getConfig().getStringList("config.eliminate.commands"));
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = commands.get(i);
            command = PlaceholderAPI.setPlaceholders(player, command);
            Bukkit.dispatchCommand(console, command);
            Eliminate e = new Eliminate(player.getName());
            e.setStatus(true);
        }
    }

    public static void runReviveCommands(@NotNull LifeSteal lifeSteal, OfflinePlayer player) {
        if (lifeSteal.getConfig().getString("config.revive.commands").equals("''") || lifeSteal.getConfig().getString("config.revive.commands") == null) {
            return;
        }
        if (lifeSteal.getConfig().getStringList("config.revive.commands").isEmpty()) {
            return;
        }
        if (lifeSteal.getConfig().getStringList("config.revive.commands").size() < 1) {
            return;
        }
        for (int i = 0; i < lifeSteal.getConfig().getStringList("config.revive.commands").size(); i++) {
            List<String> commands = new ArrayList<>(lifeSteal.getConfig().getStringList("config.revive.commands"));
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String command = commands.get(i);
            command = PlaceholderAPI.setPlaceholders(player, command);
            Bukkit.dispatchCommand(console, command);
            Eliminate e = new Eliminate(player.getName());
            e.setStatus(false);
        }
    }

    public static void runRevivingStatus(String name, OfflinePlayer player, LifeSteal lifeSteal) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncDelayedTask(lifeSteal, () -> {
            Eliminate e = new Eliminate(name);
            if (LifeSteal.getInstance().getEliminatedPlayers().contains(player.getName())) {
                e.setStatus(false);
                runReviveCommands(lifeSteal, player);
            }
        }, 300L);
    }


    public static int getTaskID() {
        return taskID;
    }

}
