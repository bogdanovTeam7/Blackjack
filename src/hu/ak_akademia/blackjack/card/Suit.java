package hu.ak_akademia.blackjack.card;

import hu.ak_akademia.blackjack.constants.Constants;

public enum Suit {

	SPADE, HEART, DIAMOND, CLUB;

	public char getSymbol() {

		char symbol = 0;
		switch (this) {
		case SPADE:
			symbol = Constants.getSpade();
			break;
		case HEART:
			symbol = Constants.getHeart();
			break;
		case DIAMOND:
			symbol = Constants.getDiamond();
			break;
		case CLUB:
			symbol = Constants.getClub();
			break;
		}
		return symbol;
	}
}
