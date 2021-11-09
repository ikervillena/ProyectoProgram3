package dataLogic.league;

import dbManagement.DataExtraction;
import dataLogic.people.Manager;
import dataLogic.people.Player;
import org.junit.Test;

import java.util.ArrayList;

public class TeamTest {


    @Test
    public void generatePlayersList(){
        Manager manager = new Manager("ikervillena","ville","Iker","Villena");
        ArrayList<Player> playersList = new Team(manager).getPlayersList();
        for(Player p : playersList){
            System.out.println(p.toString());
        }
    }

    @Test
    public void randomNumber(){
        for(int i = 0; i < 15; i++){
            System.out.println("" + (int) (Math.random()*DataExtraction.getAllPlayers().size()));
        }

    }

}
