package onceagain.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import onceagain.ChessPiece;
import onceagain.Piece;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Rank;
import onceagain.enums.Row;

import org.junit.Before;
import org.junit.Test;

public class BoardSetterTest {
    private Piece w_pawn = ChessPiece.newPiece(Rank.Pawn, Color.WHITE);
    private Piece b_pawn = ChessPiece.newPiece(Rank.Pawn, Color.BLACK);

    private Piece w_rook = ChessPiece.newPiece(Rank.Rook, Color.WHITE);
    private Piece w_knight = ChessPiece.newPiece(Rank.Knight, Color.WHITE);
    private Piece w_bishop = ChessPiece.newPiece(Rank.Bishop, Color.WHITE);
    private Piece w_queen = ChessPiece.newPiece(Rank.Queen, Color.WHITE);
    private Piece w_king = ChessPiece.newPiece(Rank.King, Color.WHITE);

    private Piece b_rook = ChessPiece.newPiece(Rank.Rook, Color.BLACK);
    private Piece b_knight = ChessPiece.newPiece(Rank.Knight, Color.BLACK);
    private Piece b_bishop = ChessPiece.newPiece(Rank.Bishop, Color.BLACK);
    private Piece b_queen = ChessPiece.newPiece(Rank.Queen, Color.BLACK);
    private Piece b_king = ChessPiece.newPiece(Rank.King, Color.BLACK);

    private Square a_1 = new Square(Column.A, Row.R1);
    private Square b_1 = new Square(Column.B, Row.R1);
    private Square c_1 = new Square(Column.C, Row.R1);
    private Square d_1 = new Square(Column.D, Row.R1);
    private Square e_1 = new Square(Column.E, Row.R1);
    private Square f_1 = new Square(Column.F, Row.R1);
    private Square g_1 = new Square(Column.G, Row.R1);
    private Square h_1 = new Square(Column.H, Row.R1);

    private Square a_2 = new Square(Column.A, Row.R2);
    private Square b_2 = new Square(Column.B, Row.R2);
    private Square c_2 = new Square(Column.C, Row.R2);
    private Square d_2 = new Square(Column.D, Row.R2);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square f_2 = new Square(Column.F, Row.R2);
    private Square g_2 = new Square(Column.G, Row.R2);
    private Square h_2 = new Square(Column.H, Row.R2);

    private Square a_7 = new Square(Column.A, Row.R7);
    private Square b_7 = new Square(Column.B, Row.R7);
    private Square c_7 = new Square(Column.C, Row.R7);
    private Square d_7 = new Square(Column.D, Row.R7);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square f_7 = new Square(Column.F, Row.R7);
    private Square g_7 = new Square(Column.G, Row.R7);
    private Square h_7 = new Square(Column.H, Row.R7);

    private Square a_8 = new Square(Column.A, Row.R8);
    private Square b_8 = new Square(Column.B, Row.R8);
    private Square c_8 = new Square(Column.C, Row.R8);
    private Square d_8 = new Square(Column.D, Row.R8);
    private Square e_8 = new Square(Column.E, Row.R8);
    private Square f_8 = new Square(Column.F, Row.R8);
    private Square g_8 = new Square(Column.G, Row.R8);
    private Square h_8 = new Square(Column.H, Row.R8);

    private BoardSetter boardSetter;

    @Before
    public void setUp() {
        boardSetter = new BoardSetter();
    }

    @Test
    public void all_the_pieces_are_in_the_correct_places() {
        ChessBoard chessBoard = boardSetter.setBoard();

        assertWhitePawnsInPlace(chessBoard);
        assertBlackPawnsInPlace(chessBoard);
        assertWhitePiecesInPlace(chessBoard);
        assertBlackPiecesInPlace(chessBoard);
    }

    private void assertWhitePawnsInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_2), equalTo(w_pawn));
        assertThat(board.pieceAt(b_2), equalTo(w_pawn));
        assertThat(board.pieceAt(c_2), equalTo(w_pawn));
        assertThat(board.pieceAt(d_2), equalTo(w_pawn));
        assertThat(board.pieceAt(e_2), equalTo(w_pawn));
        assertThat(board.pieceAt(f_2), equalTo(w_pawn));
        assertThat(board.pieceAt(g_2), equalTo(w_pawn));
        assertThat(board.pieceAt(h_2), equalTo(w_pawn));
    }

    private void assertBlackPawnsInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_7), equalTo(b_pawn));
        assertThat(board.pieceAt(b_7), equalTo(b_pawn));
        assertThat(board.pieceAt(c_7), equalTo(b_pawn));
        assertThat(board.pieceAt(d_7), equalTo(b_pawn));
        assertThat(board.pieceAt(e_7), equalTo(b_pawn));
        assertThat(board.pieceAt(f_7), equalTo(b_pawn));
        assertThat(board.pieceAt(g_7), equalTo(b_pawn));
        assertThat(board.pieceAt(h_7), equalTo(b_pawn));
    }

    private void assertWhitePiecesInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_1), equalTo(w_rook));
        assertThat(board.pieceAt(b_1), equalTo(w_knight));
        assertThat(board.pieceAt(c_1), equalTo(w_bishop));
        assertThat(board.pieceAt(d_1), equalTo(w_queen));
        assertThat(board.pieceAt(e_1), equalTo(w_king));
        assertThat(board.pieceAt(f_1), equalTo(w_bishop));
        assertThat(board.pieceAt(g_1), equalTo(w_knight));
        assertThat(board.pieceAt(h_1), equalTo(w_rook));
    }

    private void assertBlackPiecesInPlace(ChessBoard board) {
        assertThat(board.pieceAt(a_8), equalTo(b_rook));
        assertThat(board.pieceAt(b_8), equalTo(b_knight));
        assertThat(board.pieceAt(c_8), equalTo(b_bishop));
        assertThat(board.pieceAt(d_8), equalTo(b_queen));
        assertThat(board.pieceAt(e_8), equalTo(b_king));
        assertThat(board.pieceAt(f_8), equalTo(b_bishop));
        assertThat(board.pieceAt(g_8), equalTo(b_knight));
        assertThat(board.pieceAt(h_8), equalTo(b_rook));
    }


}
