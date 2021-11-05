package DataLogic.League;

import DataLogic.People.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "Squad.java".
 * @author Iker Villena Ona.
 */

public class SquadTest {

    private Squad squad;

    /** Initializes the squad with a goalkeeper, 4  defenders, 3 midfielders and 3 forwards.
     */
    
    @Before
    public void setUp(){
        ArrayList<Player> defenders = new ArrayList<>();
        defenders.add(new Player());
        ArrayList<Player> midfielders = new ArrayList<>();
        ArrayList<Player> forwards= new ArrayList<>();
        for(int i = 0; i < 3; i++){
            defenders.add(new Player());
            midfielders.add(new Player());
            forwards.add(new Player());
        }
        squad = new Squad(new Player(),defenders,midfielders,forwards);
    }

    /** Verifies the properAlignment() method.
     * For that, it checks the return of the method regarding the next alignments:
     * 1-4-3-3 (true).
     * No goalkeeper (false).
     * 1-5-3-3 (false).
     * 1-5-3-2 (true).
     */

    @Test
    public void properAlignment(){
        setUp();
        Assert.assertTrue(squad.properAlignment());
        squad.setGoalkeeper(null);
        Assert.assertFalse(squad.properAlignment());
        setUp();
        ArrayList<Player> defenders = squad.getDefenders();
        defenders.add(new Player());
        squad.setDefenders(defenders);
        Assert.assertFalse(squad.properAlignment());
        ArrayList<Player> forwards = squad.getForwards();
        forwards.remove(2);
        squad.setForwards(forwards);
        Assert.assertTrue(squad.properAlignment());
    }
    
}
