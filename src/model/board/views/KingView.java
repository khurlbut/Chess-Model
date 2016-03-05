package model.board.views;

import java.util.ArrayList;
import java.util.List;

import model.board.BoardPosition;
import model.board.Square;
import model.enums.Color;
import model.enums.MovementLimitations;
import model.enums.ViewVector;
import model.piece.Piece;

public final class KingView extends RadiatingView {

    private static final ViewVector[] KING_MOVES = { ViewVector.UP, ViewVector.RIGHT_UP, ViewVector.RIGHT,
            ViewVector.RIGHT_DOWN, ViewVector.DOWN, ViewVector.LEFT_DOWN, ViewVector.LEFT, ViewVector.LEFT_UP };

    KingView(Color color, BoardPosition boardPosition) {
        super(color, boardPosition, KING_MOVES, MovementLimitations.ONE_UNIT_SQUARE);
    }

    @Override
    public List<Square> moveToSquares() {
        List<Square> moveToSquares = new ArrayList<Square>();
        List<Square> openSquares = super.moveToSquares();

        for (Square availableSquare : openSquares) {
            if (!squareIsUnderAttack(availableSquare)) {
                moveToSquares.add(availableSquare);
            }
        }

        return moveToSquares;
    }

    private boolean squareIsUnderAttack(Square availableSquare) {
        return piecesAttackingAt(availableSquare).size() == 0;
    }

    private List<Piece> piecesAttackingAt(Square square) {
        List<Piece> attackingPieces = new ArrayList<Piece>();
        List<Piece> opponentPieces = chessBoard.piecesFor(viewColor.opponentColor());

        for (Piece opponentPiece : opponentPieces) {
            if (opponentPiece.threatenedSquares(chessBoard).contains(square)) {
                attackingPieces.add(opponentPiece);
            }
        }

        return attackingPieces;
    }

}
