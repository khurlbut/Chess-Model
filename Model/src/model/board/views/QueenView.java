package model.board.views;

import model.board.BoardPosition;
import model.enums.Color;
import model.enums.ViewDirection;

public final class QueenView extends RadiatingView {

    private static final ViewDirection[] QUEEN_MOVES = { ViewDirection.UP, ViewDirection.RIGHT_UP, ViewDirection.RIGHT,
            ViewDirection.RIGHT_DOWN, ViewDirection.DOWN, ViewDirection.LEFT_DOWN, ViewDirection.LEFT, ViewDirection.LEFT_UP };

    public QueenView(BoardPosition boardPosition, Color color) {
        super(boardPosition, color, QUEEN_MOVES);
    }

}
