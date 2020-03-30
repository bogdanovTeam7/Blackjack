package hu.ak_akademia.blackjack.card;

import hu.ak_akademia.blackjack.constants.Constants;

public class Card {

	private Suit suit;
	private Rank rank;
	private boolean isFaceUp = true;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public boolean isFaceUp() {
		return isFaceUp;
	}

	public void setFaceUp(boolean isFaceUp) {
		this.isFaceUp = isFaceUp;
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public String toString() {
		String symbol = "";
		if (isFaceUp) {
			symbol = rank.getSymbol() + suit.getSymbol();
		} else {
			symbol += Constants.getCardback();
		}
		return symbol;
	}

}
