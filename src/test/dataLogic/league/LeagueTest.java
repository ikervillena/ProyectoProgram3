package test.dataLogic.league;

import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/** This class has the aim of testing the methods implemented in the class "League.java".
 * @author Iker Villena Ona.
 */

public class LeagueTest {

    private League league;
    private Team team;
    private Manager manager;

    /** Initializes the league and the team that forms the league, for running tests of the methods.
     */

    @Before
    public void setUp(){
        manager = new Manager("ikervillena","ville","Iker","Villena");
        team = new Team(manager);
        ArrayList<Team> teamsList = new ArrayList<Team>();
        teamsList.add(team);
        league = new League(teamsList);
    }

    /**Tests the getID() method.
     * For that: it checks that the ID number that it provides for any league is the one with which it is regiestered in the Dataase.
     */

    @Test
    public void getID(){
        for(Manager m : DataExtraction.getAllManagers()){
            for(League l : m.getLeagues()){
                assertEquals("The ID does NOT belong to the league.",l.getID(),DataExtraction.getLeagueID(l.getEntryCode()));
            }
        }
    }

    /**Tests the generateID() method.
     * For that: it checks that the ID number generated does not belong to any league.
     */

    @Test
    public void generateID(){
        boolean validID = true;
        int newID = League.generateID();
        for(League l : DataExtraction.getAllLeagues()){
            if(l.getID() == newID){
                validID = false;
            }
        }
        assertTrue("The generated ID is NOT valid", validID);
    }

    /**Tests the canAccess(Manager) method.
     * For that: it checks (with every league) that it returns true when the manager provided as a parameter participates the league
     * and false if he does not.
     */

    @Test
    public void canAccess(){
        Manager newManager = new Manager("mikelperez","123","Mikel","Pérez");
        assertTrue("A manager of the league does not have access to it.",league.canAccess(manager));
        assertFalse("A manager who does not participate in the league has access to it",league.canAccess(newManager));
    }

    /**Tests the getFreePlayers() method.
     * For that: it checks that none of the players contained in the ArrayList returned play for any of the teams that participate in the league.
     */

    @Test
    public void getFreePlayers(){
        for(League l : DataExtraction.getAllLeagues()){
            ArrayList<Player> freePlayers = l.getFreePlayers();
            for(Team t : l.getTeamsList()){
                for(Player teamPlayer : t.getPlayersList()){
                    for(Player freePlayer : freePlayers){
                        if(teamPlayer.getID() == freePlayer.getID()){
                            fail("Player who belongs to a team is included in the free players list.");
                        }
                    }
                }
            }
        }
    }

    /** Verifies the addTeam() method.
     * For that: checks the number of teams of the league before and after adding a team.
     */

    @Test
    public void addTeam(){
        setUp();
        assertEquals(1,league.getTeamsList().size());
        Team newTeam = new Team(new Manager("ikervillena","ville","Iker","Villena"));
        league.addTeam(newTeam);
        assertEquals(2,league.getTeamsList().size());
    }

    /** Verifies the removeTeam() method.
     * For that: checks the number of teams of the league before and after removing a team.
     */

    @Test
    public void removeTeam(){
        setUp();
        assertEquals(1,league.getTeamsList().size());
        league.removeTeam(team);
        assertEquals(0,league.getTeamsList().size());
    }

    /**Tests the getTeam(Manager) method.
     */

    @Test
    public void getTeam(){
        setUp();
        Manager newManager = new Manager("username","password","name","surname");
        assertEquals(team,league.getTeam(manager));
        assertTrue(league.getTeam(newManager) == null);
    }

}
