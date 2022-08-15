package retamrovec.finesoftware.lifesteal.Listeners;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CommandUseEvent2 extends Event implements Cancellable {
    /*

    CUSTOM API EVENT STILL IN PROGRESS.

     */

    private static final HandlerList HANDLERS = new HandlerList();
    private final CommandSender sender;
    private final String[] args;
    private boolean cancelled;

    public CommandUseEvent2(CommandSender sender, String[] args) {
        cancelled = false;
        this.sender = sender;
        this.args = args;
    }

    public CommandSender getSender(){return sender;}
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
