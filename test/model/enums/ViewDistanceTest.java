package model.enums;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.enums.MovementType;

import org.junit.Test;

public class ViewDistanceTest {

    @Test
    public void test() {
        MovementType viewDistance = MovementType.SINGLE_UNIT;
        assertFalse(viewDistance.edgeOfBoard());

        viewDistance = MovementType.EDGE_OF_BOARD;
        assertTrue(viewDistance.edgeOfBoard());
    }

}
