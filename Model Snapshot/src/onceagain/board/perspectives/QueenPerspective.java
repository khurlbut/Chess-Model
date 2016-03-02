package onceagain.board.perspectives;

import onceagain.board.Square;
import onceagain.enums.Direction;


public final class QueenPerspective extends RadiantPerspective {

    private static final Direction[] QUEEN_DIRECTIONS = Direction.values();

    public QueenPerspective(Square s) {
        super(s, QUEEN_DIRECTIONS);
    }

}
