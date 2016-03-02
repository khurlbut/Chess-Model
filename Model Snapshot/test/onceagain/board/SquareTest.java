package onceagain.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import onceagain.enums.Column;
import onceagain.enums.Direction;
import onceagain.enums.Row;

import org.junit.Before;
import org.junit.Test;

public class SquareTest {

    private Square e_4 = new Square(Column.E, Row.R4);

    @Before
    public void setUp() {
    }

    @Test
    public void square_name_format_is_as_follows() {
        Square a_1 = new Square(Column.A, Row.R1);
        assertThat(a_1.toString(), equalTo("A_1"));
    }

    @Test
    public void neighbor_UP() {
        assertThat(e_4.neighbor(Direction.UP), equalTo(new Square(Column.E, Row.R5)));
    }

    @Test
    public void neighbor_DOWN() {
        assertThat(e_4.neighbor(Direction.DOWN), equalTo(new Square(Column.E, Row.R3)));
    }

    @Test
    public void neighbor_LEFT() {
        assertThat(e_4.neighbor(Direction.LEFT), equalTo(new Square(Column.D, Row.R4)));
    }

    @Test
    public void neighbor_RIGHT() {
        assertThat(e_4.neighbor(Direction.RIGHT), equalTo(new Square(Column.F, Row.R4)));
    }

    @Test
    public void neighbor_LEFT_UP() {
        assertThat(e_4.neighbor(Direction.LEFT_UP), equalTo(new Square(Column.D, Row.R5)));
    }

    @Test
    public void neighbor_UP_RIGHT() {
        assertThat(e_4.neighbor(Direction.RIGHT_UP), equalTo(new Square(Column.F, Row.R5)));
    }

    @Test
    public void neighbor_LEFT_DOWN() {
        assertThat(e_4.neighbor(Direction.LEFT_DOWN), equalTo(new Square(Column.D, Row.R3)));
    }

    @Test
    public void neighbor_RIGHT_DOWN() {
        assertThat(e_4.neighbor(Direction.RIGHT_DOWN), equalTo(new Square(Column.F, Row.R3)));
    }

}
