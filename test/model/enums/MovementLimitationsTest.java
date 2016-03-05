package model.enums;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.enums.MovementLimitations;

import org.junit.Test;

public class MovementLimitationsTest {

    @Test
    public void test() {
        MovementLimitations limitations = MovementLimitations.ONE_UNIT_SQUARE;
        assertFalse(limitations.edgeOfBoard());

        limitations = MovementLimitations.EDGE_OF_BOARD;
        assertTrue(limitations.edgeOfBoard());
    }

}
