package dataLogic.people;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import dataLogic.interfaces.IDBConnection;
import dataLogic.people.Attributes.Position;
import dbManagement.DataExtraction;

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

    //Falta documentar el constructor


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

    @Override
    public int getID() {
        return DataExtraction.getID("player","player_id","shirtname",shirtName);
    }
}

// getClub();