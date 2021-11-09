package businessLogic;

import dataLogic.people.Attributes.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticTest{

    @Test
    public void test(){
        Position position = new Position("Midfielder","MDF",6,3,6,-2);
        Statistic statistic = new Statistic(true,0,1,1,0,false);
        int actualPoints = statistic.getPoints(position);
        assertEquals(3,actualPoints);
    }
}