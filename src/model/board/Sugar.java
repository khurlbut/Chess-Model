package model.board;

import static model.piece.PieceFactory.newPiece;

import java.util.Arrays;
import java.util.List;

import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;

public final class Sugar {

    public static PutEvent put(Piece piece) {
        return new PutEvent(piece);
    }

    public static PutEvent put(Color color, Rank rank, Square square) {
        return new PutEvent(newPiece(color, rank, square));
    }

    public static MoveEvent move(Square source, Square target) {
        return new MoveEvent(source, target);
    }

    public static CaptureEvent capture(Piece attacker, Piece target, ChessBoard board) {
        Square attackingSquare = board.squareHolding(attacker);
        Square targetSquare = board.squareHolding(target);
        return capture(attackingSquare, targetSquare, target);
    }

    public static CaptureEvent capture(Square attackingSquare, Square targetSquare, Piece target) {
        return new CaptureEvent(attackingSquare, targetSquare, target);
    }

    public static RemoveEvent remove(Square square) {
        return new RemoveEvent(square);
    }

    public static Square square(Column column, Row row) {
        return new Square(column, row);
    }

    public static BoardPosition position(Column column, Row row, ChessBoard board) {
        return new BoardPosition(square(column, row), board);
    }

    public static ChessBoard play(List<GameEvent> events, ChessBoard board) {
        for (GameEvent event : events) {
            board = board.playEvent(event);
        }
        return board;
    }

    public static List<GameEvent> eventList(GameEvent... events) {
        return Arrays.asList(events);
    }

    public static boolean hasMoved(Piece piece, Square squarePieceIsOn) {
        return piece != null && !piece.homeSquare().equals(squarePieceIsOn);
    }

    public static boolean isCollaborator(Color color, Piece otherPiece) {
        return otherPiece != null && otherPiece.color().equals(color);
    }

}
