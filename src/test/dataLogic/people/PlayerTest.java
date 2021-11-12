package test.dataLogic.people;

import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Test;

import static org.junit.Assert.fail;

public class PlayerTest {

    @Test
    public void getID(){
        for(Player p : DataExtraction.getAllPlayers()){
            if(!p.getShirtName().equals(DataExtraction.getPlayer(p.getID()).getShirtName())){
                fail("The ID provided does not belong to the player.");
            }
        }
    }

}
