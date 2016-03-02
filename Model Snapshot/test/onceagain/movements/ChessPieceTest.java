package onceagain.movements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import onceagain.ChessPiece;
import onceagain.Piece;
import onceagain.board.ChessBoard;
import onceagain.board.Square;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Rank;
import onceagain.enums.Row;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import events.CaptureEvent;
import events.MoveEvent;
import events.PutEvent;

@RunWith(MockitoJUnitRunner.class)
public class ChessPieceTest {

    @Mock
    private StrategySelector strategySelector;
    @Mock
    private MoveStrategy moveStrategy;

    private Square d_4 = new Square(Column.D, Row.R4);
    private Square d_5 = new Square(Column.D, Row.R5);
    private Square d_7 = new Square(Column.D, Row.R7);

    private PutEvent putPiece_d_4;
    private PutEvent putPiece_d_7;
    private Piece piece_d_4;
    private Piece piece_d_7;

    private ChessBoard chessBoard;

    @Before
    public void setUp() {
        ChessPiece.setStrategySelector(strategySelector);
        when(strategySelector.selectMoveStrategy(Rank.Queen)).thenReturn(moveStrategy);
        when(moveStrategy.rank()).thenReturn(Rank.Queen);
        when(moveStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(moveStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        piece_d_4 = ChessPiece.newPiece(Rank.Queen, Color.WHITE);
        piece_d_7 = ChessPiece.newPiece(Rank.Queen, Color.BLACK);

        putPiece_d_4 = new PutEvent(piece_d_4, d_4);
        putPiece_d_7 = new PutEvent(piece_d_7, d_7);

        chessBoard = new ChessBoard();

        chessBoard = chessBoard.put(putPiece_d_4);
        chessBoard = chessBoard.put(putPiece_d_7);
        chessBoard = chessBoard.setBoardForGameInProgress();
    }

    @Test
    public void piece_name_format_is_as_follows() {
        assertThat(piece_d_4.toString(), equalTo("w Queen"));
    }

    @Test
    public void is_legal_move_invokes_same_method_on_the_move_strategy() {
        MoveEvent move = new MoveEvent(d_4, d_5);
        chessBoard.playEvent(move);

        verify(moveStrategy).isLegalMove(move, chessBoard);
    }

    @Test
    public void is_legal_capture_invokes_same_method_on_the_move_strategy() {
        CaptureEvent capture = new CaptureEvent(d_4, d_7);
        chessBoard.playEvent(capture);

        verify(moveStrategy).isLegalCapture(capture, chessBoard);
    }

}
