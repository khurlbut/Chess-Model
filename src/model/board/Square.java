package model.board;

import model.enums.Column;
import model.enums.Row;
import model.enums.ViewVector;
import model.exceptions.ConstructorArgsExcetpion;

public class Square {

    protected final Row row;
    protected final Column col;

    public Square(Column c, Row r) {
        if (c == null || r == null) {
            throw new ConstructorArgsExcetpion("Constructor does not allow null(s)!");
        }
        col = c;
        row = r;
    }

    public Row row() {
        return row;
    }

    public Column col() {
        return col;
    }

    public Square neighbor(ViewVector boardMove) {
        Column c = horizontalNeighbor(boardMove);
        Row r = verticalNeighbor(boardMove);

        if (c == null || r == null) {
            return null;
        }
        return new Square(c, r);
    }

    private Column horizontalNeighbor(ViewVector boardMove) {
        return col.horizontalNeighbor(boardMove.horizontalDelta());
    }

    private Row verticalNeighbor(ViewVector boardMove) {
        return row.verticalNeighbor(boardMove.verticalDelta());
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
