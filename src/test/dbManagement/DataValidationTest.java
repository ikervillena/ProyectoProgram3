package test.dbManagement;

import main.dataLogic.league.League;
import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataValidation;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataValidationTest {

    @Test
    public void checkEntryCode(){
        for(League l : DataExtraction.getAllLeagues()){
            assertTrue(DataValidation.checkEntryCode(l.getEntryCode()));
        }
    }

    @Test
    public void checkPassword(){
        for(Manager m : DataExtraction.getAllManagers()){
            assertTrue("The method does not accept a correct password.",DataValidation.checkPassword(m.getUsername(),m.getPassword()));
            assertFalse("The method accepts an incorrect password.",DataValidation.checkPassword(m.getUsername(),m.getPassword()+"not valid"));
        }
    }

}
