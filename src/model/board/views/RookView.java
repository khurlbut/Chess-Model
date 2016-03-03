package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewVector;

public final class RookView extends RadiatingView {

    private static final ViewVector[] ROOK_MOVES = { ViewVector.UP, ViewVector.DOWN, ViewVector.LEFT, ViewVector.RIGHT };

    public RookView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, ROOK_MOVES);
    }

}
