package DBManagement;

import DataLogic.People.Attributes.Position;
import DataLogic.People.Player;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "DataExtractor.java".
 * @author Iker Villena Ona.
 */

public class DataExtractorTest {

    /** Verifies the getPositions() method.
     * For that: extracts the list of positions saved in the DB, and checks that the ArrayList has all of them (4).
     */

    @Test
    public void getPositions(){
        ArrayList<Position> positionsList = DataExtractor.getPositions();
        Assert.assertEquals(4,positionsList.size());
    }

    @Test
    public void getAllPlayers(){
        ArrayList<Player> playersList = DataExtractor.getAllPlayers();
        for(Player p : playersList){
            System.out.println(p);
        }
    }

}
