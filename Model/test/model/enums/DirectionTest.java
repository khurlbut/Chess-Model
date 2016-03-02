package model.enums;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import model.enums.ViewDirection;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void direction_name_format_is_as_follows() {
        ViewDirection left_up = ViewDirection.LEFT_UP;
        assertThat(left_up.toString(), equalTo("left_up"));
    }

    @Test
    public void testUp() {
        ViewDirection up = ViewDirection.UP;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRightUp() {
        ViewDirection up = ViewDirection.RIGHT_UP;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRight() {
        ViewDirection up = ViewDirection.RIGHT;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testRightDown() {
        ViewDirection up = ViewDirection.RIGHT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testDown() {
        ViewDirection up = ViewDirection.DOWN;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeftDown() {
        ViewDirection up = ViewDirection.LEFT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeft() {
        ViewDirection up = ViewDirection.LEFT;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testLeftUp() {
        ViewDirection up = ViewDirection.LEFT_UP;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(1));
    }
    
    @Test
    public void testRightUpUp() {
        ViewDirection knightMove = ViewDirection.RIGHT_UP_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(1));
        assertThat(knightMove.verticalDelta(), equalTo(2));
    }

    @Test
    public void testRightRightUp() {
        ViewDirection knightMove = ViewDirection.RIGHT_RIGHT_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(2));
        assertThat(knightMove.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRightRightDown() {
        ViewDirection knightMove = ViewDirection.RIGHT_RIGHT_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(2));
        assertThat(knightMove.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testRightDownDown() {
        ViewDirection knightMove = ViewDirection.RIGHT_DOWN_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(1));
        assertThat(knightMove.verticalDelta(), equalTo(-2));
    }

    @Test
    public void testLeftDownDown() {
        ViewDirection knightMove = ViewDirection.LEFT_DOWN_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(-1));
        assertThat(knightMove.verticalDelta(), equalTo(-2));
    }

    @Test
    public void testLeftLeftDown() {
        ViewDirection knightMove = ViewDirection.LEFT_LEFT_DOWN;
        assertThat(knightMove.horizontalDelta(), equalTo(-2));
        assertThat(knightMove.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeftLeftUp() {
        ViewDirection knightMove = ViewDirection.LEFT_LEFT_UP;
        assertThat(knightMove.horizontalDelta(), equalTo(-2));
        assertThat(knightMove.verticalDelta(), equalTo(1));
    }

}
