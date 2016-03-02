package onceagain.movements;

import onceagain.board.ChessBoard;
import onceagain.enums.Rank;
import events.CaptureEvent;
import events.MoveEvent;

public interface MoveStrategy {

    Rank rank();

    boolean isLegalMove(MoveEvent move, ChessBoard chessBoard);

    boolean isLegalCapture(CaptureEvent capture, ChessBoard chessBoard);

}
