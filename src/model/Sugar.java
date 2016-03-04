package model;

import java.util.Arrays;
import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.PutEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

public final class Sugar {
    public static PutEvent put(Piece piece) {
        return new PutEvent(piece);
    }

    public static PutEvent put(Color color, Rank rank, Square square) {
        return new PutEvent(piece(color, rank, square));
    }

    public static Piece piece(Color color, Rank rank, Square square) {
        return PieceFactory.newPiece(color, rank, square);
    }

    public static Square square(Column column, Row row) {
        return new Square(column, row);
    }

    public static BoardPosition position(ChessBoard board, Column column, Row row) {
        return new BoardPosition(board, square(column, row));
    }

    public static ChessBoard play(ChessBoard board, List<GameEvent> events) {
        for (GameEvent event : events) {
            board = board.playEvent(event);
        }
        return board;
    }

    public static List<GameEvent> eventList(GameEvent... events) {
        return Arrays.asList(events);
    }

    public static boolean hasMoved(Piece piece, Square currentSquare) {
        return piece != null && !piece.homeSquare().equals(currentSquare);
    }

}
