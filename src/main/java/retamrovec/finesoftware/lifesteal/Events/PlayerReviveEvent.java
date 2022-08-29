package retamrovec.finesoftware.lifesteal.Events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerReviveEvent extends Event implements Cancellable {

    /*

    @author: RETAMROVEC
    @version: 1.0

     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final OfflinePlayer player;
    private String message;
    private boolean cancelled;

    public PlayerReviveEvent(OfflinePlayer player, String message) {
        cancelled = false;
        this.player = player;
        this.message = message;
    }

    public OfflinePlayer getPlayer(){return player;}
    @Override
    public boolean isCancelled(){return cancelled;}
    @Override
    public void setCancelled(boolean b){this.cancelled = b;}
    @Override
    public @NotNull HandlerList getHandlers(){return HANDLERS;}
    public static HandlerList getHandlerList(){return HANDLERS;}
}
