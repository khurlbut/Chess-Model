package onceagain.enums;

public enum Direction {
    UP("up", 0, 1), DOWN("down", 0, -1), LEFT("left", -1, 0), RIGHT("right", 1, 0), LEFT_UP("left_up", -1, 1), RIGHT_UP(
        "right_up", 1, 1), LEFT_DOWN("left_down", -1, -1), RIGHT_DOWN("right_down", 1, -1);

    private String direction;
    private Integer horizontalDelta;
    private Integer verticalDelta;

    Direction(String dir, int horizontalDelta, int verticalDelta) {
        this.direction = dir;
        this.horizontalDelta = horizontalDelta;
        this.verticalDelta = verticalDelta;
    }

    public int horizontalDelta() {
        return horizontalDelta;
    }

    public int verticalDelta() {
        return verticalDelta;
    }

    @Override
    public String toString() {
        return direction;
    }

}
