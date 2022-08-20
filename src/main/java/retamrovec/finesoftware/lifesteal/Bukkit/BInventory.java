package retamrovec.finesoftware.lifesteal.Bukkit;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BInventory {

    public static void removeItem(Inventory inventory, ItemStack itemStack) {
        ItemStack is = itemStack.clone();
        is.setAmount(1);
        inventory.removeItem(is);
    }

}
