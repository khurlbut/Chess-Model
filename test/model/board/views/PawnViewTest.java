package model.board.views;

import static model.Sugar.eventList;
import static model.Sugar.piece;
import static model.Sugar.play;
import static model.Sugar.position;
import static model.Sugar.put;
import static model.Sugar.square;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.PutEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;

import org.junit.Before;
import org.junit.Test;

public class PawnViewTest {

    private RankView w_pawnView_e_2 = null;
    private RankView b_pawnView_e_7 = null;

    private final Square e_5 = square(Column.E, Row.R5);
    private final Square f_5 = square(Column.F, Row.R5);
    private final Square f_4 = square(Column.F, Row.R4);
    private final Square f_3 = square(Column.F, Row.R3);
    private final Square e_3 = square(Column.E, Row.R3);
    private final Square d_3 = square(Column.D, Row.R3);
    private final Square d_4 = square(Column.D, Row.R4);
    private final Square d_5 = square(Column.D, Row.R5);

    private final Square e_6 = square(Column.E, Row.R6);
    private final Square f_6 = square(Column.F, Row.R6);
    private final Square g_6 = square(Column.G, Row.R6);
    private final Square g_5 = square(Column.G, Row.R5);
    private final Square g_4 = square(Column.G, Row.R4);
    private final Square g_3 = square(Column.G, Row.R3);
    private final Square g_2 = square(Column.G, Row.R2);
    private final Square f_2 = square(Column.F, Row.R2);
    private final Square e_2 = square(Column.E, Row.R2);
    private final Square d_2 = square(Column.D, Row.R2);
    private final Square c_2 = square(Column.C, Row.R2);
    private final Square c_3 = square(Column.C, Row.R3);
    private final Square c_4 = square(Column.C, Row.R4);
    private final Square c_5 = square(Column.C, Row.R5);
    private final Square c_6 = square(Column.C, Row.R6);
    private final Square d_6 = square(Column.D, Row.R6);
    private final Square e_4 = square(Column.E, Row.R4);

    private ChessBoard chessBoard;
    private BoardPosition position_e_2;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    public void it_finds_squares_holding_pieces_attacked() {
        chessBoard = putBlackPawnsInBoxAround_E_4();
        position_e_2 = position(chessBoard, Column.E, Row.R2);

        w_pawnView_e_2 = new PawnView(position_e_2, Color.WHITE);

        List<Square> squaresHoldingPiecesAttacked = w_pawnView_e_2.squaresHoldingPiecesAttacked();
        assertThat(squaresHoldingPiecesAttacked.size(), equalTo(2));

        assertTrue(squaresHoldingPiecesAttacked.contains(d_3));
        assertTrue(squaresHoldingPiecesAttacked.contains(f_3));
    }

    @Test
    public void it_finds_squares_holding_pieces_defended() {
        chessBoard = putBlackPawnsInBoxWithRadiousTwoAround_E_4();
        BoardPosition position_e_7 = position(chessBoard, Column.E, Row.R7);

        b_pawnView_e_7 = new PawnView(position_e_7, Color.BLACK);

        List<Square> squaresHoldingPiecesDefended = b_pawnView_e_7.squaresHoldingPiecesDefended();
        assertThat(squaresHoldingPiecesDefended.size(), equalTo(2));

        assertTrue(squaresHoldingPiecesDefended.contains(d_6));
        assertTrue(squaresHoldingPiecesDefended.contains(f_6));
    }

    @Test
    public void it_finds_move_to_squares() {
        chessBoard = chessBoard.playEvent(put(Color.WHITE, Rank.Pawn, e_2));
        position_e_2 = position(chessBoard, Column.E, Row.R2);

        w_pawnView_e_2 = new PawnView(position_e_2, Color.WHITE);

        List<Square> moveToSquares = w_pawnView_e_2.moveToSquares();
        assertThat(moveToSquares.size(), equalTo(2));

        assertTrue(moveToSquares.contains(e_3));
        assertTrue(moveToSquares.contains(e_4));
    }

    @Test
    public void it_finds_threatened_squares() {
        chessBoard = chessBoard.playEvent(put(Color.WHITE, Rank.Pawn, e_2));
        position_e_2 = position(chessBoard, Column.E, Row.R2);

        w_pawnView_e_2 = new PawnView(position_e_2, Color.WHITE);

        List<Square> threatenedSquares = w_pawnView_e_2.threatenedSquares();
        assertThat(threatenedSquares.size(), equalTo(2));

        assertTrue(threatenedSquares.contains(d_3));
        assertTrue(threatenedSquares.contains(f_3));
    }

    private ChessBoard putBlackPawnsInBoxAround_E_4() {
        Piece b_pawn_e_5 = piece(Color.BLACK, Rank.Pawn, e_5);
        Piece b_pawn_f_5 = piece(Color.BLACK, Rank.Pawn, f_5);
        Piece b_pawn_f_4 = piece(Color.BLACK, Rank.Pawn, f_4);
        Piece b_pawn_f_3 = piece(Color.BLACK, Rank.Pawn, f_3);
        Piece b_pawn_e_3 = piece(Color.BLACK, Rank.Pawn, e_3);
        Piece b_pawn_d_3 = piece(Color.BLACK, Rank.Pawn, d_3);
        Piece b_pawn_d_4 = piece(Color.BLACK, Rank.Pawn, d_4);
        Piece b_pawn_d_5 = piece(Color.BLACK, Rank.Pawn, d_5);

        PutEvent put_b_pawn_e_5 = put(b_pawn_e_5);
        PutEvent put_b_pawn_f_5 = put(b_pawn_f_5);
        PutEvent put_b_pawn_f_4 = put(b_pawn_f_4);
        PutEvent put_b_pawn_f_3 = put(b_pawn_f_3);
        PutEvent put_b_pawn_e_3 = put(b_pawn_e_3);
        PutEvent put_b_pawn_d_3 = put(b_pawn_d_3);
        PutEvent put_b_pawn_d_4 = put(b_pawn_d_4);
        PutEvent put_b_pawn_d_5 = put(b_pawn_d_5);

        List<GameEvent> puts =
            eventList(put_b_pawn_e_5, put_b_pawn_f_5, put_b_pawn_f_4, put_b_pawn_f_3, put_b_pawn_e_3, put_b_pawn_d_3,
            put_b_pawn_d_4, put_b_pawn_d_5);
        
        chessBoard = play(chessBoard, puts);

        return chessBoard;
    }

    private ChessBoard putBlackPawnsInBoxWithRadiousTwoAround_E_4() {
        Piece b_pawn_e_6 = piece(Color.BLACK, Rank.Pawn, e_6);
        Piece b_pawn_f_6 = piece(Color.BLACK, Rank.Pawn, f_6);
        Piece b_pawn_g_6 = piece(Color.BLACK, Rank.Pawn, g_6);
        Piece b_pawn_g_5 = piece(Color.BLACK, Rank.Pawn, g_5);
        Piece b_pawn_g_4 = piece(Color.BLACK, Rank.Pawn, g_4);
        Piece b_pawn_g_3 = piece(Color.BLACK, Rank.Pawn, g_3);
        Piece b_pawn_g_2 = piece(Color.BLACK, Rank.Pawn, g_2);
        Piece b_pawn_f_2 = piece(Color.BLACK, Rank.Pawn, f_2);
        Piece b_pawn_e_2 = piece(Color.BLACK, Rank.Pawn, e_2);
        Piece b_pawn_d_2 = piece(Color.BLACK, Rank.Pawn, d_2);
        Piece b_pawn_c_2 = piece(Color.BLACK, Rank.Pawn, c_2);
        Piece b_pawn_c_3 = piece(Color.BLACK, Rank.Pawn, c_3);
        Piece b_pawn_c_4 = piece(Color.BLACK, Rank.Pawn, c_4);
        Piece b_pawn_c_5 = piece(Color.BLACK, Rank.Pawn, c_5);
        Piece b_pawn_c_6 = piece(Color.BLACK, Rank.Pawn, c_6);
        Piece b_pawn_d_6 = piece(Color.BLACK, Rank.Pawn, d_6);

        PutEvent put_b_pawn_e_6 = put(b_pawn_e_6);
        PutEvent put_b_pawn_f_6 = put(b_pawn_f_6);
        PutEvent put_b_pawn_g_6 = put(b_pawn_g_6);
        PutEvent put_b_pawn_g_5 = put(b_pawn_g_5);
        PutEvent put_b_pawn_g_4 = put(b_pawn_g_4);
        PutEvent put_b_pawn_g_3 = put(b_pawn_g_3);
        PutEvent put_b_pawn_g_2 = put(b_pawn_g_2);
        PutEvent put_b_pawn_f_2 = put(b_pawn_f_2);
        PutEvent put_b_pawn_e_2 = put(b_pawn_e_2);
        PutEvent put_b_pawn_d_2 = put(b_pawn_d_2);
        PutEvent put_b_pawn_c_2 = put(b_pawn_c_2);
        PutEvent put_b_pawn_c_3 = put(b_pawn_c_3);
        PutEvent put_b_pawn_c_4 = put(b_pawn_c_4);
        PutEvent put_b_pawn_c_5 = put(b_pawn_c_5);
        PutEvent put_b_pawn_c_6 = put(b_pawn_c_6);
        PutEvent put_b_pawn_d_6 = put(b_pawn_d_6);

        List<GameEvent> puts =
            eventList(put_b_pawn_e_6, put_b_pawn_f_6, put_b_pawn_g_6, put_b_pawn_g_5, put_b_pawn_g_4,
                put_b_pawn_g_3, put_b_pawn_g_2, put_b_pawn_f_2, put_b_pawn_e_2, put_b_pawn_d_2, put_b_pawn_c_2,
                put_b_pawn_c_3, put_b_pawn_c_4, put_b_pawn_c_5, put_b_pawn_c_6, put_b_pawn_d_6);

        chessBoard = play(chessBoard, puts);

        return chessBoard;
    }

}
