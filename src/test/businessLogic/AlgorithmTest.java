package test.businessLogic;

import main.businessLogic.Algorithm;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

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

    @Test
    public void a(){
        float[] a = {1,2,3*4,5};
        float[] b = Arrays.copyOfRange(a,0,2-1);
        for(int i = 0; i<b.length; i++){
            System.out.println(b[i]);
        }

    }

    @Test
    public void getClubPlayers(){
        for(Player p : DataExtraction.getClubPlayers("Real Sociedad")){
            System.out.println(p.getShirtName());
            for(int i = 0; i < p.getValueHistory().length; i++){
                System.out.println("Jornada "+i+": "+p.getValueHistory()[i]);
            }
        }
    }
}
