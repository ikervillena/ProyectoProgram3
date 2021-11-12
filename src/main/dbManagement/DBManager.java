package main.dbManagement;

import java.sql.*;

/** This class contains methods needed to interact with the Database
 * @author Iker Villena Ona
 */

public class DBManager {

    /**Creates a new Database.
     * @param fileName the name for the new Database's file.
     */

    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**Provides a Connection to the Database.
     * @return The Connection to the Database.
     */

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:ProjectDB.db");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**Creates a new table if the Database, if it does not exist yet.
     * @param sql String sql for creating the table.
     */

    public static void createNewTable(String sql) {
        try
                (
                        Connection conn = connect();
                        Statement stmt = conn.createStatement()
                ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
