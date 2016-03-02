package onceagain.movements;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import onceagain.board.ChessBoard;
import onceagain.enums.Rank;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import events.CaptureEvent;
import events.MoveEvent;

public class MockStrategySelector extends StrategySelector {

    @Mock
    private StrategySelector strategySelector;
    @Mock
    private MoveStrategy pawnStrategy;
    @Mock
    private MoveStrategy rookStrategy;
    @Mock
    private MoveStrategy knightStrategy;
    @Mock
    private MoveStrategy bishopStrategy;
    @Mock
    private MoveStrategy kingStrategy;
    @Mock
    private MoveStrategy queenStrategy;

    public MockStrategySelector() {
        MockitoAnnotations.initMocks(this);
        setUpStrategySelector();
    }

    @Override
    public MoveStrategy selectMoveStrategy(Rank rank) {
        return strategySelector.selectMoveStrategy(rank);
    };

    private void setUpStrategySelector() {
        when(strategySelector.selectMoveStrategy(Rank.Pawn)).thenReturn(pawnStrategy);
        when(strategySelector.selectMoveStrategy(Rank.Rook)).thenReturn(rookStrategy);
        when(strategySelector.selectMoveStrategy(Rank.Knight)).thenReturn(knightStrategy);
        when(strategySelector.selectMoveStrategy(Rank.Bishop)).thenReturn(bishopStrategy);
        when(strategySelector.selectMoveStrategy(Rank.King)).thenReturn(kingStrategy);
        when(strategySelector.selectMoveStrategy(Rank.Queen)).thenReturn(queenStrategy);

        when(pawnStrategy.rank()).thenReturn(Rank.Pawn);
        when(pawnStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(pawnStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        when(rookStrategy.rank()).thenReturn(Rank.Rook);
        when(rookStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(rookStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        when(knightStrategy.rank()).thenReturn(Rank.Knight);
        when(knightStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(knightStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        when(bishopStrategy.rank()).thenReturn(Rank.Bishop);
        when(bishopStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(bishopStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        when(queenStrategy.rank()).thenReturn(Rank.Queen);
        when(queenStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(queenStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);

        when(kingStrategy.rank()).thenReturn(Rank.King);
        when(kingStrategy.isLegalMove(any(MoveEvent.class), any(ChessBoard.class))).thenReturn(true);
        when(kingStrategy.isLegalCapture(any(CaptureEvent.class), any(ChessBoard.class))).thenReturn(true);
    }

}
