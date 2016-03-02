package onceagain.board.perspectives;

import onceagain.board.square.Square;
import onceagain.enums.Direction;

public final class RookPerspective extends RadiantPerspective {

    private static final Direction[] ROOK_DIRECTIONS = { Direction.UP, Direction.DOWN, Direction.LEFT,
            Direction.RIGHT };

    public RookPerspective(Square s) {
        super(s, ROOK_DIRECTIONS);
    }

}
