package test.dataLogic.league;

import main.dataLogic.league.Bid;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class BidTest {

    private Bid bid1;
    private Bid bid2;
    private Bid bid3;
    private Bid bid4;

    @Before
    public void setUp(){
        bid1 = new Bid(null,null,null,10);
        bid2 = new Bid(null,null,null,20);
        bid3 = new Bid(null,null,null,20);
        bid4 = new Bid(null,null,null,30);
    }

    /**Tests the compareTo(Bid) method.
     */

    @Test
    public void compareTo(){
        assertEquals(-1,bid2.compareTo(bid1));
        assertEquals(0,bid2.compareTo(bid3));
        assertEquals(1,bid2.compareTo(bid4));
    }
}
