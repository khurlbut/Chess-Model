package model.enums;

public enum MovementType {
    SINGLE_UNIT(true), EDGE_OF_BOARD(false);

    MovementType(boolean singleUnit) {
        this.singleUnit = singleUnit;
    }

    private boolean singleUnit;

    public boolean edgeOfBoard() {
        return !singleUnit;
    }

}
