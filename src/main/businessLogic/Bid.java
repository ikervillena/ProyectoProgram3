package main.businessLogic;

import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataDeletion;
import main.dbManagement.DataInsertion;
import main.dbManagement.DataUpdate;

public class Bid {

    Team interestedTeam;
    Team currentTeam;
    Player player;
    float fee;

    public Bid(Team interestedTeam, Team currentTeam, Player player, float fee) {
        this.interestedTeam = interestedTeam;
        this.currentTeam = currentTeam;
        this.player = player;
        this.fee = fee;
    }

    /**Saves the bid to the DataBase.
     */

    public void save(){
        DataInsertion.insertBid(this);
    }

    /**Accepts a bid, and saves the result to the Database. For that:
     * Gives to the old Team the money offered (if the Player belonged to a Team).
     * Extracts the money offered from the Player's new Team.
     * Moves the Player from the old Team to the new one.
     * Deletes the bid.
     */

    public void accept(){
        if(currentTeam != null){
            currentTeam.increaseMoney(fee);
        }
        interestedTeam.reduceMoney(fee);
        DataUpdate.updatePlayFor(this.player,this.currentTeam,this.interestedTeam);
        DataDeletion.deleteBid(this);
    }

    /**Rejects the bid, deleting it from the DataBase.
     */

    public void reject(){
        DataDeletion.deleteBid(this);
    }

    @Override
    public String toString() {
        return fee+"millions offered for "+player.getShirtName()+" by "+interestedTeam.getManager().getUsername();
    }

    public Team getInterestedTeam() {
        return interestedTeam;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public Player getPlayer() {
        return player;
    }

    public float getFee() {
        return fee;
    }
}
