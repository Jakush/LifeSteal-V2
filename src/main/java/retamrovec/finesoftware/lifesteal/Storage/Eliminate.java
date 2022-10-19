package retamrovec.finesoftware.lifesteal.Storage;

import retamrovec.finesoftware.lifesteal.LifeSteal;

public class Eliminate {

    private final String eliminated;
    private boolean status;
    public Eliminate(String eliminated) {
        this.eliminated = eliminated;
    }

    public String getEliminated() {
        return eliminated;
    }

    public boolean getStatus(){return status;}

    public void setStatus(boolean status){
        this.status = status;
        if (status) LifeSteal.getInstance().getEliminatedPlayers().add(eliminated);
        if (!status) LifeSteal.getInstance().getEliminatedPlayers().remove(eliminated);
    }

}
