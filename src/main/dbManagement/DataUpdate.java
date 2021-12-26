package main.dbManagement;

import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This class contains methods needed to update data from the Database.
 * @author Iker Villena Ona
 */

public class DataUpdate {

    public static void setBudget(Team team, float newBudget){
        String sql = "UPDATE team SET budget = ? WHERE team_id = ?";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setFloat(1,newBudget);
            pstmt.setInt(2,team.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Moves a player from one Team to another in the DataBase.
     * @param player Player that needs to be transferred.
     * @param oldTeam Team to which the player used to belong.
     * @param newTeam Team to which the player belongs now.
     */

    public static void updatePlayFor(Player player, Team oldTeam, Team newTeam){
        String sql = "UPDATE playfor SET team_id = ? WHERE player_id = ? AND team_id = ?";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,newTeam.getID());
            pstmt.setInt(2,player.getID());
            pstmt.setInt(3,oldTeam.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Updates the result of a match.
     * @param homeClub Home club of the match.
     * @param awayClub Away club of the match.
     * @param homeGoals Goals scored by the home club.
     * @param awayGoals Goals scored by the away club.
     */

    public static void updateMatch(String homeClub, String awayClub, int homeGoals, int awayGoals){
        String sql = "UPDATE match SET homegoals = ?, awaygoals = ? WHERE homeclub = '"+homeClub+
                "' AND awayclub = '"+awayClub+"'";
        try (Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,homeGoals);
            pstmt.setInt(2,awayGoals);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
