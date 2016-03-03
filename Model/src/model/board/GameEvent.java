package model.board;


public interface GameEvent {

    Square target();

    Square source();

    ChessBoard playEvent(ChessBoard chessBoard);

    int type();

}
