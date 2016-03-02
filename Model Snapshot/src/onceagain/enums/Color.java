package onceagain.enums;

public enum Color {
	BLACK("b"), WHITE("w");
	
	private String color;
	
	Color(String color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return color;
	}

}
