package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewDirection;

public final class BishopView extends RadiatingView {

    private static final ViewDirection[] BISHOP_MOVES = { ViewDirection.RIGHT_UP, ViewDirection.RIGHT_DOWN,
            ViewDirection.LEFT_UP, ViewDirection.LEFT_DOWN };

    BishopView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, BISHOP_MOVES);
    }

}
