package events;

import onceagain.board.ChessBoard;
import onceagain.board.Square;

public interface GameEvent {

    Square target();

    Square source();

    ChessBoard play(ChessBoard chessBoard);

}
