package retamrovec.finesoftware.lifesteal.Storage;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import retamrovec.finesoftware.lifesteal.Runnables.HologramRunnable;
import retamrovec.finesoftware.lifesteal.Runnables.ReviveRunnable;

public class Hologram {

    private String indentifier;
    private final Location location;
    private boolean beacon;
    private final HologramRunnable hr;
    private final ReviveRunnable rr;
    public Hologram(String indentifier, Location location, @NotNull Block block) {
        this.indentifier = indentifier;
        this.location = location;

        this.rr = new ReviveRunnable(block.getLocation(), this);
        this.hr = new HologramRunnable(indentifier, block, rr, this);
    }

    public void cancelRunnables() {
        hr.stop();
        rr.stop();
    }

    public void cancelHologramRunnable() {
        hr.cancel();
    }
    public HologramRunnable getHologramRunnable() {
        return hr;
    }
    public ReviveRunnable getReviveRunnable() { return rr; }

    public String getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(String id) {
        this.indentifier = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setBoolean(Boolean bol) {
        this.beacon = bol;
    }

    public boolean hasBeacon(){
        return beacon;
    }
}
