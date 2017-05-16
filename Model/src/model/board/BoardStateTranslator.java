package model.board;

import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;

public class BoardStateTranslator {

    public String boardToString(ChessBoard board) {

        StringBuffer b = new StringBuffer(255);

        for (Row row : Row.values()) {
            for (Column col : Column.values()) {
                Piece p = board.pieceAt(new Square(col, row));
                b.append(compressedIdOf(p)).append(",");
            }
        }


        // Remove trailing comma
        b.setLength(b.length() - 1);

        System.out.println(b.length());

        return (b.toString());
    }

    public String compressedIdOf(Piece p) {
        if (null == p) {
            return "''";
        }
        return "'" + pieceToString(p).replace(" ", "").substring(0, 2) + "'";
    }

    private String pieceToString(Piece p) {
        String compressedId;
        if (Rank.Knight.equals(p.rank())) {
            // Knight is a special case since K is used for King.
            compressedId = p.toString().replace("K", "N");
        } else {
            compressedId = p.toString();
        }
        return compressedId;
    }

}
