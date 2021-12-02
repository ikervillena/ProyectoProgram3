package test.businessLogic;

import main.businessLogic.Algorithm;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.fail;

public class AlgorithmTest {

    private ArrayList<Player> playersList;

    @Before
    public void setUp(){
        playersList = DataExtraction.getAllPlayers();
    }

    @Test
    public void quickSortPlayers(){
        setUp();
        Algorithm.quickSortPlayers(playersList,0,playersList.size()-1);
        int prevPositionIndex = 0;
        for(Player p : playersList){
            if(p.getPosition().getIndex()<prevPositionIndex){
                fail("The list of players is not properly ordered.");
            }
            prevPositionIndex = p.getPosition().getIndex();

        }
    }

}
