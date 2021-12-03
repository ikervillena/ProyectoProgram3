package test.dataLogic.people;

import main.businessLogic.Statistic;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player1;
    private Player player2;

    /**Initializes variables player1 and player2 with statistics regarding 2 different rounds.
     */

    @Before
    public void setUp(){
        ArrayList<Statistic> statsRecord = new ArrayList<>();
        statsRecord.add(new Statistic(true,0,2,3,1,false));
        statsRecord.add(new Statistic(true,0,0,0,0,true));
        ArrayList<Player> playersList = new ArrayList<>();
        player1 = new Player("a","b","ab",10, DataExtraction.getPositions().get(0),null, statsRecord);
        player2 = new Player("b","a","ba",2,DataExtraction.getPositions().get(1), null, statsRecord);
    }

    /**Tests the getID() method.
     */

    @Test
    public void getID(){
        for(Player p : DataExtraction.getAllPlayers()){
            if(!p.getShirtName().equals(DataExtraction.getPlayer(p.getID()).getShirtName())){
                fail("The ID provided does not belong to the player.");
            }
        }
    }

    /**Tests the getPointsRecord() method.
     * For that: it checks that the number of elements in de list of points goes along with the number of statistics of the player,
     * and that points provided for each statistic are the ones expected.
     */

    @Test
    public void getPointsRecord(){
        setUp();
        assertEquals("The number of statistics does not match the number of punctuations",2,player1.getPointsRecord().size());
        assertEquals("The number of statistics does not match the number of punctuations",2,player2.getPointsRecord().size());
        assertTrue(player1.getPointsRecord().get(0) == 5);
        assertTrue(player1.getPointsRecord().get(1) == 6);
        assertTrue(player2.getPointsRecord().get(0) == 6);
        assertTrue(player2.getPointsRecord().get(1) == 4);
    }

    /**Tests the getPoints(int roundNum) method:
     * For that: checks that the points provided for every player in each round match the expectations.
     */

    @Test
    public void getPoints(){
        setUp();
        assertTrue(player1.getPoints(1) == 5);
        assertTrue(player1.getPoints(2) == 6);
        assertTrue(player2.getPoints(1) == 6);
        assertTrue(player2.getPoints(2) == 4);
    }

    /**Tests the equals() method.
     */

    @Test
    public void equals(){
        ArrayList<Player> p1 = DataExtraction.getAllPlayers();
        ArrayList<Player> p2 = DataExtraction.getAllPlayers();
        for(int i = 0 ; i < p1.size(); i++){
            assertEquals(p1.get(i),p2.get(i));
            if(i<p1.size()-1){
                assertNotEquals(p1.get(i),p2.get(i+1));
            }
        }
    }

}
