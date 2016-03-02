package events;

import onceagain.board.ChessBoard;
import onceagain.board.Square;
import exceptions.ConstructorArgsExcetpion;

public class MoveEvent implements GameEvent {

    private Square source;
    private Square target;

    public MoveEvent(Square source, Square target) {
        if (source == null || target == null) {
            throw new ConstructorArgsExcetpion("Constructor does not allow null(s)!");
        }
        this.source = source;
        this.target = target;
    }

    @Override
    public Square source() {
        return source;
    }

    @Override
    public Square target() {
        return target;
    }

    @Override
    public ChessBoard play(ChessBoard chessBoard) {
        return chessBoard.move(this);
    }

    @Override
    public String toString() {
        return source + " --> " + target;
    }

}
