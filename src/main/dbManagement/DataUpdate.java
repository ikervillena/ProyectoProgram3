package main.dbManagement;

import main.dataLogic.league.Team;
import main.dataLogic.people.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
