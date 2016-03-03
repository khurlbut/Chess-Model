package model.board;

import java.util.ArrayList;
import java.util.List;

import model.board.views.RankViewFactory;
import model.enums.Color;
import model.exceptions.IllegalGameEventException;
import model.piece.Piece;

public class ChessBoard {

    private final boolean boardIsSet;
    private final BackingMap backingMap;
    private List<GameEvent> gameEvents;

    public ChessBoard() {
        boardIsSet = false;
        backingMap = new BackingMap();
        gameEvents = new ArrayList<GameEvent>();
    }

    public boolean boardIsSet() {
        return boardIsSet;
    }

    public ChessBoard setBoardForGame() {
        ChessBoard chessBoard = new BoardSetter().setBoard();
        return new ChessBoard(chessBoard.backingMap, chessBoard.gameEvents, true);
    }

    public ChessBoard setBoardForGameInProgress() {
        guard_SetBoardForGameInProgress();
        return new ChessBoard(backingMap, gameEvents, true);
    }

    private ChessBoard(BackingMap backingMap, List<GameEvent> gameEvents, boolean boardIsSet) {
        this.backingMap = backingMap;
        this.gameEvents = gameEvents;
        this.boardIsSet = boardIsSet;
    }

    public ChessBoard playEvent(GameEvent event) {
        return event.playEvent(this);
    }

    public Piece pieceAt(Square square) {
        return backingMap.getPieceAt(square);
    }

    public Piece piece(GameEvent event) {
        return backingMap.getPieceAt(event.source());
    }

    public Square squareHolding(Piece piece) {
        return backingMap.getSquareHolding(piece);
    }

    public List<GameEvent> gameEvents() {
        return gameEvents;
    }

    public List<GameEvent> potentialGameEvents(Color colorMovingFirst) {
        ArrayList<GameEvent> potentialGameEvents = new ArrayList<GameEvent>();
        List<Piece> pieces = piecesFor(colorMovingFirst);
        for (Piece piece : pieces) {
            potentialGameEvents.addAll(piece.potentialGameEvents(this));
        }
        return potentialGameEvents;
    }

    public List<Piece> piecesFor(Color color) {
        return backingMap.pieces(color);
    }

    ChessBoard capture(CaptureEvent capture) {
        guard(capture);
        return new ChessBoard(newBackingMap(capture), newGameEventsList(capture), boardIsSet);
    }

    ChessBoard move(MoveEvent move) {
        guard(move);
        return new ChessBoard(newBackingMap(move), newGameEventsList(move), boardIsSet);
    }

    ChessBoard put(PutEvent put) {
        guard(put);
        return new ChessBoard(newBackingMap(put), newGameEventsList(put), boardIsSet);
    }

    ChessBoard remove(RemoveEvent remove) {
        guard(remove);
        return new ChessBoard(newBackingMap(remove), newGameEventsList(remove), boardIsSet);
    }

    private BackingMap newBackingMap(GameEvent event) {
        switch (event.type()) {
            case PUT:
                return backingMap.put(event.target(), ((PutEvent) event).piece());
            case REMOVE:
                return backingMap.remove(event.source());
            case MOVE:
                return backingMap.move(event.source(), event.target());
            case CAPTURE:
                return backingMap.capture(event.source(), event.target());
            default:
                throw new IllegalArgumentException("Event Type: " + event.type() + " Not Supported!");
        }
    }

    private List<GameEvent> newGameEventsList(GameEvent event) {
        List<GameEvent> afterGameEvents = new ArrayList<GameEvent>(gameEvents);
        afterGameEvents.add(event);

        return afterGameEvents;
    }

    private void guard(PutEvent put) {
        guard_BoardMustNotBeSet();
        if (backingMap.isOccupied(put.target())) {
            throw new IllegalArgumentException("Attempted to put a piece on an occupied square!");
        }
    }

    private void guard(RemoveEvent remove) {
        guard_BoardMustNotBeSet();
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

    private void guard_BoardMustNotBeSet() {
        if (boardIsSet) {
            throw new IllegalStateException("Attempted to put a piece on the board after it was set!");
        }
    }

    private void guard(MoveEvent move) {
        guard_BoardMustBeSet();
        if (isNotLegalMove(move)) {
            throw new IllegalGameEventException("Move is Illegal!");
        }
    }

    private void guard(CaptureEvent capture) {
        guard_BoardMustBeSet();
        if (isNotLegalCapture(capture)) {
            throw new IllegalGameEventException("Capture is Illegal!");
        }
    }

    private boolean isNotLegalMove(MoveEvent move) {
        return !isLegalMove(move);
    }

    private boolean isNotLegalCapture(CaptureEvent capture) {
        return !isLegalCapture(capture);
    }

    private boolean isLegalMove(MoveEvent event) {
        return RankViewFactory.rankView(event, this).moveToSquares().contains(event.target());
    }

    private boolean isLegalCapture(CaptureEvent event) {
        return RankViewFactory.rankView(event, this).squaresHoldingPiecesAttacked().contains(event.target());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((backingMap == null) ? 0 : backingMap.hashCode());
        result = prime * result + (boardIsSet ? 1231 : 1237);
        result = prime * result + ((gameEvents == null) ? 0 : gameEvents.hashCode());
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
        ChessBoard other = (ChessBoard) obj;
        if (backingMap == null) {
            if (other.backingMap != null)
                return false;
        } else if (!backingMap.equals(other.backingMap))
            return false;
        if (boardIsSet != other.boardIsSet)
            return false;
        if (gameEvents == null) {
            if (other.gameEvents != null)
                return false;
        } else if (!gameEvents.equals(other.gameEvents))
            return false;
        return true;
    }

}
