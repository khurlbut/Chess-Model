package onceagain.movements;

import java.util.HashMap;
import java.util.Map;

import onceagain.enums.Rank;

public class StrategySelector {
    private Map<Rank, MoveStrategy> strategies = new HashMap<Rank, MoveStrategy>();

    private MoveStrategy pawnStrategy = new PawnStrategy();
    private MoveStrategy rookStrategy = new RookStrategy();
    private MoveStrategy knightStrategy = new KnightStrategy();
    private MoveStrategy bishopStrategy = new BishopStrategy();
    private MoveStrategy queenStrategy = new QueenStrategy();
    private MoveStrategy kingStrategy = new KingStrategy();

    public StrategySelector() {
        strategies.put(Rank.Pawn, pawnStrategy);
        strategies.put(Rank.Rook, rookStrategy);
        strategies.put(Rank.Knight, knightStrategy);
        strategies.put(Rank.Bishop, bishopStrategy);
        strategies.put(Rank.Queen, queenStrategy);
        strategies.put(Rank.King, kingStrategy);
    }

    public MoveStrategy selectMoveStrategy(Rank rank) {
        return strategies.get(rank);
    }

}
