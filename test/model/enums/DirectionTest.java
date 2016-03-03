package model.enums;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import model.enums.ViewVector;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void direction_name_format_is_as_follows() {
        ViewVector left_up = ViewVector.LEFT_UP;
        assertThat(left_up.toString(), equalTo("left_up"));
    }

    @Test
    public void testUp() {
        ViewVector up = ViewVector.UP;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRightUp() {
        ViewVector up = ViewVector.RIGHT_UP;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRight() {
        ViewVector up = ViewVector.RIGHT;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testRightDown() {
        ViewVector up = ViewVector.RIGHT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testDown() {
        ViewVector up = ViewVector.DOWN;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeftDown() {
        ViewVector up = ViewVector.LEFT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeft() {
        ViewVector up = ViewVector.LEFT;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testLeftUp() {
        ViewVector up = ViewVector.LEFT_UP;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(1));
    }
    
    @Test
    public void testRightUpUp() {
        ViewVector knightMove = ViewVector.RIGHT_UP_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(1));
        assertThat(knightMove.verticalDelta(), equalTo(2));
    }

    @Test
    public void testRightRightUp() {
        ViewVector knightMove = ViewVector.RIGHT_RIGHT_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(2));
        assertThat(knightMove.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRightRightDown() {
        ViewVector knightMove = ViewVector.RIGHT_RIGHT_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(2));
        assertThat(knightMove.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testRightDownDown() {
        ViewVector knightMove = ViewVector.RIGHT_DOWN_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(1));
        assertThat(knightMove.verticalDelta(), equalTo(-2));
    }

    @Test
    public void testLeftDownDown() {
        ViewVector knightMove = ViewVector.LEFT_DOWN_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(-1));
        assertThat(knightMove.verticalDelta(), equalTo(-2));
    }

    @Test
    public void testLeftLeftDown() {
        ViewVector knightMove = ViewVector.LEFT_LEFT_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(-2));
        assertThat(knightMove.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeftLeftUp() {
        ViewVector knightMove = ViewVector.LEFT_LEFT_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(-2));
        assertThat(knightMove.verticalDelta(), equalTo(1));
    }

}
