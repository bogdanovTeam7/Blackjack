package hu.ak_akademia.blackjack.card;

import java.util.ArrayList;
import java.util.Collections;

public class Carddeck {

	private ArrayList<Card> carddeck;

	public Carddeck() {
		carddeck = new ArrayList<>();
		
		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();

		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				Card card = new Card(suits[i], ranks[j]);
				carddeck.add(card);
			}
		}
	}

	public ArrayList<Card> getCarddeck() {
		return carddeck;
	}
	
	public void shuffle() {
		Collections.shuffle(carddeck);
	}

	public void removeUpmostCard() {
		carddeck.remove(0);
		carddeck.trimToSize();
	}
}
