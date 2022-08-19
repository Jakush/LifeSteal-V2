package retamrovec.finesoftware.lifesteal.Events;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CommandUseEvent extends Event implements Cancellable {
    /*

    @author: RETAMROVEC
    @version: 1.0

     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final CommandSender sender;
    private final String[] args;
    private boolean cancelled;

    public CommandUseEvent(CommandSender sender, String[] args) {
        cancelled = false;
        this.sender = sender;
        this.args = args;
    }

    @Deprecated
    public CommandSender getSender(){return sender;}
    public @Nullable Player getPlayer(){
        return (Player) sender;
    }
    public String[] getArgs(){return args;}
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
