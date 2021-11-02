package DataLogic.People;

/** Represents a football player.
 * @author Iker Villena Ona.
 */

public class Player {

    String name;
    String surname;
    String shirtName;
    int shirtNumber;


    //Falta documentar el constructor
    public Player(String name, String surname, String shirtName, int shirtNumber) {
        this.name = name;
        this.surname = surname;
        this.shirtName = shirtName;
        this.shirtNumber = shirtNumber;
    }
}

// getClub();