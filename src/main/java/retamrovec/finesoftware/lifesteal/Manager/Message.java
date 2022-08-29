package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Message {

    @Deprecated
    public static void colorCodes(@NotNull CommandSender sender, @NotNull String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void colorCodes(@NotNull Player player, @NotNull String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void isAllowed(boolean bool, String trueMessage, String falseMessage, DebugHandler debug) {
        if (!bool) {
            debug.info(falseMessage);
        }
        debug.info(trueMessage);
    }

}
