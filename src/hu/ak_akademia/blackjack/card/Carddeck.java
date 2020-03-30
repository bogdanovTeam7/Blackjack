package hu.ak_akademia.blackjack.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Carddeck {
	private Queue<Card> carddeck;

	public Carddeck() {
		carddeck = new LinkedList<Card>();

		Suit[] suits = Suit.values();
		Rank[] ranks = Rank.values();

		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				Card card = new Card(suits[i], ranks[j]);
				carddeck.offer(card);
			}
		}
	}

	public Queue<Card> getCarddeck() {
		return carddeck;
	}

	public void shuffle() {
		ArrayList<Card> cardsForShuffle = new ArrayList<>(carddeck);
		Collections.shuffle(cardsForShuffle);
		carddeck = new LinkedList<>(cardsForShuffle);
	}

	public Card getCard() {
		return carddeck.poll();
	}

}
