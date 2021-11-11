package dbManagement;

import dataLogic.league.League;
import dataLogic.league.Team;
import dataLogic.people.Manager;
import dataLogic.people.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/** This class contains methods needed to insert data into the Database.
 * @author Iker Villena Ona
 */

public class DataInsertion {

    /**Inserts all tha data of a league in their corresponding Database tables.
     * @param league
     */

    public static void newLeague(League league){
        insertLeague(league.getName(),league.getEntryCode());
        for(Team t : league.getTeamsList()){
            insertTeam(t, league);
        }
    }

    /**Inserts a league in the Database.
     * @param name
     * @param entryCode
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
     * @param team
     * @param league
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
     * @param team
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

}
