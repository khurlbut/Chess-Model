package strategy;

import static model.board.Sugar.eventList;
import static model.board.Sugar.move;
import static model.board.Sugar.play;
import static model.board.Sugar.put;
import static model.board.Sugar.square;
import static model.piece.PieceFactory.newPiece;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BruteForceGameStrategyTest {

    private BruteForceGameStrategy bruteForceGameStrategy;

    @Before
    public void setUp() {
        bruteForceGameStrategy = new BruteForceGameStrategy();
    }

    @Test
    @Ignore("not yet ready for prime time")
    public void test() {
        ChessBoard chessBoard = new ChessBoard();

        Square a_2 = square(Column.A, Row.R2);
        Square h_2 = square(Column.H, Row.R2);
        Square h_3 = square(Column.H, Row.R3);
        Square b_7 = square(Column.B, Row.R7);

        Piece w_pawn_a = newPiece(Color.WHITE, Rank.Pawn, a_2);
        Piece w_pawn_h = newPiece(Color.WHITE, Rank.Pawn, h_2);
        Piece b_pawn_b = newPiece(Color.BLACK, Rank.Pawn, b_7);

        List<GameEvent> putEvents = eventList(put(w_pawn_a), put(w_pawn_h), put(b_pawn_b));

        chessBoard = play(putEvents, chessBoard).setBoardForGameInProgress();

        GameEvent bestMove = move(h_2, h_3);

        assertThat(bruteForceGameStrategy.nextMove(Color.WHITE, chessBoard), equalTo(bestMove));

    }

}
