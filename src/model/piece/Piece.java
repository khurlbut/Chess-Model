package model.piece;

import static model.Sugar.capture;
import static model.Sugar.move;
import static model.board.views.RankViewFactory.rankView;

import java.util.ArrayList;
import java.util.List;

import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.Square;
import model.board.views.RankView;
import model.enums.Color;
import model.enums.Rank;

public class Piece {
    private final Rank rank;
    private final Color color;
    private final Square homeSquare;

    Piece(Color color, Rank rank, Square homeSquare) {
        if (rank == null || color == null || homeSquare == null) {
            throw new IllegalArgumentException("Constructor does not allow null(s)!");
        }
        this.rank = rank;
        this.color = color;
        this.homeSquare = homeSquare;
    }

    public Rank rank() {
        return rank;
    }

    public Color color() {
        return color;
    }

    public int points() {
        return rank.value();
    }

    public Square homeSquare() {
        return homeSquare;
    }

    public List<GameEvent> potentialGameEvents(ChessBoard chessBoard) {
        List<GameEvent> potentialGameEvents = new ArrayList<GameEvent>();
        Square mySquare = chessBoard.squareHolding(this);
        for (Square targetSquare : moveToSquares(chessBoard)) {
            potentialGameEvents.add(move(mySquare, targetSquare));
        }

        return addCaptureEvents(chessBoard, potentialGameEvents);
    }

    private List<GameEvent> addCaptureEvents(ChessBoard chessBoard, List<GameEvent> potentialGameEvents) {
        Square mySquare = chessBoard.squareHolding(this);
        for (Piece pieceAttacked : piecesAttacked(chessBoard)) {
            Square targetSquare = chessBoard.squareHolding(pieceAttacked);
            potentialGameEvents.add(capture(mySquare, targetSquare, pieceAttacked));
        }
        return potentialGameEvents;
    }

    public List<Piece> piecesAttacked(ChessBoard chessBoard) {
        List<Piece> attackedPieces = new ArrayList<Piece>();

        List<Square> squaresHoldingPiecesAttacked = squaresHoldingPiecesAttacked(chessBoard);

        for (Square square : squaresHoldingPiecesAttacked) {
            attackedPieces.add(chessBoard.pieceAt(square));
        }

        return attackedPieces;
    }

    private List<Square> squaresHoldingPiecesAttacked(ChessBoard chessBoard) {
        return myView(chessBoard).squaresHoldingPiecesAttacked();
    }

    public List<Piece> piecesDefended(ChessBoard chessBoard) {
        List<Piece> defendedPieces = new ArrayList<Piece>();

        List<Square> squaresHoldingPiecesDefended = squaresHoldingPiecesDefended(chessBoard);

        for (Square square : squaresHoldingPiecesDefended) {
            defendedPieces.add(chessBoard.pieceAt(square));
        }

        return defendedPieces;
    }

    private List<Square> squaresHoldingPiecesDefended(ChessBoard chessBoard) {
        return myView(chessBoard).squaresHoldingPiecesDefended();
    }

    public List<Piece> opponentAttackers(ChessBoard chessBoard) {
        List<Piece> opponentAttackers = new ArrayList<Piece>();
        Square mySquare = chessBoard.squareHolding(this);

        List<Piece> opponentPieces = chessBoard.piecesFor(color.opponentColor());
        for (Piece opponentPiece : opponentPieces) {
            RankView opponentView = rankView(opponentPiece, chessBoard);

            List<Square> squaresHoldingPiecesUnderAttack = opponentView.squaresHoldingPiecesAttacked();
            if (squaresHoldingPiecesUnderAttack.contains(mySquare)) {
                opponentAttackers.add(opponentPiece);
            }
        }
        return opponentAttackers;

    }

    public List<Piece> collaboratorDefenders(ChessBoard chessBoard) {
        Square mySquare = chessBoard.squareHolding(this);

        return piecesPointingAtMe(chessBoard, mySquare, chessBoard.piecesFor(color));
    }

    private List<Piece> piecesPointingAtMe(ChessBoard chessBoard, Square mySquare, List<Piece> collaborators) {
        List<Piece> collaboratorDefenders = new ArrayList<Piece>();

        for (Piece collaboratorPiece : collaborators) {
            RankView collaboratorView = rankView(collaboratorPiece, chessBoard);

            List<Square> squaresHoldingPiecesDefended = collaboratorView.squaresHoldingPiecesDefended();
            if (squaresHoldingPiecesDefended.contains(mySquare)) {
                collaboratorDefenders.add(collaboratorPiece);
            }

        }

        return collaboratorDefenders;
    }

    public List<Square> threatenedSquares(ChessBoard chessBoard) {
        return myView(chessBoard).threatenedSquares();
    }

    public List<Square> moveToSquares(ChessBoard chessBoard) {
        return myView(chessBoard).moveToSquares();
    }

    private RankView myView(ChessBoard chessBoard) {
        return rankView(this, chessBoard);
    }

    @Override
    public String toString() {
        return color + " " + rank;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((homeSquare == null) ? 0 : homeSquare.hashCode());
        result = prime * result + ((rank == null) ? 0 : rank.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piece other = (Piece) obj;
        if (color != other.color)
            return false;
        if (homeSquare == null) {
            if (other.homeSquare != null)
                return false;
        } else if (!homeSquare.equals(other.homeSquare))
            return false;
        if (rank != other.rank)
            return false;
        return true;
    }

}
