package onceagain.movements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
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

import events.MoveEvent;
import events.PutEvent;

@RunWith(MockitoJUnitRunner.class)
public class QueenMoveStrategyTest {

    private Square d_4 = new Square(Column.D, Row.R4);
    private Square d_5 = new Square(Column.D, Row.R5);

    private ChessBoard chessBoard;
    private Piece w_queen = null;
    private PutEvent w_queen_to_d4 = null;

    @Mock
    private StrategySelector strategySelector;

    private QueenStrategy queenMoves = new QueenStrategy();

    @Before
    public void setUp() {
        queenMoves = new QueenStrategy();

        ChessPiece.setStrategySelector(strategySelector);
        when(strategySelector.selectMoveStrategy(Rank.Queen)).thenReturn(queenMoves);

        w_queen = ChessPiece.newPiece(Rank.Queen, Color.WHITE);
        w_queen_to_d4 = new PutEvent(w_queen, d_4);

        chessBoard = new ChessBoard().put(w_queen_to_d4);
        chessBoard = chessBoard.setBoardForGameInProgress();
    }

    @Test
    public void test() {
        boolean legalMove = queenMoves.isLegalMove(new MoveEvent(d_4, d_5), chessBoard);
        assertThat(legalMove, equalTo(true));
    }

}
