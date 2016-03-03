package model.piece;

import model.board.Square;
import model.enums.Color;
import model.enums.Rank;

public class PieceFactory {

    public static Piece newPiece(Rank rank, Color color) {
        return new Piece(rank, color, homeSquare(rank, color));
    }

    private static Square homeSquare(Rank rank, Color color) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Piece newPiece(Rank rank, Color color, Square homeSquare) {
        return new Piece(rank, color, homeSquare);
    }

}
