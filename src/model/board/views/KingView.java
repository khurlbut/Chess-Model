package model.board.views;

import java.util.ArrayList;
import java.util.List;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.Square;
import model.enums.Color;
import model.enums.ViewVector;
import model.enums.MovementLimitations;
import model.piece.Piece;

public final class KingView extends RadiatingView {

    private static final ViewVector[] KING_MOVES = { ViewVector.UP, ViewVector.RIGHT_UP, ViewVector.RIGHT,
            ViewVector.RIGHT_DOWN, ViewVector.DOWN, ViewVector.LEFT_DOWN, ViewVector.LEFT, ViewVector.LEFT_UP };
    private Color color;
    private ChessBoard chessBoard;

    KingView(Color color, BoardPosition boardPosition) {
        super(color, boardPosition, KING_MOVES, MovementLimitations.ONE_UNIT_SQUARE);

        this.color = color;
        this.chessBoard = boardPosition.chessBoard();
    }

    @Override
    public List<Square> moveToSquares() {
        List<Square> moveToSquares = new ArrayList<Square>();
        List<Square> openSquares = super.moveToSquares();

        for (Square availableSquare : openSquares) {
            if (piecesAttackingAt(availableSquare).size() == 0) {
                moveToSquares.add(availableSquare);
            }
        }

        return moveToSquares;
    }

    private List<Piece> piecesAttackingAt(Square s) {
        List<Piece> attackingPieces = new ArrayList<Piece>();
        List<Piece> opponentPieces = chessBoard.piecesFor(color.opponentColor());
        for (Piece opponentPiece : opponentPieces) {
            if (opponentPiece.threatenedSquares(chessBoard).contains(s)) {
                attackingPieces.add(opponentPiece);
            }
        }
        return attackingPieces;
    }

}
