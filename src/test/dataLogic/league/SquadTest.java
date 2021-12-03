package test.dataLogic.league;

import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** This class has the aim of testing the methods implemented in the class "Squad.java".
 * @author Iker Villena Ona.
 */

public class SquadTest {

    /**Tests different methods aimed at providing a list of players that play in a specific position.
     */

    @Test
    public void getPositionPlayers(){
        for(League l : DataExtraction.getAllLeagues()){
            for(Team t : l.getTeamsList()){
                for(Squad s : t.getSquadRecord()){
                    for(Player p : s.getForwards()){
                        assertEquals(p.getPosition().getName(),"Forward");
                    }
                    for(Player p : s.getMidfielders()){
                        assertEquals(p.getPosition().getName(),"Midfielder");
                    }
                    for(Player p : s.getDefenders()){
                        assertEquals(p.getPosition().getName(),"Defense");
                    }
                }
            }
        }
    }
    
}
