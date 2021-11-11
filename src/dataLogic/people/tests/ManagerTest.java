package dataLogic.people.tests;

import dataLogic.league.League;
import dataLogic.league.Team;
import dataLogic.people.Manager;
import dataLogic.people.Player;
import dbManagement.DataExtraction;
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

    @Test
    public void getLeagues(){
        setUp();
        for(League l : manager.getLeagues()){
            System.out.println(l);
        }
    }

}
