package main.businessLogic;

import main.businessLogic.interfaces.IComparable;
import main.businessLogic.interfaces.IDBConnection;
import main.dbManagement.DataExtraction;

/** Represents a tactical formation in which players can be aligned by the manager.
 * @author Iker Villena Ona.
 */

public class TacticalFormation implements IDBConnection, IComparable<TacticalFormation> {

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

    /**Compares two tactical formations, taking into account the number of players in each position (defenders-midfielders-forwards, in that order)
     * @param object TacticalFormation to compare with.
     * @return Integer with the value 1 if this object is bigger(more defenders or, if same number of defenders, more midfielders), -1 if it is smaller
     * or 0 if they are equal.
     */

    @Override
    public int compareTo(TacticalFormation object) {
        if(this.numDefenders == object.getNumDefenders()){
            if(this.numMidfielders == object.getNumMidfielders()){
                return 0;
            }else{
                if(this.numMidfielders > object.getNumMidfielders()){
                    return 1;
                } else{
                    return -1;
                }
            }
        } else{
            if(this.numDefenders > object.getNumDefenders()){
                return 1;
            } else{
                return -1;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual = false;
        if(obj instanceof TacticalFormation){
            TacticalFormation formation = (TacticalFormation) obj;
            if(formation.getNumDefenders()==this.numDefenders && formation.getNumMidfielders() == this.numMidfielders &&
            formation.getNumForwards() == this.numForwards){
                isEqual = true;
            }
        }
        return isEqual;
    }
}
