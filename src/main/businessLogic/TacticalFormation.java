package main.businessLogic;

import main.businessLogic.interfaces.IDBConnection;
import main.dbManagement.DataExtraction;

/** Represents a tactical formation in which players can be aligned by the manager.
 * @author Iker Villena Ona.
 */

public class TacticalFormation implements IDBConnection {

    int numDefenders;
    int numMidfielders;
    int numForwards;

    public TacticalFormation(int numDefenders, int numMidfielders, int numForwards) {
        this.numDefenders = numDefenders;
        this.numMidfielders = numMidfielders;
        this.numForwards = numForwards;
    }

    public int getNumDefenders() {
        return numDefenders;
    }

    public void setNumDefenders(int numDefenders) {
        this.numDefenders = numDefenders;
    }

    public int getNumMidfielders() {
        return numMidfielders;
    }

    public void setNumMidfielders(int numMidfielders) {
        this.numMidfielders = numMidfielders;
    }

    public int getNumForwards() {
        return numForwards;
    }

    public void setNumForwards(int numForwards) {
        this.numForwards = numForwards;
    }

    @Override
    public String toString() {
        return numDefenders+" - "+numMidfielders+" - "+numForwards;
    }

    @Override
    public int getID() {
        return DataExtraction.getFormationID(numDefenders, numMidfielders, numForwards);
    }
}
