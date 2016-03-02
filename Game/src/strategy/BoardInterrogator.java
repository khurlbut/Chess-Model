package strategy;

import java.util.List;

import model.board.ChessBoard;
import model.enums.Color;
import model.piece.Piece;

class BoardInterrogator {

    private int materialGrade;
    private int territoryGrade;
    private int positionGrade;
    private int collaborationGrade;

    BoardGrade grade(ChessBoard chessBoard, Color color) {
        List<Piece> pieces = chessBoard.piecesFor(color);
        List<Piece> opponentPieces = chessBoard.piecesFor(color.opponentColor());

        interrogate(pieces, opponentPieces, chessBoard);

        return new BoardGrade(materialGrade, territoryGrade, positionGrade, collaborationGrade);
    }

    private void interrogate(List<Piece> pieces, List<Piece> opponentPieces, ChessBoard chessBoard) {
        int material = 0;
        int opponentMaterial = 0;

        int territory = 0;
        int opponentTerritory = 0;

        int position = 0;
        int collaboration = 0;

        for (Piece piece : pieces) {
            material += piece.points();
            territory += piece.threatenedSquares(chessBoard).size();
            collaboration += piece.piecesDefended(chessBoard).size();
            position += position(chessBoard, position, piece);
        }

        for (Piece opponentPiece : opponentPieces) {
            opponentMaterial += opponentPiece.points();
            opponentTerritory += opponentPiece.threatenedSquares(chessBoard).size();
        }

        this.materialGrade = material - opponentMaterial;
        this.territoryGrade = territory - opponentTerritory;
        this.positionGrade = position;
        this.collaborationGrade = collaboration;
    }

    private int position(ChessBoard chessBoard, int position, Piece piece) {
        List<Piece> piecesAttacked = piece.piecesAttacked(chessBoard);
        List<Piece> attackers = piece.opponentAttackers(chessBoard);
        List<Piece> defenders = piece.collaboratorDefenders(chessBoard);

        for (Piece attackedPiece : piecesAttacked) {
            if (pieceUndefended(attackedPiece, chessBoard) || pieceOffersSacrifice(piece, attackedPiece)) {
                position += attackedPiece.rank().value();
            }
        }

        int attackerCount = attackers.size();
        int defenderCount = defenders.size();

        if (defenderCount < attackerCount || sacrificeAttack(position, piece, attackers)) {
            position -= piece.rank().value();
        }

        return position;
    }

    private boolean pieceOffersSacrifice(Piece piece, Piece attackedPiece) {
        return attackedPiece.rank().value() > piece.rank().value();
    }

    private boolean pieceUndefended(Piece attackedPiece, ChessBoard chessBoard) {
        return attackedPiece.collaboratorDefenders(chessBoard).size() == 0;
    }

    private boolean sacrificeAttack(int position, Piece piece, List<Piece> attackers) {
        for (Piece attacker : attackers) {
            if (attacker.rank().value() < piece.rank().value()) {
                return true;
            }
        }
        return false;
    }

}
