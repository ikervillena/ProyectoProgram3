package main.dbManagement;

import main.businessLogic.Statistic;
import main.dataLogic.league.League;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/** This class contains methods needed to insert data into the Database.
 * @author Iker Villena Ona
 */

public class DataInsertion {

    /**Inserts all tha data of a league in their corresponding Database tables.
     * @param league the League that needs to be saved to the Database.
     */

    public static void newLeague(League league){
        insertLeague(league.getName(),league.getEntryCode());
        for(Team t : league.getTeamsList()){
            insertTeam(t, league);
        }
    }

    /**Inserts a league in the Database.
     * @param name the league's name.
     * @param entryCode the league's entry code.
     */

    public static void insertLeague(String name, String entryCode) {
        String sql = "INSERT INTO league(league_id,season_id,name,entrycode) VALUES(?,?,?,?)";

        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, League.generateID());
            pstmt.setInt(2, DataExtraction.getCurrentSeason());
            pstmt.setString(3,name);
            pstmt.setString(4,entryCode);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Inserts a team in de Database.
     * @param team the team that needs to be saved to the Database.
     * @param league the league in which the team is participating.
     */

    public static void insertTeam(Team team, League league){
        String username = team.getManager().getUsername();
        int leagueID = league.getID();
        String sql = "INSERT INTO team(team_id,league_id,username) VALUES(?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, Team.generateID());
            pstmt.setInt(2,leagueID);
            pstmt.setString(3,username);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertPlayFor(team);
    }

    /**Inserts the relationship between players and a team in the "playfor" table of the Database.
     * @param team the Team in which players play.
     */

    private static void insertPlayFor(Team team){
        for(Player player : team.getPlayersList()){
            String sql = "INSERT INTO playfor(player_id, team_id) VALUES(?,?)";

            try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, player.getID());
                pstmt.setInt(2,team.getID());
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**Saves the statistics of a player in the Database.
     * @param statistic A Statistic of a player in a specific match.
     * @param playerID Integer with the player's ID number.
     */

    public static void insertStatistic(Statistic statistic, int playerID) {
        String sql = "INSERT INTO statistic(round_num, player_id, played, goals, assists, goalsagainst, yellowcards, redcard) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,1);
            pstmt.setInt(2, playerID);
            pstmt.setBoolean(3,statistic.isPlayed());
            pstmt.setInt(4,statistic.getNumGoals());
            pstmt.setInt(5,statistic.getNumAssists());
            pstmt.setInt(6,statistic.getReceivedGoals());
            pstmt.setInt(7,statistic.getYellowCards());
            pstmt.setBoolean(8,statistic.isRedCard());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Saves the alignments of players in a Squad to the Dataabase.
     * @param team Team in which players are aligned.
     * @param playersList ArrayList with the list of players aligned.
     */
    private static void insertAlignment(Team team, ArrayList<Player> playersList){
        for (Player p : playersList){
            String sql = "INSERT INTO alignment(player_id, team_id, round_num) VALUES(?,?,?)";
            try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1,p.getID());
                pstmt.setInt(2,team.getID());
                pstmt.setInt(3,DataExtraction.getNextRound());
                pstmt.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**Saves a team's Squad to the Database (tables "squad" and "alignment").
     * @param team Team in which players are aligned.
     * @param squad Squad that is saved to the Database.
     */

    public static void insertSquad(Team team, Squad squad){
        String sql = "INSERT INTO squad(team_id,round_num,formation_id) VALUES(?,?,?)";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,team.getID());
            pstmt.setInt(2,DataExtraction.getNextRound());
            pstmt.setInt(3,squad.getFormation().getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertAlignment(team, squad.getPlayersList());
    }

}
