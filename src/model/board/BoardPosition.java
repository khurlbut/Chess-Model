package model.board;


public class BoardPosition {
    private ChessBoard chessBoard;
    private Square square;

    public BoardPosition(ChessBoard chessBoard, Square square) {
        this.chessBoard = chessBoard;
        this.square = square;
    }

    public ChessBoard chessBoard() {
        return chessBoard;
    }

    public Square square() {
        return square;
    }

}
