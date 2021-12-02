package main.dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
