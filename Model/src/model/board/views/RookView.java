package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewDirection;

public final class RookView extends RadiatingView {

    private static final ViewDirection[] ROOK_MOVES = { ViewDirection.UP, ViewDirection.DOWN, ViewDirection.LEFT, ViewDirection.RIGHT };

    public RookView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, ROOK_MOVES);
    }

}
