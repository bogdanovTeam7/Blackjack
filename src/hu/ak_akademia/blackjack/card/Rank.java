package hu.ak_akademia.blackjack.card;

public enum Rank {

	ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO;

	public int getValue() {
		int value = 10;

		if (this == ACE) {
			value = 11;
		}
		if (this == NINE) {
			value = 9;
		}
		if (this == EIGHT) {
			value = 8;
		}
		if (this == SEVEN) {
			value = 7;
		}
		if (this == SIX) {
			value = 6;
		}
		if (this == FIVE) {
			value = 5;
		}
		if (this == FOUR) {
			value = 4;
		}
		if (this == THREE) {
			value = 3;
		}
		if (this == TWO) {
			value = 2;
		}
		return value;
	}

	public String getSymbol() {
		String symbol = "";

		if (this == ACE) {
			symbol = "A";
		}
		if (this == KING) {
			symbol = "K";
		}
		if (this == QUEEN) {
			symbol = "Q";
		}
		if (this == JACK) {
			symbol = "J";
		}
		if (this == TEN) {
			symbol = "10";
		}
		if (this == NINE) {
			symbol = "9";
		}
		if (this == EIGHT) {
			symbol = "8";
		}
		if (this == SEVEN) {
			symbol = "7";
		}
		if (this == SIX) {
			symbol = "6";
		}
		if (this == FIVE) {
			symbol = "5";
		}
		if (this == FOUR) {
			symbol = "4";
		}
		if (this == THREE) {
			symbol = "3";
		}
		if (this == TWO) {
			symbol = "2";
		}
		return symbol;
	}

}
