package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewVector;
import model.enums.MovementType;

public final class KnightView extends RadiatingView {

    private static final ViewVector[] KNIGHT_DIRECTIONS = { ViewVector.RIGHT_UP_UP, ViewVector.RIGHT_RIGHT_UP,
            ViewVector.RIGHT_RIGHT_DOWN, ViewVector.RIGHT_DOWN_DOWN, ViewVector.LEFT_DOWN_DOWN,
            ViewVector.LEFT_LEFT_DOWN, ViewVector.LEFT_LEFT_UP, ViewVector.LEFT_UP_UP };

    KnightView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, KNIGHT_DIRECTIONS, MovementType.SINGLE_UNIT);
    }

}
