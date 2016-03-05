package model.board.views;

import static model.board.Sugar.position;
import model.board.ChessBoard;
import model.enums.Color;
import model.enums.Column;
import model.enums.Row;

public class ViewSugar {

    public static KingView kingView(Color color, Column column, Row row, ChessBoard board) {
        return new KingView(color, position(column, row, board));
    }

    public static QueenView queenView(Color color, Column column, Row row, ChessBoard board) {
        return new QueenView(color, position(column, row, board));
    }

    public static BishopView bishopView(Color color, Column column, Row row, ChessBoard board) {
        return new BishopView(color, position(column, row, board));
    }

    public static KnightView knightView(Color color, Column column, Row row, ChessBoard board) {
        return new KnightView(color, position(column, row, board));
    }

    public static RookView rookView(Color color, Column column, Row row, ChessBoard board) {
        return new RookView(color, position(column, row, board));
    }

    public static PawnView pawnView(Color color, Column column, Row row, ChessBoard board) {
        return new PawnView(color, position(column, row, board));
    }

}
