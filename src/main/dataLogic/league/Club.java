package main.dataLogic.league;

import main.dataLogic.people.Player;
import java.util.ArrayList;

/** Represents a club on which players compete.
 * @author Iker Villena Ona.
 */

public class Club {
    String name;
    ArrayList<Player> playersList;

    public Club(String name, ArrayList<Player> playersList) {
        this.name = name;
        this.playersList = playersList;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }

    @Override
    public String toString() {
        return name;
    }
}
