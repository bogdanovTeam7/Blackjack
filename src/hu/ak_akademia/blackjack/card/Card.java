package hu.ak_akademia.blackjack.card;

public class Card {

	private Suit suit;
	private Rank rank;
	private boolean isFaceUp;

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
		symbol = rank.getSymbol() + suit.getSymbol();
		return symbol;
	}

}
