package onceagain;

import onceagain.board.ChessBoard;
import onceagain.board.Square;
import onceagain.board.perspectives.RankPerspective;
import onceagain.enums.Color;
import onceagain.enums.Rank;
import onceagain.movements.MoveStrategy;
import onceagain.movements.StrategySelector;
import events.CaptureEvent;
import events.MoveEvent;
import exceptions.IllegalStrategyException;

public class ChessPiece implements Piece {

    private final Rank rank;
    private final Color color;
    private final MoveStrategy moveStrategy;

    private static StrategySelector selector = new StrategySelector();

    private ChessPiece(Rank rank, Color color, MoveStrategy strategy) {
        if (rank == null || color == null) {
            throw new IllegalArgumentException("Constructor does not allow null(s)!");
        }
        if (strategy == null) {
            throw new IllegalStrategyException("Strategy not found for " + rank.name());
        }
        if (!rank.equals(strategy.rank())) {
            throw new IllegalStrategyException("");
        }
        this.rank = rank;
        this.color = color;
        this.moveStrategy = strategy;
    }

    public Color color() {
        return color;
    }

    Rank rank() {
        return rank;
    }

    @Override
    public boolean isLegalMove(MoveEvent move, ChessBoard chessBoard) {
        RankPerspective s = move.source().rankPerspective(rank);
        return s.radialsExcludingOccupied(chessBoard).contains(move.target());
    }

    @Override
    public boolean isLegalCapture(CaptureEvent capture, ChessBoard chessBoard) {
        RankPerspective s = capture.source().rankPerspective(rank);
        Square target = capture.target();
        Color opponentColor = opponentColor(target, chessBoard);

        return s.radialsOccupiedByColor(chessBoard, opponentColor).contains(target);
    }

    private Color opponentColor(Square target, ChessBoard chessBoard) {
        Piece opponentPiece = chessBoard.pieceAt(target);
        Color opponentColor = opponentPiece.color();

        return opponentColor;
    }


    @Override
    public String toString() {
        return color + " " + rank;
    }

    public static Piece newPiece(Rank rank, Color color) {
        return new ChessPiece(rank, color, selector.selectMoveStrategy(rank));
    }

    public static void setStrategySelector(StrategySelector strategySelector) {
        selector = strategySelector;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        result = prime * result + ((moveStrategy == null) ? 0 : moveStrategy.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChessPiece other = (ChessPiece) obj;
        if (color != other.color)
            return false;
        if (rank != other.rank)
            return false;
        if (moveStrategy == null) {
            if (other.moveStrategy != null)
                return false;
        } else if (!moveStrategy.equals(other.moveStrategy))
            return false;
        return true;
    }

}
