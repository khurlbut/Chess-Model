package onceagain.board.perspectives;

import onceagain.board.Square;
import onceagain.enums.Direction;

public final class BishopPerspective extends RadiantPerspective {

    private static final Direction[] BISHOP_DIRECTIONS = { Direction.RIGHT_UP, Direction.RIGHT_DOWN,
            Direction.LEFT_UP, Direction.LEFT_DOWN };

    public BishopPerspective(Square s) {
        super(s, BISHOP_DIRECTIONS);
    }

}
