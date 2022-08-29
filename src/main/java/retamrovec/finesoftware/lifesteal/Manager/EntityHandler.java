package retamrovec.finesoftware.lifesteal.Manager;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class EntityHandler {

    public static boolean isConsole(CommandSender sender) {
        return sender instanceof ConsoleCommandSender;
    }

    public static Player castConsole(CommandSender sender) {
        return (Player) sender;
    }

    public static Player castConsole(HumanEntity sender) {
        return (Player) sender;
    }

    @Contract(pure = true)
    public static @Nullable Player castNoConsole(CommandSender sender) {
        if (!isConsole(sender)) {
            return (Player) sender;
        }
        return Bukkit.getPlayer("CONSOLE");
    }

}
