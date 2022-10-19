package retamrovec.finesoftware.lifesteal.Storage;

import org.bukkit.Location;

public class Hologram {

    private String indentifier;
    private Location location;
    private boolean beacon;
    public Hologram(String indentifier, Location location) {
        this.indentifier = indentifier;
        this.location = location;


    }

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
