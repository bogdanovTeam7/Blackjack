package hu.ak_akademia.blackjack.card;

import hu.ak_akademia.blackjack.constans.Constans;

public enum Suit {

	SPADE, HEART, DIAMOND, CLUB;

	public char getSymbol() {

		char symbol = 0;
		switch (this) {
		case SPADE:
			symbol = Constans.getSpade();
			break;
		case HEART:
			symbol = Constans.getHeart();
			break;
		case DIAMOND:
			symbol = Constans.getDiamond();
			break;
		case CLUB:
			symbol = Constans.getClub();
			break;
		}
		return symbol;
	}
}
