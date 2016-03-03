package model.board.views;

import model.board.BoardPosition;
import model.board.ChessBoard;
import model.board.GameEvent;
import model.board.Square;
import model.enums.Color;
import model.enums.Rank;
import model.piece.Piece;

public class RankViewFactory {

    public static RankView rankView(GameEvent event, ChessBoard chessBoard) {
        return newRankView(chessBoard.pieceAt(event.source()), chessBoard);
    }

    public static RankView rankView(Piece piece, ChessBoard chessBoard) {
        return newRankView(piece, chessBoard);
    }

    private static RankView newRankView(Piece piece, ChessBoard chessBoard) {
        Rank rank = piece.rank();
        Color color = piece.color();
        Square viewPoint = chessBoard.squareHolding(piece);
        BoardPosition boardPosition = new BoardPosition(chessBoard, viewPoint);

        switch (rank) {
            case Pawn:
                return new PawnView(boardPosition, color);
            case Rook:
                return new RookView(boardPosition, color);
            case Knight:
                return new KnightView(boardPosition, color);
            case Bishop:
                return new BishopView(boardPosition, color);
            case Queen:
                return new QueenView(boardPosition, color);
            case King:
                return new KingView(boardPosition, color);
            default:
                throw new RuntimeException("This should never happen! Rank is: " + rank);
        }
    }

}
