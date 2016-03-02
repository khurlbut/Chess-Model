package strategy;

import model.board.ChessBoard;
import model.board.GameEvent;
import model.enums.Color;

public interface GameStrategy {

    GameEvent nextMove(Color color, ChessBoard chessBoard);

}
