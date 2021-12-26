package main.dbManagement;

import main.dataLogic.league.Bid;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** This class contains methods needed to delete data from the Database.
 * @author Iker Villena Ona
 */

public class DataDeletion {

    public static void delete(String table, String colValue1, int value1,String colvValue2, int value2){
        String sql = "delete from "+table+" where "+colValue1+" = ? and "+colvValue2+" = ?";
        try(Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, value1);
            pstmt.setInt(2,value2);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**Deletes a specific bid from the DataBase.
     * @param bid Bid that needs to be deleted.
     */

    public static void deleteBid(Bid bid){
        String sql = "delete from bid where from_team = ? AND (to_team = ? OR to_team IS NULL) AND player_id = ?";
        try(Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,bid.getInterestedTeam().getID());
            if(bid.getCurrentTeam() != null){
                pstmt.setInt(2,bid.getCurrentTeam().getID());
            }
            pstmt.setInt(3,bid.getPlayer().getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**Deletes a Team from the DataBase.
     * @param team Team that needs to be deleted.
     */

    public static void deleteTeam(Team team){
        String sql = "delete from team where team_id = ?";
        try(Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, team.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**Deletes a League from the DataBase.
     * @param league League that needs to be deleted.
     */

    public static void deleteLeague(League league){
        String sql = "delete from league where league_id = ?";
        try(Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, league.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**Cleans a table.
     * @param tableName String with the name of the table from which data needs to be removed.
     */

    public static void cleanTable(String tableName){
        String sql = "delete from ?";
        try(Connection conn = DBManager.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,tableName);
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
