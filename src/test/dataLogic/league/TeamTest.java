package test.dataLogic.league;

import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dbManagement.DataExtraction;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TeamTest {

    /**Tests the generateID() method.
     * For that: it checks that the ID number generated does not belong to any league.
     */

    @Test
    public void generateID(){
        boolean validID = true;
        int newID = Team.generateID();
        for(League l : DataExtraction.getAllLeagues()){
            for(Team t : l.getTeamsList()){
                if(t.getID() == newID){
                    fail("The ID number generated belongs to an existing team.");
                }
            }
        }
    }
}
