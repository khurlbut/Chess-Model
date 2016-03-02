package onceagain.board.perspectives;

import java.util.List;

import onceagain.board.ChessBoard;
import onceagain.board.Square;
import onceagain.enums.Color;

public interface RankPerspective {

    List<Square> radialsExcludingOccupied(ChessBoard chessBoard);

    List<Square> radialsOccupiedByColor(ChessBoard chessBoard, Color opponentColor);

}
