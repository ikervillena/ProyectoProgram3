package test.dataLogic.league;

import main.businessLogic.Statistic;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TeamTest {

    private Team team;
    private Player player1;
    private Player player2;

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
        team = new Team(0,100,null,playersList,squadsList);
    }

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

    /**Tests the methods aimed at getting a list of players that play in a certain position.
     */

    @Test
    public void getPlayers(){
        setUp();
        assertEquals(1, team.getGoalkeepers().size());
        assertEquals(player1, team.getGoalkeepers().get(0));
        assertEquals(1, team.getDefenders().size());
        assertEquals(player2, team.getDefenders().get(0));
        assertEquals(0, team.getMidfielders().size());
        assertEquals(0, team.getForwards().size());
    }

    /**Tests the generatePlayersList(ArrayList<Player>) method.
     */

    @Test
    public void generatePlayersList(){
        ArrayList<Player> playersList = Team.generatePlayersList(DataExtraction.getAllPlayers());
        assertEquals(15,playersList.size());
        assertTrue(1 == playersList
                .stream()
                .filter(player -> player.getPosition().getName().equals("Goalkeeper"))
                .count());
        assertTrue(5 == playersList
                .stream()
                .filter(player -> player.getPosition().getName().equals("Defense"))
                .count());
        assertTrue(5 == playersList
                .stream()
                .filter(player -> player.getPosition().getName().equals("Midfielder"))
                .count());
        assertTrue(4 == playersList
                .stream()
                .filter(player -> player.getPosition().getName().equals("Forward"))
                .count());
    }

}
