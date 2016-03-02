package events;

import onceagain.Piece;
import onceagain.board.ChessBoard;
import onceagain.board.Square;
import exceptions.ConstructorArgsExcetpion;

public class PutEvent implements GameEvent {

    private Piece piece;
    private Square target;

    public PutEvent(Piece piece, Square target) {
        if (piece == null || target == null) {
            throw new ConstructorArgsExcetpion("Constructor does not allow null(s)!");
        }
        this.piece = piece;
        this.target = target;
    }

    @Override
    public Square target() {
        return target;
    }

    @Override
    public Square source() {
        throw new UnsupportedOperationException("There is no source. A Put comes from nothing.");
    }

    @Override
    public ChessBoard play(ChessBoard chessBoard) {
        return chessBoard.put(this);
    }

    public Piece piece() {
        return piece;
    }

    @Override
    public String toString() {
        return "put " + piece + " " + target;
    }

}
