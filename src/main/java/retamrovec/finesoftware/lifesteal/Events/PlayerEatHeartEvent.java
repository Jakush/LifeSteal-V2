package retamrovec.finesoftware.lifesteal.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerEatHeartEvent extends Event implements Cancellable {
    /*

    @author: RETAMROVEC
    @version: 1.0

     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final ItemStack itemStack;
    private boolean cancelled;

    public PlayerEatHeartEvent(Player player, ItemStack itemStack) {
        cancelled = false;
        this.player = player;
        this.itemStack = itemStack;
    }

    public Player getPlayer(){return player;}
    public ItemStack getItem(){return itemStack;}
    public boolean isCancelled(){return cancelled;}

    @Override
    public void setCancelled(boolean b){this.cancelled = b;}

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
