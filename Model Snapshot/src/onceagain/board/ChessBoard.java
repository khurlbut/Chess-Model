package onceagain.board;

import onceagain.Piece;
import events.CaptureEvent;
import events.GameEvent;
import events.MoveEvent;
import events.PutEvent;
import events.RemoveEvent;
import exceptions.IllegalGameEventException;

public final class ChessBoard {

    private final BackingMap backingMap;
    private final boolean boardIsSet;

    public ChessBoard() {
        backingMap = new BackingMap();
        boardIsSet = false;
    }

    boolean boardIsSet() {
        return boardIsSet;
    }

    ChessBoard setBoardForGame() {
        return new ChessBoard(new BoardSetter().setBoard().backingMap, true);
    }

    public ChessBoard setBoardForGameInProgress() {
        guard_SetBoardForGameInProgress();
        return new ChessBoard(backingMap, true);
    }

    private ChessBoard(BackingMap backingMap, boolean boardIsSet) {
        this.backingMap = backingMap;
        this.boardIsSet = boardIsSet;
    }

    public Piece pieceAt(Square square) {
        return backingMap.getPieceAt(square);
    }

    public Piece piece(GameEvent event) {
        return backingMap.getPieceAt(event.source());
    }

    public ChessBoard playEvent(GameEvent event) {
        return event.play(this);
    }

    public ChessBoard put(PutEvent put) {
        guard(put);
        return new ChessBoard(backingMap.put(put.target(), put.piece()), boardIsSet);
    }

    public ChessBoard remove(RemoveEvent remove) {
        guard(remove);
        return new ChessBoard(backingMap.remove(remove.source()), boardIsSet);
    }

    public ChessBoard move(MoveEvent move) {
        guard_MoveMustBeLegal(move);
        return new ChessBoard(backingMap.move(move.source(), move.target()), boardIsSet);
    }

    public ChessBoard capture(CaptureEvent capture) {
        guard_CaptureMustBeLegal(capture);
        return new ChessBoard(backingMap.replace(capture.source(), capture.target()), boardIsSet);
    }

    private void guard(PutEvent put) {
        guard_BoardMustBeUnset();
        if (backingMap.isOccupied(put.target())) {
            throw new IllegalArgumentException("Attempted to put a piece on an occupied square!");
        }
    }

    private void guard(RemoveEvent remove) {
        guard_BoardMustBeUnset();
        if (backingMap.isNotOccupied(remove.source())) {
            throw new IllegalArgumentException("Attempted to remove a piece from an empty square!");
        }
    }

    private void guard_SetBoardForGameInProgress() {
        if (backingMap.isEmpty()) {
            throw new IllegalStateException("Attempted to set an empty board!");
        }
        if (boardIsSet) {
            throw new IllegalStateException("Attempted to set an already set board!");
        }
    }

    private void guard_BoardMustBeSet() {
        if (!boardIsSet) {
            throw new IllegalStateException("Attempted to move or capture a piece on the board before it was set!");
        }
    }

    private void guard_BoardMustBeUnset() {
        if (boardIsSet) {
            throw new IllegalStateException("Attempted to put a piece on the board after it was set!");
        }
    }

    private void guard_MoveMustBeLegal(MoveEvent move) {
        guard_BoardMustBeSet();
        if (isNotLegalMove(move)) {
            throw new IllegalGameEventException("Move is Illegal!");
        }
    }

    private void guard_CaptureMustBeLegal(CaptureEvent capture) {
        guard_BoardMustBeSet();
        if (isNotLegalCapture(capture)) {
            throw new IllegalGameEventException("Capture is Illegal!");
        }
    }

    private boolean isNotLegalMove(MoveEvent move) {
        return !legalMove(move);
    }

    private boolean isNotLegalCapture(CaptureEvent capture) {
        return !illegalCapture(capture);
    }

    private boolean legalMove(MoveEvent move) {
        return piece(move).isLegalMove(move, this);
    }

    private boolean illegalCapture(CaptureEvent capture) {
        return piece(capture).isLegalCapture(capture, this);
    }

}
