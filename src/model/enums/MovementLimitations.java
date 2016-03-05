package model.enums;

public enum MovementLimitations {
    ONE_UNIT_SQUARE(true), EDGE_OF_BOARD(false);

    MovementLimitations(boolean oneUnitSquareOnly) {
        this.oneUnitSquareOnly = oneUnitSquareOnly;
    }

    private boolean oneUnitSquareOnly;

    public boolean edgeOfBoard() {
        return !oneUnitSquareOnly;
    }

}
