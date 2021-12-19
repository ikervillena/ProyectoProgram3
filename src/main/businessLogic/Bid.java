package main.businessLogic;

import main.businessLogic.interfaces.IComparable;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataDeletion;
import main.dbManagement.DataInsertion;
import main.dbManagement.DataUpdate;

public class Bid implements IComparable<Bid>{

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
            DataUpdate.updatePlayFor(this.player,this.currentTeam,this.interestedTeam);
        } else{
            DataInsertion.insertPlayFor(this.player,this.interestedTeam);
        }
        interestedTeam.reduceMoney(fee);
        DataDeletion.deleteBid(this);
    }

    /**Deletes the bid from the DataBase.
     */

    public void delete(){
        DataDeletion.deleteBid(this);
    }

    @Override
    public String toString() {
        return fee+" millones ofrecidos por "+player.getShirtName()+" por "+interestedTeam.getManager().getUsername();
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

    /**Compares a bid to another.
     * @param object Object that needs to be compared.
     * @return -1 if his bid's fee is higher, 0 if it is equal and 1 if it is smaller.
     */

    @Override
    public int compareTo(Bid object) {
        if(this.getFee()>object.getFee()){
            return -1;
        } else{
            if(this.getFee()==object.getFee()){
                return 0;
            } else{
                return 1;
            }
        }
    }
}
