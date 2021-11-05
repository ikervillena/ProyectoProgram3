package DataLogic.People;

import DataLogic.People.Attributes.Position;

/** Represents a football player.
 * @author Iker Villena Ona.
 */

public class Player {

    String name;
    String surname;
    String shirtName;
    int shirtNumber;
    Position position;
    float value;

    //Falta documentar el constructor


    public Player(String name, String surname, String shirtName, int shirtNumber, Position position, float value) {
        this.name = name;
        this.surname = surname;
        this.shirtName = shirtName;
        this.shirtNumber = shirtNumber;
        this.position = position;
        this.value = value;
    }

    /** Constructor used for testing the class "Squad.java".
     */

    public Player() {
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", shirtName='" + shirtName + '\'' +
                ", shirtNumber=" + shirtNumber +
                ", position=" + position +
                ", value=" + value +
                '}';
    }

    public Position getPosition() {
        return position;
    }
}

// getClub();