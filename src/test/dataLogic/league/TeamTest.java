package test.dataLogic.league;

import main.businessLogic.Statistic;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TeamTest {

    private Team team;
    private Player player1;
    private Player player2;

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

    @Before
    public void setUp(){
        ArrayList<Statistic> statsRecord = new ArrayList<>();
        statsRecord.add(new Statistic(true,0,2,3,1,false));
        statsRecord.add(new Statistic(true,0,0,0,0,true));
        ArrayList<Player> playersList = new ArrayList<>();
        player1 = new Player("a","b","ab",10, DataExtraction.getPositions().get(0),null, statsRecord);
        player2 = new Player("b","a","ba",2,DataExtraction.getPositions().get(1), null, statsRecord);
        playersList.add(player1);
        playersList.add(player2);
        ArrayList<Squad> squadsList = new ArrayList<>();
        squadsList.add(new Squad(1, DataExtraction.getAllFormations().get(0),playersList));
        squadsList.add(new Squad(2,DataExtraction.getAllFormations().get(1),playersList));
        team = new Team(0,null,null,squadsList);
    }

    /**Tests the getPoints(int) method.
     */

    @Test
    public void getPoints(){
        setUp();
        int pointsRound1 = player1.getPoints(1)+ player2.getPoints(1);
        assertEquals(pointsRound1,team.getPoints(1));
        int pointsRound2 = player1.getPoints(2)+ player2.getPoints(2);
        assertEquals(pointsRound2,team.getPoints(2));
    }

    /**Tests the getTotalPoints() method.
     */

    @Test
    public void getTotalPoints(){
        setUp();
        int totalPoints = totalPoints = player1.getPoints(1)+player1.getPoints(2)+player2.getPoints(1)+player2.getPoints(2);
        assertEquals(totalPoints,team.getTotalPoints());
    }

}
