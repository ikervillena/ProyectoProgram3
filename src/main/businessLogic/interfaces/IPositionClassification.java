package main.businessLogic.interfaces;

import main.dataLogic.people.Player;

import java.util.ArrayList;

/**Contains the methods needed to get players of different positions.
 * @author Iker Villena Ona
 */

public interface IPositionClassification {

    ArrayList<Player> getDefenders();
    ArrayList<Player> getMidfielders();
    ArrayList<Player> getForwards();

}
