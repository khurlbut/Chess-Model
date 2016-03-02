package onceagain;

import onceagain.board.ChessBoard;
import onceagain.enums.Color;
import events.CaptureEvent;
import events.MoveEvent;

public interface Piece {

    boolean isLegalMove(MoveEvent move, ChessBoard chessBoard);

    boolean isLegalCapture(CaptureEvent capture, ChessBoard chessBoard);

    Color color();

}
