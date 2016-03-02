package onceagain.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import onceagain.board.perspectives.RadiantPerspective;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Direction;
import onceagain.enums.Row;

import org.junit.Before;
import org.junit.Test;

public class RadiantSquareTest {

    private RadiantPerspective radiantSquare_e_4 = null;

    @Before
    public void setUp() {
        radiantSquare_e_4 = new RadiantPerspective(e_4, Direction.values());
    }

    @Test
    public void it_builds_a_list_of_all_radial_squares() {
        List<Square> radials = radiantSquare_e_4.radials();
        assertFalse(radials.contains(radiantSquare_e_4));

        assertColumnE(radials);
        assertRow4(radials);
        assertDiagonalRightUp(radials);
        assertDiagonalRightDown(radials);
    }

    @Test
    public void it_builds_a_list_of_radial_squares_up_to_and_including_first_occupied_squares() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<Square> radials = radiantSquare_e_4.radialsIncludingOccuppied(chessBoard);

        assertRow4(radials);

        assertTrue(radials.contains(e_5));
        assertTrue(radials.contains(e_6));
        assertTrue(radials.contains(e_7));
        assertFalse(radials.contains(e_8));

        assertFalse(radials.contains(b_1));
        assertTrue(radials.contains(c_2));
        assertTrue(radials.contains(d_3));
        assertTrue(radials.contains(f_5));
        assertTrue(radials.contains(g_6));
        assertTrue(radials.contains(h_7));

        assertFalse(radials.contains(a_8));
        assertTrue(radials.contains(b_7));
        assertTrue(radials.contains(c_6));
        assertTrue(radials.contains(d_5));
        assertTrue(radials.contains(f_3));
        assertTrue(radials.contains(g_2));
        assertFalse(radials.contains(h_1));
    }

    @Test
    public void it_builds_a_list_of_squares_in_the_line_from_its_neighbor_to_the_edge() {
        List<Square> line = radiantSquare_e_4.line(Direction.UP);
        assertFalse(line.contains(radiantSquare_e_4));

        assertTrue(line.contains(e_5));
        assertTrue(line.contains(e_6));
        assertTrue(line.contains(e_7));
        assertTrue(line.contains(e_8));
    }

    @Test
    public void it_builds_a_list_of_squares_in_the_line_from_its_neighbor_to_the_nearest_piece() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<Square> line = radiantSquare_e_4.lineIncludingNearestOccupied(Direction.UP, chessBoard);
        assertFalse(line.contains(radiantSquare_e_4));

        assertTrue(line.contains(e_5));
        assertTrue(line.contains(e_6));
        assertTrue(line.contains(e_7));
        assertFalse(line.contains(e_8));
    }

    @Test
    public void it_builds_a_list_of_squares_in_the_line_from_its_neighbor_to_the_last_available_square() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<Square> line = radiantSquare_e_4.lineExcludingNearestOccupied(Direction.UP, chessBoard);
        assertFalse(line.contains(radiantSquare_e_4));

        assertTrue(line.contains(e_5));
        assertTrue(line.contains(e_6));
        assertFalse(line.contains(e_7));
        assertFalse(line.contains(e_8));
    }

    @Test
    public void it_builds_a_list_with_the_square_in_the_line_that_holds_the_nearest_piece_with_the_specified_color() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<Square> line = radiantSquare_e_4.nearestColorOccupiedOnLine(Direction.UP, chessBoard, Color.BLACK);
        assertFalse(line.contains(radiantSquare_e_4));

        assertFalse(line.contains(e_5));
        assertFalse(line.contains(e_6));
        assertTrue(line.contains(e_7));
        assertFalse(line.contains(e_8));
    }

    @Test
    public void neighbor_at_board_edge_is_null() {
        Square a_1 = new Square(Column.A, Row.R1);
        assertNull(a_1.neighbor(Direction.LEFT));
        assertNull(a_1.neighbor(Direction.DOWN));
        assertNull(a_1.neighbor(Direction.LEFT_UP));
        assertNull(a_1.neighbor(Direction.LEFT_DOWN));
        assertNull(a_1.neighbor(Direction.RIGHT_DOWN));

        assertNotNull(a_1.neighbor(Direction.UP));
        assertNotNull(a_1.neighbor(Direction.RIGHT));
        assertNotNull(a_1.neighbor(Direction.RIGHT_UP));
    }

    private void assertDiagonalRightDown(List<Square> radials) {
        assertTrue(radials.contains(a_8));
        assertTrue(radials.contains(b_7));
        assertTrue(radials.contains(c_6));
        assertTrue(radials.contains(d_5));
        assertTrue(radials.contains(f_3));
        assertTrue(radials.contains(g_2));
        assertTrue(radials.contains(h_1));
    }

    private void assertDiagonalRightUp(List<Square> radials) {
        assertTrue(radials.contains(b_1));
        assertTrue(radials.contains(c_2));
        assertTrue(radials.contains(d_3));
        assertTrue(radials.contains(f_5));
        assertTrue(radials.contains(g_6));
        assertTrue(radials.contains(h_7));
    }

    private void assertRow4(List<Square> radials) {
        assertTrue(radials.contains(a_4));
        assertTrue(radials.contains(b_4));
        assertTrue(radials.contains(c_4));
        assertTrue(radials.contains(d_4));
        assertTrue(radials.contains(f_4));
        assertTrue(radials.contains(g_4));
        assertTrue(radials.contains(h_4));
    }

    private void assertColumnE(List<Square> radials) {
        assertTrue(radials.contains(e_1));
        assertTrue(radials.contains(e_2));
        assertTrue(radials.contains(e_3));
        assertTrue(radials.contains(e_5));
        assertTrue(radials.contains(e_6));
        assertTrue(radials.contains(e_7));
        assertTrue(radials.contains(e_8));
    }

    private Square e_1 = new Square(Column.E, Row.R1);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square e_3 = new Square(Column.E, Row.R3);
    private Square e_4 = new Square(Column.E, Row.R4);
    private Square e_5 = new Square(Column.E, Row.R5);
    private Square e_6 = new Square(Column.E, Row.R6);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square e_8 = new Square(Column.E, Row.R8);

    private Square a_4 = new Square(Column.A, Row.R4);
    private Square b_4 = new Square(Column.B, Row.R4);
    private Square c_4 = new Square(Column.C, Row.R4);
    private Square d_4 = new Square(Column.D, Row.R4);
    private Square f_4 = new Square(Column.F, Row.R4);
    private Square g_4 = new Square(Column.G, Row.R4);
    private Square h_4 = new Square(Column.H, Row.R4);

    private Square b_1 = new Square(Column.B, Row.R1);
    private Square c_2 = new Square(Column.C, Row.R2);
    private Square d_3 = new Square(Column.D, Row.R3);
    private Square f_5 = new Square(Column.F, Row.R5);
    private Square g_6 = new Square(Column.G, Row.R6);
    private Square h_7 = new Square(Column.H, Row.R7);

    private Square a_8 = new Square(Column.A, Row.R8);
    private Square b_7 = new Square(Column.B, Row.R7);
    private Square c_6 = new Square(Column.C, Row.R6);
    private Square d_5 = new Square(Column.D, Row.R5);
    private Square f_3 = new Square(Column.F, Row.R3);
    private Square g_2 = new Square(Column.G, Row.R2);
    private Square h_1 = new Square(Column.H, Row.R1);

}
