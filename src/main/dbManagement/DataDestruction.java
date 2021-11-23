package main.dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataDestruction {

    public static void delete(String table, String keyColumn, int league_id) throws SQLException {
        String sql = "Delete from "+table+" where "+keyColumn+" = ?";
        Connection conn = DBManager.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,league_id);
        pstmt.executeUpdate();
    }

    public static void deleteTeam(int league_id) throws SQLException {
        String sql = "Delete from team where league_id = ?";
        Connection conn = DBManager.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,league_id);
        pstmt.executeUpdate();
    }

}
