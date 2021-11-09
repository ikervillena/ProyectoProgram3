package dataLogic.people.tests;

import dataLogic.people.Manager;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {

    private Manager manager;

    @Before
    public void setUp(){
        manager = new Manager("julencanas","juls","Julen","Cañas");
    }

    @Test
    public void createLeague(){
        setUp();
        manager.createLeague("Amazing League","only win");
    }

}
