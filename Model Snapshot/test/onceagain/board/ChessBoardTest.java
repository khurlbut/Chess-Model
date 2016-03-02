package onceagain.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import onceagain.ChessPiece;
import onceagain.Piece;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Rank;
import onceagain.enums.Row;
import onceagain.movements.MockStrategySelector;
import onceagain.movements.MoveStrategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import events.CaptureEvent;
import events.MoveEvent;
import events.PutEvent;
import events.RemoveEvent;
import exceptions.IllegalGameEventException;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardTest {
    private Piece w_pawn = null;
    private Piece b_pawn = null;

    private Piece w_rook = null;
    private Piece w_bishop = null;

    private PutEvent putEvent = null;
    private RemoveEvent removeEvent = null;

    private MoveEvent moveEvent = null;
    private CaptureEvent captureEvent = null;

    private Square a_1 = new Square(Column.A, Row.R1);
    private Square a_2 = new Square(Column.A, Row.R2);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square h_8 = new Square(Column.H, Row.R8);
    private Square e_4 = new Square(Column.E, Row.R4);

    private MockStrategySelector strategySelector;

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        strategySelector = new MockStrategySelector();
        ChessPiece.setStrategySelector(strategySelector);

        w_pawn = ChessPiece.newPiece(Rank.Pawn, Color.WHITE);
        b_pawn = ChessPiece.newPiece(Rank.Pawn, Color.BLACK);

        w_rook = ChessPiece.newPiece(Rank.Rook, Color.WHITE);
        w_bishop = ChessPiece.newPiece(Rank.Bishop, Color.WHITE);

        putEvent = new PutEvent(w_rook, a_1);
        removeEvent = new RemoveEvent(a_1);
        moveEvent = new MoveEvent(e_2, e_4);
        captureEvent = new CaptureEvent(e_2, e_7);
    }

    @Test
    public void default_constructor_creates_an_unSet_chess_board() {
        chessBoard = new ChessBoard().setBoardForGame();
        assertThat(new ChessBoard().boardIsSet(), equalTo(false));
    }

    @Test
    public void setBoard_sets_the_board() {
        chessBoard = new ChessBoard().setBoardForGame();
        assertThat(chessBoard.boardIsSet(), equalTo(true));
    }

    @Test
    public void setBoardForGameInProgress_sets_the_board() {
        chessBoard = new ChessBoard();
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard = chessBoard.put(new PutEvent(w_rook, a_1));
        assertThat(chessBoard.pieceAt(a_1), equalTo(w_rook));

        chessBoard = chessBoard.setBoardForGameInProgress();
        assertThat(chessBoard.boardIsSet(), equalTo(true));

        chessBoard = chessBoard.move(new MoveEvent(a_1, a_2));
        assertThat(chessBoard.pieceAt(a_1), equalTo(null));
        assertThat(chessBoard.pieceAt(a_2), equalTo(w_rook));
    }

    @Test
    public void playEvent_allows_a_Put_event_if_the_board_is_unset() {
        chessBoard = new ChessBoard().playEvent(putEvent);
        assertThat(chessBoard.pieceAt(putEvent.target()), equalTo(putEvent.piece()));
    }

    @Test
    public void playEvent_allows_a_Remove_event_if_the_board_is_unset() {
        chessBoard = new BoardSetter().setBoard();
        assertNotNull(chessBoard.pieceAt(removeEvent.source()));

        chessBoard = chessBoard.playEvent(removeEvent);
        assertNull(chessBoard.pieceAt(removeEvent.source()));
    }

    @Test
    public void playEvent_plays_a_Move_event() {
        chessBoard = new ChessBoard().setBoardForGame();
        Piece movingPiece = chessBoard.piece(moveEvent);
        chessBoard = chessBoard.playEvent(moveEvent);

        assertNull(chessBoard.pieceAt(moveEvent.source()));
        assertThat(chessBoard.pieceAt(moveEvent.target()), equalTo(movingPiece));
    }

    @Test
    public void playEvent_plays_a_Capture_event() {
        chessBoard = new ChessBoard().setBoardForGame();
        Piece capturingPiece = chessBoard.piece(captureEvent);
        chessBoard = chessBoard.playEvent(captureEvent);

        assertNull(chessBoard.pieceAt(captureEvent.source()));
        assertThat(chessBoard.pieceAt(captureEvent.target()), equalTo(capturingPiece));
    }

    @Test(expected = IllegalStateException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_after_the_board_is_set() {
        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard.playEvent(putEvent);
    }

    @Test(expected = IllegalStateException.class)
    public void playEvent_throws_exception_if_a_Remove_is_called_after_the_board_is_set() {
        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard.remove(removeEvent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Remove_is_called_on_an_empty_square() {
        chessBoard = new ChessBoard().remove(removeEvent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_on_an_occupied_square() {
        chessBoard = new ChessBoard().put(putEvent);
        chessBoard.playEvent(new PutEvent(w_bishop, putEvent.target()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_with_an_existing_piece_on_the_board() {
        chessBoard = new ChessBoard().put(putEvent);
        chessBoard.playEvent(new PutEvent(putEvent.piece(), h_8));
    }

    @Test(expected = IllegalGameEventException.class)
    public void playEvent_throws_exception_if_a_Move_is_called_and_the_target_is_not_a_Strategy_LegalMove() {
        MoveStrategy pawnStrategy = strategySelector.selectMoveStrategy(Rank.Pawn);
        when(pawnStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(false);

        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard = chessBoard.playEvent(moveEvent);
    }

    @Test(expected = IllegalGameEventException.class)
    public void playEvent_throws_exception_if_a_Capture_is_called_and_the_target_is_not_a_Strategy_LegalMove() {
        MoveStrategy pawnStrategy = strategySelector.selectMoveStrategy(Rank.Pawn);
        when(pawnStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(false);

        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard = chessBoard.playEvent(captureEvent);
    }

    @Test(expected = IllegalStateException.class)
    public void setBoardInProgress_throws_exception_if_the_board_is_empty() {
        new ChessBoard().setBoardForGameInProgress();
    }

    @Test(expected = IllegalStateException.class)
    public void playing_a_Move_event_before_the_board_has_been_set_throws_an_exception() {
        Square a_3 = new Square(Column.A, Row.R3);
        chessBoard = new ChessBoard().put(new PutEvent(w_pawn, a_2));
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard.move(new MoveEvent(a_2, a_3));
    }

    @Test(expected = IllegalStateException.class)
    public void playing_a_Capture_event_before_the_board_has_been_set_throws_an_exception() {
        Square b_3 = new Square(Column.B, Row.R3);
        chessBoard = new ChessBoard().put(new PutEvent(w_pawn, a_2));
        chessBoard = new ChessBoard().put(new PutEvent(b_pawn, b_3));
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard.capture(new CaptureEvent(a_2, b_3));
    }

}
