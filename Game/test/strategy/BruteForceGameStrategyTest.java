package strategy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.MoveEvent;
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

public class BruteForceGameStrategyTest {

    private BruteForceGameStrategy bruteForceGameStrategy;

    @Before
    public void setUp() {
        bruteForceGameStrategy = new BruteForceGameStrategy();
    }

    @Test
    public void test() {
        ChessBoard chessBoard = new ChessBoard();

        Square a_2 = new Square(Column.A, Row.R2);
        Square a_4 = new Square(Column.A, Row.R4);
        Square h_2 = new Square(Column.H, Row.R2);
        Square b_7 = new Square(Column.B, Row.R7);

        Piece w_pawn_a = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, a_2);
        Piece w_pawn_h = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, h_2);
        Piece b_pawn_b = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, b_7);

        PutEvent put_w_pawn_a = new PutEvent(w_pawn_a);
        PutEvent put_w_pawn_h = new PutEvent(w_pawn_h);
        PutEvent put_b_pawn_b = new PutEvent(b_pawn_b);

        chessBoard =
            chessBoard.playEvent(put_w_pawn_a).playEvent(put_w_pawn_h).playEvent(put_b_pawn_b)
                .setBoardForGameInProgress();

        GameEvent bestMove = new MoveEvent(a_2, a_4);

        assertThat(bruteForceGameStrategy.nextMove(Color.WHITE, chessBoard), equalTo(bestMove));

    }

}
