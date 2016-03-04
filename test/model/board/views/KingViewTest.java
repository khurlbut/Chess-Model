package model.board.views;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.PutEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

import org.junit.Before;
import org.junit.Test;

public class KingViewTest {

    private RankView w_kingView_e_4 = null;
    private RankView b_kingView_e_4 = null;

    private final Square e_5 = new Square(Column.E, Row.R5);
    private final Square f_5 = new Square(Column.F, Row.R5);
    private final Square f_4 = new Square(Column.F, Row.R4);
    private final Square f_3 = new Square(Column.F, Row.R3);
    private final Square e_3 = new Square(Column.E, Row.R3);
    private final Square d_3 = new Square(Column.D, Row.R3);
    private final Square d_4 = new Square(Column.D, Row.R4);
    private final Square d_5 = new Square(Column.D, Row.R5);

    private final Square e_6 = new Square(Column.E, Row.R6);
    private final Square f_6 = new Square(Column.F, Row.R6);
    private final Square g_6 = new Square(Column.G, Row.R6);
    private final Square g_5 = new Square(Column.G, Row.R5);
    private final Square g_4 = new Square(Column.G, Row.R4);
    private final Square g_3 = new Square(Column.G, Row.R3);
    private final Square g_2 = new Square(Column.G, Row.R2);
    private final Square f_2 = new Square(Column.F, Row.R2);
    private final Square e_2 = new Square(Column.E, Row.R2);
    private final Square d_2 = new Square(Column.D, Row.R2);
    private final Square c_2 = new Square(Column.C, Row.R2);
    private final Square c_3 = new Square(Column.C, Row.R3);
    private final Square c_4 = new Square(Column.C, Row.R4);
    private final Square c_5 = new Square(Column.C, Row.R5);
    private final Square c_6 = new Square(Column.C, Row.R6);
    private final Square d_6 = new Square(Column.D, Row.R6);

    private ChessBoard chessBoard;
    private BoardPosition position_e_4;

    @Before
    public void setUp() {
        chessBoard = new ChessBoard();
        position_e_4 = new BoardPosition(chessBoard, new Square(Column.E, Row.R4));
    }

    @Test
    public void it_finds_squares_holding_pieces_attacked() {
        chessBoard = putBlackPawnsInBoxAround_E_4();
        position_e_4 = new BoardPosition(chessBoard, new Square(Column.E, Row.R4));

        w_kingView_e_4 = new KingView(position_e_4, Color.WHITE);

        List<Square> squaresHoldingPiecesAttacked = w_kingView_e_4.squaresHoldingPiecesAttacked();
        assertThat(squaresHoldingPiecesAttacked.size(), equalTo(8));

        assertTrue(squaresHoldingPiecesAttacked.contains(e_5));
        assertTrue(squaresHoldingPiecesAttacked.contains(f_5));
        assertTrue(squaresHoldingPiecesAttacked.contains(f_4));
        assertTrue(squaresHoldingPiecesAttacked.contains(f_3));
        assertTrue(squaresHoldingPiecesAttacked.contains(e_3));
        assertTrue(squaresHoldingPiecesAttacked.contains(d_3));
        assertTrue(squaresHoldingPiecesAttacked.contains(d_4));
        assertTrue(squaresHoldingPiecesAttacked.contains(d_5));
    }

    @Test
    public void it_finds_squares_holding_pieces_defended() {
        chessBoard = putBlackPawnsInBoxAround_E_4();
        position_e_4 = new BoardPosition(chessBoard, new Square(Column.E, Row.R4));

        b_kingView_e_4 = new KingView(position_e_4, Color.BLACK);

        List<Square> squaresHoldingPiecesDefended = b_kingView_e_4.squaresHoldingPiecesDefended();
        assertThat(squaresHoldingPiecesDefended.size(), equalTo(8));

        assertTrue(squaresHoldingPiecesDefended.contains(e_5));
        assertTrue(squaresHoldingPiecesDefended.contains(f_5));
        assertTrue(squaresHoldingPiecesDefended.contains(f_4));
        assertTrue(squaresHoldingPiecesDefended.contains(f_3));
        assertTrue(squaresHoldingPiecesDefended.contains(e_3));
        assertTrue(squaresHoldingPiecesDefended.contains(d_3));
        assertTrue(squaresHoldingPiecesDefended.contains(d_4));
        assertTrue(squaresHoldingPiecesDefended.contains(d_5));

    }

    @Test
    public void it_finds_move_to_squares() {
        w_kingView_e_4 = new KingView(position_e_4, Color.WHITE);

        List<Square> moveToSquares = w_kingView_e_4.moveToSquares();
        assertThat(moveToSquares.size(), equalTo(8));

        assertTrue(moveToSquares.contains(e_5));
        assertTrue(moveToSquares.contains(f_5));
        assertTrue(moveToSquares.contains(f_4));
        assertTrue(moveToSquares.contains(f_3));
        assertTrue(moveToSquares.contains(e_3));
        assertTrue(moveToSquares.contains(d_3));
        assertTrue(moveToSquares.contains(d_4));
        assertTrue(moveToSquares.contains(d_5));
    }

    @Test
    public void it_finds_threatened_to_squares() {
        w_kingView_e_4 = new KingView(position_e_4, Color.WHITE);

        List<Square> threatenedSquares = w_kingView_e_4.threatenedSquares();
        assertThat(threatenedSquares.size(), equalTo(8));

        assertTrue(threatenedSquares.contains(e_5));
        assertTrue(threatenedSquares.contains(f_5));
        assertTrue(threatenedSquares.contains(f_4));
        assertTrue(threatenedSquares.contains(f_3));
        assertTrue(threatenedSquares.contains(e_3));
        assertTrue(threatenedSquares.contains(d_3));
        assertTrue(threatenedSquares.contains(d_4));
        assertTrue(threatenedSquares.contains(d_5));
    }

    @Test
    public void king_cannot_attack_beyond_the_inner_box() {
        chessBoard = putBlackPawnsInBoxWithRadiousTwoAround_E_4();
        position_e_4 = new BoardPosition(chessBoard, new Square(Column.E, Row.R4));

        w_kingView_e_4 = new KingView(position_e_4, Color.WHITE);

        List<Square> squaresHoldingPiecesAttacked = w_kingView_e_4.squaresHoldingPiecesAttacked();
        assertThat(squaresHoldingPiecesAttacked.size(), equalTo(0));
    }

    @Test
    public void king_cannot_move_into_check() {
        chessBoard = putBlackPawnsInBoxWithRadiousTwoAround_E_4();
        position_e_4 = new BoardPosition(chessBoard, new Square(Column.E, Row.R4));

        w_kingView_e_4 = new KingView(position_e_4, Color.WHITE);

        List<Square> moveToSquares = w_kingView_e_4.moveToSquares();

        assertThat(moveToSquares.size(), equalTo(1));
        assertTrue(moveToSquares.contains(e_3));
    }

    private ChessBoard putBlackPawnsInBoxAround_E_4() {
        Piece b_pawn_e_5 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, e_5);
        Piece b_pawn_f_5 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, f_5);
        Piece b_pawn_f_4 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, f_4);
        Piece b_pawn_f_3 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, f_3);
        Piece b_pawn_e_3 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, e_3);
        Piece b_pawn_d_3 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_3);
        Piece b_pawn_d_4 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_4);
        Piece b_pawn_d_5 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_5);

        PutEvent put_b_pawn_e_5 = new PutEvent(b_pawn_e_5);
        PutEvent put_b_pawn_f_5 = new PutEvent(b_pawn_f_5);
        PutEvent put_b_pawn_f_4 = new PutEvent(b_pawn_f_4);
        PutEvent put_b_pawn_f_3 = new PutEvent(b_pawn_f_3);
        PutEvent put_b_pawn_e_3 = new PutEvent(b_pawn_e_3);
        PutEvent put_b_pawn_d_3 = new PutEvent(b_pawn_d_3);
        PutEvent put_b_pawn_d_4 = new PutEvent(b_pawn_d_4);
        PutEvent put_b_pawn_d_5 = new PutEvent(b_pawn_d_5);

        List<PutEvent> puts =
            Arrays.asList(put_b_pawn_e_5, put_b_pawn_f_5, put_b_pawn_f_4, put_b_pawn_f_3, put_b_pawn_e_3,
                put_b_pawn_d_3, put_b_pawn_d_4, put_b_pawn_d_5);

        for (PutEvent putEvent : puts) {
            chessBoard = chessBoard.playEvent(putEvent);
        }

        return chessBoard;

    }

    private ChessBoard putBlackPawnsInBoxWithRadiousTwoAround_E_4() {
        Piece b_pawn_e_6 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, e_6);
        Piece b_pawn_f_6 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, f_6);
        Piece b_pawn_g_6 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_6);
        Piece b_pawn_g_5 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_5);
        Piece b_pawn_g_4 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_4);
        Piece b_pawn_g_3 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_3);
        Piece b_pawn_g_2 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, g_2);
        Piece b_pawn_f_2 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, f_2);
        Piece b_pawn_e_2 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, e_2);
        Piece b_pawn_d_2 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_2);
        Piece b_pawn_c_2 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_2);
        Piece b_pawn_c_3 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_3);
        Piece b_pawn_c_4 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_4);
        Piece b_pawn_c_5 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_5);
        Piece b_pawn_c_6 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, c_6);
        Piece b_pawn_d_6 = PieceFactory.newPiece(Color.BLACK, Rank.Pawn, d_6);

        PutEvent put_b_pawn_e_6 = new PutEvent(b_pawn_e_6);
        PutEvent put_b_pawn_f_6 = new PutEvent(b_pawn_f_6);
        PutEvent put_b_pawn_g_6 = new PutEvent(b_pawn_g_6);
        PutEvent put_b_pawn_g_5 = new PutEvent(b_pawn_g_5);
        PutEvent put_b_pawn_g_4 = new PutEvent(b_pawn_g_4);
        PutEvent put_b_pawn_g_3 = new PutEvent(b_pawn_g_3);
        PutEvent put_b_pawn_g_2 = new PutEvent(b_pawn_g_2);
        PutEvent put_b_pawn_f_2 = new PutEvent(b_pawn_f_2);
        PutEvent put_b_pawn_e_2 = new PutEvent(b_pawn_e_2);
        PutEvent put_b_pawn_d_2 = new PutEvent(b_pawn_d_2);
        PutEvent put_b_pawn_c_2 = new PutEvent(b_pawn_c_2);
        PutEvent put_b_pawn_c_3 = new PutEvent(b_pawn_c_3);
        PutEvent put_b_pawn_c_4 = new PutEvent(b_pawn_c_4);
        PutEvent put_b_pawn_c_5 = new PutEvent(b_pawn_c_5);
        PutEvent put_b_pawn_c_6 = new PutEvent(b_pawn_c_6);
        PutEvent put_b_pawn_d_6 = new PutEvent(b_pawn_d_6);

        List<PutEvent> putEvents =
            Arrays.asList(put_b_pawn_e_6, put_b_pawn_f_6, put_b_pawn_g_6, put_b_pawn_g_5, put_b_pawn_g_4,
                put_b_pawn_g_3, put_b_pawn_g_2, put_b_pawn_f_2, put_b_pawn_e_2, put_b_pawn_d_2, put_b_pawn_c_2,
                put_b_pawn_c_3, put_b_pawn_c_4, put_b_pawn_c_5, put_b_pawn_c_6, put_b_pawn_d_6);

        for (PutEvent putEvent : putEvents) {
            chessBoard = chessBoard.playEvent(putEvent);
        }

        return chessBoard;

    }

}
