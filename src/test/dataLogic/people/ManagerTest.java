package test.dataLogic.people;

import main.dataLogic.league.League;
import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class ManagerTest {

    private Manager manager;

    @Before
    public void setUp(){
        manager = new Manager("julencanas","juls","Julen","Cañas");
    }

    @Test
    public void getLeagues(){
        setUp();
        for(Manager m : DataExtraction.getAllManagers()){
            for(League l : m.getLeagues()){
                assertTrue("The manager does not have access to all the leagues provided by the method.",l.canAccess(m));
            }
        }
    }

}
