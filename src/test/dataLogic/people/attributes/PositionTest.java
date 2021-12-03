package test.dataLogic.people.attributes;

import main.businessLogic.Algorithm;
import main.dataLogic.people.Player;
import main.dataLogic.people.attributes.Position;
import main.dbManagement.DataExtraction;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/** This class has the aim of testing the methods implemented in the class "Position.java".
 * @author Iker Villena Ona.
 */

public class PositionTest {

    /** Verifies the getPosition() method.
     * For that: creates the objects contained in the DB, and then compares their attributes with
     * the ones provided by the getPosition() method.
     */

    @Test
    public void getPosition(){
        Position gkp = new Position("Goalkeeper","GKP",6,5,6,-2);
        Position def = new Position("Defense","DEF",5,4,4,-1);
        Position mdf = new Position("Midfielder","MDF",4,3,2,-1);
        Position fwd = new Position("Forward","FRW",3,2,1,0);
        Assert.assertEquals(gkp.toString(), Position.getPosition("Goalkeeper").toString());
        Assert.assertEquals(def.toString(),Position.getPosition("Defense").toString());
        Assert.assertEquals(mdf.toString(),Position.getPosition("Midfielder").toString());
        Assert.assertEquals(fwd.toString(),Position.getPosition("Forward").toString());
    }

}
