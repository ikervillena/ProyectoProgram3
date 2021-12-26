package test.businessLogic;

import main.businessLogic.QuickSort;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

public class QuickSortTest {

    private ArrayList<Player> playersList;

    @Before
    public void setUp(){
        playersList = DataExtraction.getAllPlayers();
    }

    @Test
    public void sort(){
        setUp();
        new QuickSort<Player>().sort(playersList,0, playersList.size()-1);
        for(int i = 0; i < playersList.size()-1; i++){
            assertTrue(playersList.get(i).getPosition().getIndex()<=
                    playersList.get(i+1).getPosition().getIndex());
            System.out.println(playersList.get(i).getPosition().getName());
        }
    }

}
