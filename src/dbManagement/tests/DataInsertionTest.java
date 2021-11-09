package dbManagement.tests;

import dataLogic.league.League;
import dataLogic.league.Team;
import dataLogic.people.Manager;
import dbManagement.DataExtraction;
import dbManagement.DataInsertion;
import org.junit.Before;
import org.junit.Test;

public class DataInsertionTest {

    private Manager manager;
    private String entryCode;

    @Before
    public void setUp(){
        manager = DataExtraction.getManager("ikervillena");
        entryCode = "new League";
    }

}
