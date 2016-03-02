package onceagain.enums;

public enum Rank {
	Pawn("Pawn", 1), Rook("Rook", 5), Knight("Knight", 3), Bishop("Bishop", 4), Queen("Queen", 9), King("King", 20);

	private final String title;
	private short value;

	Rank(String title, int value) {
		this.title = title;
		this.value = (short) value;
	}

	String title() {
		return title;
	}

	short value() {
		return value;
	}
}
