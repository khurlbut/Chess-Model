package events;

import onceagain.board.ChessBoard;
import onceagain.board.Square;
import exceptions.ConstructorArgsExcetpion;

public class RemoveEvent implements GameEvent {

    private Square source;

    public RemoveEvent(Square source) {
        if (source == null) {
            throw new ConstructorArgsExcetpion("Constructor does not allow null!");
        }
        this.source = source;
    }

    @Override
    public Square target() {
        throw new UnsupportedOperationException("There is no target. A Remove goes nowhere.");
    }

    @Override
    public Square source() {
        return source;
    }

    @Override
    public ChessBoard play(ChessBoard chessBoard) {
        return chessBoard.remove(this);
    }

    @Override
    public String toString() {
        return "remove " + source;
    }

}
