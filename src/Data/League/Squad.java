package Data.League;

import Data.People.Player;

import java.util.ArrayList;

/** Represents a squad formed by players of different positions
 * @author Iker Villena Ona
 */

public class Squad {

    Player goalkeeper;
    ArrayList<Player> defenders;
    ArrayList<Player> midfielders;
    ArrayList<Player> forwards;

    private boolean properAllignment() {
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

}


