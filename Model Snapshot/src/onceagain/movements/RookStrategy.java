package onceagain.movements;

import onceagain.board.ChessBoard;
import onceagain.enums.Rank;
import events.CaptureEvent;
import events.MoveEvent;

public class RookStrategy implements MoveStrategy {

    @Override
    public Rank rank() {
        return Rank.Rook;
    }

    @Override
    public boolean isLegalMove(MoveEvent move, ChessBoard chessBoard) {
        return false;
    }

    @Override
    public boolean isLegalCapture(CaptureEvent capture, ChessBoard chessBoard) {
        return false;
    }

}
