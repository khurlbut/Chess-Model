package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewVector;

public final class QueenView extends RadiatingView {

    private static final ViewVector[] QUEEN_MOVES = { ViewVector.UP, ViewVector.RIGHT_UP, ViewVector.RIGHT,
            ViewVector.RIGHT_DOWN, ViewVector.DOWN, ViewVector.LEFT_DOWN, ViewVector.LEFT, ViewVector.LEFT_UP };

    public QueenView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, QUEEN_MOVES);
    }

}
