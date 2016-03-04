package model.piece;

import model.board.Square;
import model.enums.Color;
import model.enums.Rank;

public class PieceFactory {

    public static Piece newPiece(Rank rank, Color color) {
        return new Piece(color, rank, homeSquare(color, rank));
    }

    private static Square homeSquare(Color color, Rank rank) {
        // TODO Auto-generated method stub
        return null;
    }

    public static Piece newPiece(Color color, Rank rank, Square homeSquare) {
        return new Piece(color, rank, homeSquare);
    }

}
