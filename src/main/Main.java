package main;


import main.presentationLogic.Login;

import java.sql.SQLException;

/**This class is aimed at initializing the game, opening the login view.
 * @author Iker Villena Ona
 */

public class Main {

    public static void main(String[] args) throws SQLException {

        new Login().setVisible(true);

    }

}
