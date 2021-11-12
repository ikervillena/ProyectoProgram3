package test.businessLogic;

import main.businessLogic.Statistic;
import main.dataLogic.people.attributes.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticTest{

    @Test
    public void getPoints(){
        Position position = new Position("Midfielder","MDF",6,3,6,-2);
        Statistic statistic = new Statistic(true,0,1,1,0,false);
        int actualPoints = statistic.getPoints(position);
        assertEquals(3,actualPoints);
    }
}