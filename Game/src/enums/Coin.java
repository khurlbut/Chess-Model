package enums;

public enum Coin {
    HEADS, TAILS;

    public static Coin toss() {
        double coin = Math.random();
        if (coin > 0.5) {
            return HEADS;
        } else {
            return TAILS;
        }
    }

}
