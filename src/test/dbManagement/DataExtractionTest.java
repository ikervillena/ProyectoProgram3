package test.dbManagement;

import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import main.dataLogic.league.League;
import main.dataLogic.people.attributes.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "DataExtractor.java".
 * @author Iker Villena Ona.
 */

public class DataExtractionTest {

    /** Verifies the getPositions() method.
     * For that: extracts the list of positions saved in the DB, and checks that the ArrayList has all of them (4).
     */

    @Test
    public void getPositions(){
        ArrayList<Position> positionsList = DataExtraction.getPositions();
        Assert.assertEquals(4,positionsList.size());
    }

}
