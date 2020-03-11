package application;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.card.Rank;
import hu.ak_akademia.blackjack.card.Suit;

public class MeinTest {

	public static void main(String[] args) {

		Carddeck carddeck = new Carddeck();
		ArrayList<Card> cards = carddeck.getCarddeck();
		for (Card card : cards) {
			System.out.println(card);
		}
		System.out.println("--------------------------");
		carddeck.shuffle();
		cards = carddeck.getCarddeck();
		for (Card card : cards) {
			System.out.println(card);
		}

	}

}
