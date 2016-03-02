package onceagain.enums;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DirectionTest {

    @Test
    public void direction_name_format_is_as_follows() {
        Direction left_up = Direction.LEFT_UP;
        assertThat(left_up.toString(), equalTo("left_up"));
    }

    @Test
    public void testUp() {
        Direction up = Direction.UP;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testDown() {
        Direction up = Direction.DOWN;
        assertThat(up.horizontalDelta(), equalTo(0));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testRight() {
        Direction up = Direction.RIGHT;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testLeft() {
        Direction up = Direction.LEFT;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(0));
    }

    @Test
    public void testRightUp() {
        Direction up = Direction.RIGHT_UP;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testRightDown() {
        Direction up = Direction.RIGHT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

    @Test
    public void testLeftUp() {
        Direction up = Direction.LEFT_UP;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(1));
    }

    @Test
    public void testLeftDown() {
        Direction up = Direction.LEFT_DOWN;
        assertThat(up.horizontalDelta(), equalTo(-1));
        assertThat(up.verticalDelta(), equalTo(-1));
    }

}
