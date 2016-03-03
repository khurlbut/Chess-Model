package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewDirection;
import model.enums.ViewDistance;

public final class KnightView extends RadiatingView {

    private static final ViewDirection[] KNIGHT_DIRECTIONS = { ViewDirection.RIGHT_UP_UP, ViewDirection.RIGHT_RIGHT_UP,
            ViewDirection.RIGHT_RIGHT_DOWN, ViewDirection.RIGHT_DOWN_DOWN, ViewDirection.LEFT_DOWN_DOWN,
            ViewDirection.LEFT_LEFT_DOWN, ViewDirection.LEFT_LEFT_UP, ViewDirection.LEFT_UP_UP };

    KnightView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, KNIGHT_DIRECTIONS, ViewDistance.SINGLE_UNIT);
    }

}
