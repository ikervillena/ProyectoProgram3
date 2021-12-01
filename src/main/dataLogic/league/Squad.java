package main.dataLogic.league;

import main.businessLogic.TacticalFormation;
import main.businessLogic.interfaces.IPositionClassification;
import main.dataLogic.people.Player;
import java.util.ArrayList;

/** Represents a squad formed by players of different positions.
 * @author Iker Villena Ona.
 */

public class Squad implements IPositionClassification {

    int roundNum;
    TacticalFormation formation;
    ArrayList<Player> playersList;

    /**Constructor of Squad.
     * @param formation TacticalFormation in which players are aligned.
     * @param playersList An ArrayList<Player> with the list of players aligned.
     */

    /**Constructor of Squad.
     * @param roundNum Integer with the squad's round number.
     * @param formation TacticalFormation in which players are aligned.
     * @param playersList An ArrayList<Player> with the list of players aligned.
     */

    public Squad(int roundNum, TacticalFormation formation, ArrayList<Player> playersList) {
        this.roundNum = roundNum;
        this.formation = formation;
        this.playersList = playersList;
    }

    /**
     * Indicates if an alignment is proper or not.
     * To be proper, the tactical formation selected must coincide with the number of players that play in each position.
     * @return a boolean showing if the alignment is correct (true) or not (false).
     */

    public boolean properAlignment() {
        if(getDefenders().size() == formation.getNumDefenders() || getMidfielders().size() == formation.getNumMidfielders()
        || getForwards().size() == formation.getNumForwards() || getGoalkeeper() != null){
            return true;
        } else{
            return false;
        }
    }

    /**This method is used for getting a list of players that play in a specific position out of the Squad.
     * @param positionName String with the position's name.
     * @return ArrayList<Player> with the list of players that play in the provided position.
     */

    private ArrayList<Player> getPlayers(String positionName){
        ArrayList<Player> list = new ArrayList<>();
        for(Player p : playersList){
            if(p.getPosition().getName().equals(positionName)){
                list.add(p);
            }
        }
        return list;
    }

    /**
     * @return Player aligned in the Squad who plays in the "Goalkeeper" position.
     */

    public Player getGoalkeeper(){
        Player goalkeeper = null;
        for(Player p : playersList){
            if(p.getPosition().getName().equals("Goalkeeper")){
                goalkeeper = p;
                break;
            }
        }
        return goalkeeper;
    }

    /**
     * @return ArrayList<Player> with the list of players from the Squad that play in the "Defense" position.
     */

    @Override
    public ArrayList<Player> getDefenders(){
        return getPlayers("Defense");
    }

    /**
     * @return ArrayList<Player> with the list of players from the Squad that play in the "Midfielder" position.
     */

    @Override
    public ArrayList<Player> getMidfielders(){
        return getPlayers("Midfielder");
    }

    /**
     * @return ArrayList<Player> with the list of players from the Squad that play in the "Forward" position.
     */

    @Override
    public ArrayList<Player> getForwards(){
        return getPlayers("Forwards");
    }

    public int getRoundNum() {
        return roundNum;
    }

    public TacticalFormation getFormation() {
        return formation;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
}


