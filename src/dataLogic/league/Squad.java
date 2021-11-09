package dataLogic.league;

import dataLogic.people.Player;

import java.util.ArrayList;

/** Represents a squad formed by players of different positions.
 * @author Iker Villena Ona.
 */

public class Squad {

    Player goalkeeper;
    ArrayList<Player> defenders;
    ArrayList<Player> midfielders;
    ArrayList<Player> forwards;

    /**
     * Constructor of a Squad.
     * @param goalkeeper
     * @param defenders
     * @param midfielders
     * @param forwards
     */

    public Squad(Player goalkeeper, ArrayList<Player> defenders, ArrayList<Player> midfielders, ArrayList<Player> forwards) {
        this.goalkeeper = goalkeeper;
        this.defenders = defenders;
        this.midfielders = midfielders;
        this.forwards = forwards;
    }

    /**
     * Indicates if an allignment is proper or not.
     * To be proper, the formation must coincide with one of the preestablished ones and there must be a goalkeeper.
     * @return a boolean showing if the allignment is correct (true) or not (false).
     */

    public boolean properAlignment() {
        boolean correctFormation = false;
        int[][] acceptedFormations = {{3,4,3},{3,5,2},{4,3,3},{4,4,2},{4,5,1},{5,4,1},{5,3,2}};
        for(int[] f : acceptedFormations){
            if(f[0] == defenders.size() && f[1] == midfielders.size() && f[2] == forwards.size()){
                correctFormation = true;
                break;
            }
        }
        if(goalkeeper != null && correctFormation){
            return true;
        }else{
            return false;
        }
    }

    public static Squad createSquad(ArrayList<Player> playersList){
        Player goalkeeper = null;
        ArrayList<Player> defenders = new ArrayList<>();
        ArrayList<Player> midfielders = new ArrayList<>();
        ArrayList<Player> forwards = new ArrayList<>();
        for(Player p : playersList){
            switch (p.getPosition().getName()){
                case "Goalkeeper":
                    goalkeeper = p;
                    break;
                case "Defense":
                    defenders.add(p);
                    break;
                case "Midfielder":
                    midfielders.add(p);
                    break;
                case "Forward":
                    forwards.add(p);
                    break;
            }
        }
        return (new Squad(goalkeeper,defenders,midfielders,forwards));
    }

    public Player getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(Player goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public ArrayList<Player> getDefenders() {
        return defenders;
    }

    public void setDefenders(ArrayList<Player> defenders) {
        this.defenders = defenders;
    }

    public ArrayList<Player> getMidfielders() {
        return midfielders;
    }

    public void setMidfielders(ArrayList<Player> midfielders) {
        this.midfielders = midfielders;
    }

    public ArrayList<Player> getForwards() {
        return forwards;
    }

    public void setForwards(ArrayList<Player> forwards) {
        this.forwards = forwards;
    }
}


