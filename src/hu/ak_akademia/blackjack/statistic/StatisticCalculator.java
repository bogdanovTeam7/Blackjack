package hu.ak_akademia.blackjack.statistic;

import java.util.Iterator;
import java.util.List;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Gamer;

public class StatisticCalculator {

	private List<Card> cardsInHand;
	private Carddeck carddeck;
	private int points;

	public StatisticCalculator(List<Card> cardsInHand, Carddeck carddeck) {
		this.cardsInHand = cardsInHand;
		this.carddeck = carddeck;
		points = calculatePoints();
	}

	public int getPoints() {
		return points;
	}

	private int calculatePoints() {
		Gamer temp = new Gamer();
		temp.setCardsInHand(cardsInHand);
		return temp.getPoints();
	}

	public double pushingOdds() {
		double odds = -1;
		int pointsToBlackjack = Constants.getPointOfBlackjack() - points;
		if (pointsToBlackjack <= 0) {
			return 100;
		}
		if (pointsToBlackjack >= 11) {
			return 0;
		}
		int numberOfPushingCards = calculatePushingCards(pointsToBlackjack);

		return odds;
	}

	private int calculatePushingCards(int pointsToBlackjack) {
		int count = 0;

		// TODO Auto-generated method stub
		return 0;
	}

	public double blackjackOdds() {
		double odds = -1;
		int pointsToBlackjack = Constants.getPointOfBlackjack() - points;
		if (pointsToBlackjack <= 0) {
			return 0;
		}
		odds = calculateOdds(pointsToBlackjack);
		return odds;

	}

	private double calculateOdds(int pointsToBlackjack) {
		if (pointsToBlackjack > 11) {
			return 0;
		}
		// TODO Auto-generated method stub
		return 0;
	}

}
