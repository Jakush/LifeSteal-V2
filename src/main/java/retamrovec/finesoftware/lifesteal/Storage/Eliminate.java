package retamrovec.finesoftware.lifesteal.Storage;

public class Eliminate {

    private static String eliminated;
    private static boolean status;
    public Eliminate(String eliminated) {
        Eliminate.eliminated = eliminated;
    }

    public static String getEliminated() {
        return eliminated;
    }

    public static boolean getStatus(){return status;}

    public static void setStatus(boolean status){
        Eliminate.status = status;
    }

}
