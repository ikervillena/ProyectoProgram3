package DataLogic.League;

import DataLogic.People.Manager;
import DataLogic.People.Player;

import java.util.ArrayList;

/** Represents a virtual team that users of the game (managers) can manage.
 * @author Iker Villena Ona.
 */

public class Team {

    Manager manager;
    ArrayList<Player> playersList;
    Squad currentSquad = null;
    ArrayList<Squad> squadRecord = null;

    // Falta documentar el constructor
    public Team(Manager manager) {
        this.manager = manager;
    }
}
