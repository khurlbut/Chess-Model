package onceagain.movements;

import onceagain.Piece;
import onceagain.board.ChessBoard;
import onceagain.board.Square;
import onceagain.board.perspectives.QueenPerspective;
import onceagain.enums.Color;
import onceagain.enums.Rank;
import events.CaptureEvent;
import events.MoveEvent;

public class QueenStrategy implements MoveStrategy {

    @Override
    public Rank rank() {
        return Rank.Queen;
    }

    @Override
    public boolean isLegalMove(MoveEvent move, ChessBoard chessBoard) {
        QueenPerspective source = new QueenPerspective(move.source());
        Square target = move.target();

        return source.radialsExcludingOccupied(chessBoard).contains(target);
    }

    @Override
    public boolean isLegalCapture(CaptureEvent capture, ChessBoard chessBoard) {
        QueenPerspective source = new QueenPerspective(capture.source());
        Square target = capture.target();
        Color opponentColor = opponentColor(target, chessBoard);

        return source.radialsOccupiedByColor(chessBoard, opponentColor).contains(target);
    }

    private Color opponentColor(Square target, ChessBoard chessBoard) {
        Piece opponentPiece = chessBoard.pieceAt(target);
        Color opponentColor = opponentPiece.color();

        return opponentColor;
    }

}
