package model.enums;

public enum ViewDistance {
    SINGLE_UNIT(true), EDGE_OF_BOARD(false);

    ViewDistance(boolean singleUnit) {
        this.singleUnit = singleUnit;
    }

    private boolean singleUnit;

    public boolean edgeOfBoard() {
        return !singleUnit;
    }

}
