package retamrovec.finesoftware.lifesteal.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.LifeSteal;

public class HealthManagerTab implements TabCompleter {
	
	LifeSteal lifesteal;
	public HealthManagerTab (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		final List<String> tab = new ArrayList<>();
		
		if (args.length == 1) {
			return StringUtil.copyPartialMatches(args[0], Arrays.asList("author","help","reload","spigotmc","set","send","recipe","showRecipe","ver","version","revive"), tab);
		}
		if (args.length == 2) {
			List<String> names = new ArrayList<>();
			for (Player player : Bukkit.getOnlinePlayers()) {
				names.add(player.getName());
			}
			return StringUtil.copyPartialMatches(args[1], names, tab);
		}
		if (args.length == 3) {
			List<String> num = new ArrayList<>();
			for (int i = 1; i < 40; i++) {
				num.add(String.valueOf(i));
			}
			return StringUtil.copyPartialMatches(args[2], num, tab);
		}
		
		return tab;
	}

}
