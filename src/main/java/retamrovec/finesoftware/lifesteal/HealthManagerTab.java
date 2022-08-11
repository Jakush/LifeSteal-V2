package retamrovec.finesoftware.lifesteal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class HealthManagerTab implements TabCompleter {
	
	LifeSteal lifesteal;
	public HealthManagerTab (LifeSteal lifesteal) {
		this.lifesteal = lifesteal;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		final List<String> tabcompleterarray = new ArrayList<String>();
		
		if (args.length == 1) {
			return StringUtil.copyPartialMatches(args[0], Arrays.asList("author","help","reload","spigotmc","set","send","recipe","showrecipe"), tabcompleterarray);
		} else if (args.length == 2) {
			List<String> names = new ArrayList<>();
			for (Player player : Bukkit.getOnlinePlayers()) {
				names.add(player.getName());
			}
			return StringUtil.copyPartialMatches(args[1], names, tabcompleterarray);
		} else if (args.length == 3) {
			return StringUtil.copyPartialMatches(args[2], Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40"), new ArrayList<>());
		}
		
		return tabcompleterarray;
	}

}
