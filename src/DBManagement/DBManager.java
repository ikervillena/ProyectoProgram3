package DBManagement;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class contains methods needed to interact with the Database
 * @author Iker Villena Ona
 */

public class DBManager {

    public static void createNewDatabase(String fileName)
    {

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
