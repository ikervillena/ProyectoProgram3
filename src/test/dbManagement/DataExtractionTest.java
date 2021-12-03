package test.dbManagement;

import main.businessLogic.Statistic;
import main.businessLogic.TacticalFormation;
import main.dataLogic.league.Club;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Administrator;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import main.dataLogic.people.attributes.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "DataExtractor.java".
 * @author Iker Villena Ona.
 */

public class DataExtractionTest {

    /** Verifies the getPositions() method.
     * For that: extracts the list of positions saved in the DB, and checks that the ArrayList has all of them (4).
     */

    @Test
    public void getPositions(){
        ArrayList<Position> positionsList = DataExtraction.getPositions();
        Assert.assertEquals(4,positionsList.size());
    }

    @Test
    public void getMatches(){
        String[][] a = DataExtraction.getMatches(1);
        for(int i = 0; i < a.length;i++){
            System.out.println(a[i][0] + " - " + a[i][1]);
        }
    }

    @Test
    public void getAllClubs(){
        for(Club c : DataExtraction.getAllClubs()){
            for(Player p : c.getPlayersList()){
                System.out.println(p.getShirtName());
            }
        }
    }

    @Test
    public void playerStatsSaved(){
        System.out.println(DataExtraction.playerStatsSaved(0,DataExtraction.getAllPlayers().get(0).getID()));
        System.out.println(DataExtraction.playerStatsSaved(1,DataExtraction.getAllPlayers().get(0).getID()));
        System.out.println(DataExtraction.playerStatsSaved(2,DataExtraction.getAllPlayers().get(0).getID()));
    }

    @Test
    public void unsavedMatches(){
        System.out.println(DataExtraction.numUnsavedMatches(0));
        System.out.println(DataExtraction.numUnsavedMatches(1));
    }

    @Test
    public void getMatchesList(){
        for(int i = 0; i < DataExtraction.getMatches(1).length;i++){
            System.out.println(DataExtraction.getMatches(1)[i][0]+" - "+DataExtraction.getMatches(1)[i][1]);
        }
    }

    @Test
    public void imprimirStats(){
        for(Player p : DataExtraction.getClubPlayers("Real Sociedad")){
            for(Statistic s : p.getStatsRecord()){
                System.out.println(p.getShirtName()+": "+s.getPoints(p.getPosition())+" points.");
            }
        }
    }

    @Test
    public void getAllPlayers(){
        int a = 1;
        for(Player p : DataExtraction.getAllPlayers()){
            System.out.println(a+": "+p.getShirtName()+" "+p.getStatsRecord().get(0).getPoints(p.getPosition())+" points.");
            a++;
        }
    }

    @Test
    public void getPointsRecord(){
        for(Player p : DataExtraction.getClubPlayers("Real Sociedad")){
            System.out.println(p.getShirtName());
            for(int i = 0; i < p.getPointsRecord().size(); i++){
                System.out.println("Round "+i+1+": "+p.getPointsRecord().get(i));
            }
        }
    }

    @Test
    public void getFormationsList(){
        for(TacticalFormation t : DataExtraction.getAllFormations()){
            System.out.println(t+" - "+t.getID());
        }
    }

    @Test
    public void lineUp(){
        for(Player p : DataExtraction.getTeams(0).get(1).getSquadRecord().get(0).getPlayersList()){
            System.out.println(p);
        }

    }

    @Test
    public void league(){
        for(League l : DataExtraction.getAllLeagues()){
            System.out.println("League ID: "+l.getID());
            for(Team t : l.getTeamsList()){
                System.out.println("Team ID: "+t.getID()+". Squad record size: "+t.getSquadRecord().size());
            }
        }
    }

}
