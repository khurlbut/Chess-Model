package onceagain.board;

import onceagain.board.perspectives.QueenPerspective;
import onceagain.board.perspectives.RankPerspective;
import onceagain.enums.Column;
import onceagain.enums.Direction;
import onceagain.enums.Rank;
import onceagain.enums.Row;
import exceptions.ConstructorArgsExcetpion;

public class Square {

    private final Row row;
    private final Column col;

    public Square(Column c, Row r) {
        if (c == null || r == null) {
            throw new ConstructorArgsExcetpion("Constructor does not allow null(s)!");
        }
        row = r;
        col = c;
    }

    public Row row() {
        return row;
    }

    public Column col() {
        return col;
    }

    public Square neighbor(Direction dir) {
        Column neighborCol = col.horizontalNeighbor(dir.horizontalDelta());
        Row neighborRow = row.verticalNeighbor(dir.verticalDelta());
        if (neighborCol == null || neighborRow == null) {
            return null;
        }
        return new Square(neighborCol, neighborRow);
    }

    public RankPerspective rankPerspective(Rank rank) {
        return new QueenPerspective(this);
    }

    @Override
    public String toString() {
        return col + "_" + row;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((col == null) ? 0 : col.hashCode());
        result = prime * result + ((row == null) ? 0 : row.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Square other = (Square) obj;
        if (col != other.col)
            return false;
        if (row != other.row)
            return false;
        return true;
    }

}
