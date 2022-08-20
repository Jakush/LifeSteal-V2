package retamrovec.finesoftware.lifesteal.Events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerEatHeartEvent extends Event implements Cancellable {
    /*

    @author: RETAMROVEC
    @version: 1.0

     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private boolean cancelled;

    public PlayerEatHeartEvent(Player player) {
        cancelled = false;
        this.player = player;
    }

    @Deprecated
    public @Nullable Player getPlayer(){
        return player;
    }
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
