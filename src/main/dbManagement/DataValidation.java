package main.dbManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** This class contains methods needed to validate data that is saved in the Database.
 * @author Iker Villena Ona
 */

public class DataValidation {

    /**This method provides a generic form of checking if a value is not already registered, that can be used for checking leagueID,entryCode...
     *
     * @param table the table's name.
     * @param column the column's name.
     * @param value the value for the column in the table that needs to be checked.
     * @return a boolean which has the value true if the value is not registered yet and false if it is.
     */

    public static boolean check(String table, String column, Object value){
        boolean newID = true;
        String sql = "select count(*) as num from "+table+" where "+column+" = " + value;
        try (Connection conn = DBManager.connect(); Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                if(rs.getInt("num") > 0) {
                    newID = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newID;
    }

    /**This method checks whether the entry code received as a parameter is registered in the Database or not.
     * @param entryCode the entry code that needs to be checked.
     * @return a boolean which has the value true if the entryCode is correct and false if it is not.
     */

    public static boolean checkEntryCode(String entryCode){
        return (!check("league","entrycode","'"+entryCode+"'"));
    }

    /**This method checks whether the username and password provided belong to any of the managers
     * @param username the manager's username that needs to be checked.
     * @param password the manager's password that needs to be checked.
     * @return a boolean which is true if the username and the password are correct, and false if they are not.
     */

    public static boolean checkPassword(String username, String password){
        /*
        boolean correctPassword = false;
        for(Manager m : DataExtraction.getAllManagers()){
            if(m.getUsername().equals(username) && m.getPassword().equals(password)){
                correctPassword = true;
                break;
            }
        }
        return correctPassword;
         */
        //Using Lambda expressions:
        return DataExtraction.getAllManagers()
                .stream()
                .anyMatch(manager -> manager.checkPassword(username, password));
    }

}
