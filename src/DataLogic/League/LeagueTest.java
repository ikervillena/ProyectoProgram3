package DataLogic.League;

import DataLogic.People.Manager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "League.java".
 * @author Iker Villena Ona.
 */

public class LeagueTest {

    private League league;
    private Team team;

    /** Initializes the league and the team that forms the league, for running tests of the methods.
     */

    @Before
    public void setUp(){
        team = new Team(new Manager("ikervillena","ville","Iker","Villena"));
        ArrayList<Team> teamsList = new ArrayList<Team>();
        teamsList.add(team);
        league = new League(teamsList);
    }

    /** Verifies the addTeam() method.
     * For that: checks the number of teams of the league before and after adding a team.
     */

    @Test
    public void addTeam(){
        setUp();
        Assert.assertEquals(1,league.getTeamsList().size());
        Team newTeam = new Team(new Manager("ikervillena","ville","Iker","Villena"));
        league.addTeam(newTeam);
        Assert.assertEquals(2,league.getTeamsList().size());
    }

    /** Verifies the removeTeam() method.
     * For that: checks the number of teams of the league before and after removing a team.
     */

    @Test
    public void removeTeam(){
        setUp();
        Assert.assertEquals(1,league.getTeamsList().size());
        league.removeTeam(team);
        Assert.assertEquals(0,league.getTeamsList().size());
    }
}
