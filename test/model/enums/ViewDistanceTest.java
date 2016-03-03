package model.enums;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.enums.ViewDistance;

import org.junit.Test;

public class ViewDistanceTest {

    @Test
    public void test() {
        ViewDistance viewDistance = ViewDistance.SINGLE_UNIT;
        assertFalse(viewDistance.edgeOfBoard());

        viewDistance = ViewDistance.EDGE_OF_BOARD;
        assertTrue(viewDistance.edgeOfBoard());
    }

}
