package DBManagement;

import DataLogic.People.Attributes.Position;
import DataLogic.People.Player;

import java.sql.*;
import java.util.ArrayList;

/** This class has the aim of extracting data from de DataBase.
 * It provides the methods needed for that purpose.
 * @author Iker Villena Ona.
 */

public class DataExtractor {

    public static ArrayList<Position> getPositions(){
        String sql = "select position_name,shortname,pts_goal,pts_assist,pts_nogoalsagainst," +
                "pts_goalsagainst from position";
        ArrayList<Position> positionsList = new ArrayList<>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                positionsList.add(new Position(rs.getString("position_name"),rs.getString("shortname")
                        ,rs.getInt("pts_goal"),rs.getInt("pts_assist"),
                        rs.getInt("pts_nogoalsagainst"),rs.getInt("pts_goalsagainst")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return positionsList;
    }

    public static ArrayList<Player> getAllPlayers(){
        String sql = "select name,surname,shirtname,number,position_name,value from player";
        ArrayList<Player> playersList = new ArrayList<Player>();
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String shirtName = rs.getString("shirtname");
                int shirtNumber = rs.getInt("number");
                Position position = Position.getPosition(rs.getString("position_name"));
                float value = rs.getFloat("value");
                playersList.add(new Player(name,surname,shirtName,shirtNumber,position,value));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playersList;
    }

}
