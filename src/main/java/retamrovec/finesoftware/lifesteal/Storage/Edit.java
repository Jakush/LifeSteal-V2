package retamrovec.finesoftware.lifesteal.Storage;

import org.bukkit.entity.Player;

public class Edit {

    private static boolean status = false;
    private static Player player;
    public Edit(Player player){
        Edit.player = player;
    }

    public static boolean getStatus(){return status;}

    public static void setStatus(boolean status){
        Edit.status = status;}

    public static Player getPlayer(){return player;}
}
