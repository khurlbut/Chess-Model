package model.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChessBoardTest {

    private Square a_1 = new Square(Column.A, Row.R1);
    private Square a_2 = new Square(Column.A, Row.R2);
    private Square b_3 = new Square(Column.B, Row.R3);
    private Square e_2 = new Square(Column.E, Row.R2);
    private Square e_3 = new Square(Column.E, Row.R3);
    private Square e_7 = new Square(Column.E, Row.R7);
    private Square e_4 = new Square(Column.E, Row.R4);

    private Piece w_queen_e_3 = null;
    private Piece w_rook_a_1 = null;
    private Piece w_bishop_a_1 = null;
    private Piece w_pawn_a_2 = null;
    private Piece b_pawn_b_3 = null;
    private Piece b_pawn_e_7 = null;

    private PutEvent put_w_rook_a_1 = null;
    private PutEvent put_w_queen_e_3 = null;
    private MoveEvent move_pawn_e_2_e_4 = null;
    private RemoveEvent remove_rook_a_1 = null;
    private CaptureEvent capture_w_queen_e_3_x_e7 = null;

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        w_pawn_a_2 = PieceFactory.newPiece(Rank.Pawn, Color.WHITE, a_2);
        b_pawn_b_3 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, b_3);
        b_pawn_e_7 = PieceFactory.newPiece(Rank.Pawn, Color.BLACK, e_7);
        w_rook_a_1 = PieceFactory.newPiece(Rank.Rook, Color.WHITE, a_1);
        w_bishop_a_1 = PieceFactory.newPiece(Rank.Bishop, Color.WHITE, a_1);
        w_queen_e_3 = PieceFactory.newPiece(Rank.Queen, Color.WHITE, e_3);

        put_w_rook_a_1 = new PutEvent(w_rook_a_1);
        put_w_queen_e_3 = new PutEvent(w_queen_e_3);
        remove_rook_a_1 = new RemoveEvent(a_1);
        move_pawn_e_2_e_4 = new MoveEvent(e_2, e_4);
        capture_w_queen_e_3_x_e7 = new CaptureEvent(e_3, e_7, b_pawn_e_7);
    }

    @Test
    public void default_constructor_creates_an_unSet_board() {
        assertThat(new ChessBoard().boardIsSet(), equalTo(false));
    }

    @Test
    public void setBoardForGame_sets_the_board() {
        assertThat(new ChessBoard().setBoardForGame().boardIsSet(), equalTo(true));
    }

    @Test
    public void setBoardForGameInProgress_sets_the_board() {
        chessBoard = new ChessBoard();
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard = chessBoard.put(new PutEvent(w_rook_a_1));
        assertThat(chessBoard.pieceAt(a_1), equalTo(w_rook_a_1));

        chessBoard = chessBoard.setBoardForGameInProgress();
        assertThat(chessBoard.boardIsSet(), equalTo(true));

        chessBoard = chessBoard.move(new MoveEvent(a_1, a_2));
        assertThat(chessBoard.pieceAt(a_1), equalTo(null));
        assertThat(chessBoard.pieceAt(a_2), equalTo(w_rook_a_1));
    }

    @Test
    public void playEvent_allows_a_Put_event_if_the_board_is_unset() {
        chessBoard = new ChessBoard().playEvent(put_w_rook_a_1);
        assertThat(chessBoard.pieceAt(put_w_rook_a_1.target()), equalTo(put_w_rook_a_1.piece()));
    }

    @Test
    public void playEvent_allows_a_Remove_event_if_the_board_is_unset() {
        chessBoard = new BoardSetter().setBoard();
        assertNotNull(chessBoard.pieceAt(remove_rook_a_1.source()));

        chessBoard = chessBoard.playEvent(remove_rook_a_1);
        assertNull(chessBoard.pieceAt(remove_rook_a_1.source()));
    }

    @Test
    public void playEvent_plays_a_Move_event() {
        chessBoard = new ChessBoard().setBoardForGame();
        Piece movingPiece = chessBoard.piece(move_pawn_e_2_e_4);
        chessBoard = chessBoard.playEvent(move_pawn_e_2_e_4);

        assertNull(chessBoard.pieceAt(move_pawn_e_2_e_4.source()));
        assertThat(chessBoard.pieceAt(move_pawn_e_2_e_4.target()), equalTo(movingPiece));
    }

    @Test
    public void playEvent_plays_a_Capture_event() {
        chessBoard = new BoardSetter().setBoard();
        chessBoard = chessBoard.put(put_w_queen_e_3).setBoardForGameInProgress();
        Piece capturingPiece = chessBoard.piece(capture_w_queen_e_3_x_e7);
        chessBoard = chessBoard.playEvent(capture_w_queen_e_3_x_e7);

        assertNull(chessBoard.pieceAt(capture_w_queen_e_3_x_e7.source()));
        assertThat(chessBoard.pieceAt(capture_w_queen_e_3_x_e7.target()), equalTo(capturingPiece));
    }

    @Test
    public void it_retains_a_history_of_GameEvents() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<GameEvent> events = chessBoard.gameEvents();

        assertThat(events.size(), equalTo(32));

        chessBoard = chessBoard.setBoardForGame();
        MoveEvent move = new MoveEvent(e_2, new Square(Column.E, Row.R4));

        chessBoard = chessBoard.move(move);
        assertThat(chessBoard.gameEvents().size(), equalTo(33));

        MoveEvent lastEvent = (MoveEvent) chessBoard.gameEvents().get(32);
        assertThat(lastEvent, equalTo(move));
    }

    @Test
    public void it_returns_a_list_of_pieces_by_color() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        List<Piece> blackPieces = chessBoard.piecesFor(Color.BLACK);

        assertThat(blackPieces.size(), equalTo(16));

        List<Piece> uniquePieces = new ArrayList<Piece>();
        for (Piece piece : blackPieces) {
            assertThat(piece.color(), equalTo(Color.BLACK));
            assertFalse(uniquePieces.contains(piece));
            uniquePieces.add(piece);
        }
    }

    @Test(expected = IllegalStateException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_after_the_board_is_set() {
        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard.playEvent(put_w_rook_a_1);
    }

    @Test(expected = IllegalStateException.class)
    public void playEvent_throws_exception_if_a_Remove_is_called_after_the_board_is_set() {
        chessBoard = new ChessBoard().setBoardForGame();
        chessBoard.remove(remove_rook_a_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Remove_is_called_on_an_empty_square() {
        chessBoard = new ChessBoard().remove(remove_rook_a_1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_on_an_occupied_square() {
        chessBoard = new ChessBoard().put(put_w_rook_a_1);
        chessBoard.playEvent(new PutEvent(w_bishop_a_1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void playEvent_throws_exception_if_a_Put_is_called_with_an_existing_piece_on_the_board() {
        chessBoard = new ChessBoard().put(put_w_rook_a_1);
        chessBoard.playEvent(new PutEvent(put_w_rook_a_1.piece()));
    }

    @Test(expected = IllegalStateException.class)
    public void setBoardInProgress_throws_exception_if_the_board_is_empty() {
        new ChessBoard().setBoardForGameInProgress();
    }

    @Test(expected = IllegalStateException.class)
    public void playing_a_Move_event_before_the_board_has_been_set_throws_an_exception() {
        Square a_3 = new Square(Column.A, Row.R3);
        chessBoard = new ChessBoard().put(new PutEvent(w_pawn_a_2));
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard.move(new MoveEvent(a_2, a_3));
    }

    @Test(expected = IllegalStateException.class)
    public void playing_a_Capture_event_before_the_board_has_been_set_throws_an_exception() {
        chessBoard = new ChessBoard().put(new PutEvent(w_pawn_a_2));
        chessBoard = new ChessBoard().put(new PutEvent(b_pawn_b_3));
        assertThat(chessBoard.boardIsSet(), equalTo(false));

        chessBoard.capture(new CaptureEvent(a_2, b_3, b_pawn_b_3));
    }

}