package main.dataLogic.people;

import main.dataLogic.interfaces.IDBConnection;
import main.dataLogic.people.attributes.Position;
import main.dbManagement.DataExtraction;

/** Represents a football player.
 * @author Iker Villena Ona.
 */

public class Player implements IDBConnection {

    String name;
    String surname;
    String shirtName;
    int shirtNumber;
    Position position;
    float[] valueHistory;

    /**Constructor of a Player.
     * @param name A String with the player's name.
     * @param surname A String with the player's surname.
     * @param shirtName A String with the player's shirt name.
     * @param shirtNumber An integer with the player's shirt number.
     * @param position A Position in which the player usually plays.
     * @param valueHistory A float[] containing the player's valuer history.
     */

    public Player(String name, String surname, String shirtName, int shirtNumber, Position position, float[] valueHistory) {
        this.name = name;
        this.surname = surname;
        this.shirtName = shirtName;
        this.shirtNumber = shirtNumber;
        this.position = position;
        this.valueHistory = valueHistory;
    }

    /** Constructor used for testing the class "Squad.java".
     */

    public Player() {
    }

    /**This method transforms the object Player into a String.
     * @return a String with the player's name, surname, shirt name, shirt number, position and valuer history.
     */

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", shirtName='" + shirtName + '\'' +
                ", shirtNumber=" + shirtNumber +
                ", position=" + position +
                ", valueHistory=" + valueHistory +
                '}';
    }

    public Position getPosition() {
        return position;
    }

    public float[] getValueHistory() {
        return valueHistory;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getShirtName() {
        return shirtName;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    /**Provides the ID number with which the Player is registered in the Database.
     * @return An integer with the ID number.
     */

    @Override
    public int getID() {
        return DataExtraction.getID("player","player_id","shirtname",shirtName);
    }

    // getClub();

}