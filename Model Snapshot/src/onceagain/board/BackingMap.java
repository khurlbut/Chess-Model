package onceagain.board;

import java.util.HashMap;
import java.util.Map;

import onceagain.Piece;

import com.google.common.collect.ImmutableMap;

final class BackingMap {

    private ImmutableMap<Square, Piece> backingMap = null;

    BackingMap() {
        this.backingMap = new ImmutableMap.Builder<Square, Piece>().build();
    }

    Piece getPieceAt(Square square) {
        return backingMap.get(square);
    }

    BackingMap put(Square square, Piece piece) {
        validatePutArgs(square, piece);
        return new BackingMap(putMap(square, piece));
    }

    private BackingMap(Map<Square, Piece> mutableMap) {
        this.backingMap = new ImmutableMap.Builder<Square, Piece>().putAll(mutableMap).build();
    }

    private Map<Square, Piece> putMap(Square square, Piece piece) {
        Map<Square, Piece> map = mutableBackingMap();

        map.put(square, piece);

        return map;
    }

    BackingMap remove(Square source) {
        validateRemoveArgs(source);
        return new BackingMap(removeMap(source));
    }

    private Map<Square, Piece> removeMap(Square square) {
        Map<Square, Piece> map = mutableBackingMap();

        map.remove(square);

        return map;
    }

    BackingMap move(Square source, Square target) {
        validateMoveArgs(source, target);
        return new BackingMap(moveMap(source, target));
    }

    BackingMap replace(Square source, Square target) {
        validateReplaceArgs(source, target);
        return new BackingMap(moveMap(source, target));
    }

    private Map<Square, Piece> moveMap(Square source, Square target) {
        Map<Square, Piece> map = mutableBackingMap();

        map.put(target, map.get(source));
        map.remove(source);

        return map;
    }

    private Map<Square, Piece> mutableBackingMap() {
        return new HashMap<Square, Piece>(backingMap);
    }

    private boolean isOnBoard(Piece piece) {
        for (Map.Entry<Square, Piece> entry : backingMap.entrySet()) {
            // Object instance equality ( == ) is intentional!
            if (entry.getValue() == piece) {
                return true;
            }
        }
        return false;
    }

    boolean isOccupied(Square square) {
        return backingMap.containsKey(square);
    }

    boolean isNotOccupied(Square target) {
        return !isOccupied(target);
    }

    boolean isEmpty() {
        return backingMap.isEmpty();
    }

    private void validatePutArgs(Square target, Piece piece) {
        if (isOccupied(target)) {
            throw new IllegalArgumentException("Attempted to put a piece on an occupied square!");
        }
        if (isOnBoard(piece)) {
            throw new IllegalArgumentException("Attempted to put the same piece on the board twice!");
        }
    }

    private void validateRemoveArgs(Square source) {
        if (isNotOccupied(source)) {
            throw new IllegalArgumentException("Attempted to remove a piece on an empty square!");
        }
    }

    private void validateMoveArgs(Square source, Square target) {
        if (isNotOccupied(source)) {
            throw new IllegalArgumentException("Attempted to move from an empty square!");
        }
        if (isOccupied(target)) {
            throw new IllegalArgumentException("Attempted to move a piece on an occupied square!");
        }
    }

    private void validateReplaceArgs(Square source, Square target) {
        if (isNotOccupied(source)) {
            throw new IllegalArgumentException("Attempted to replace from an empty square!");
        }
        if (isNotOccupied(target)) {
            throw new IllegalArgumentException("Attempted to replace on an empty square!");
        }
    }

}
