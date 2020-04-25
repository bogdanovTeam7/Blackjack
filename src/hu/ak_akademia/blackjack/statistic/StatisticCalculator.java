package hu.ak_akademia.blackjack.statistic;

import java.util.ArrayList;
import java.util.List;
import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.card.Rank;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Gamer;

public class StatisticCalculator {

	private List<Card> cardsInHand;
	private Carddeck carddeck;
	private int points;
	private int pointsByMinValueOfAces;

	public StatisticCalculator(List<Card> cardsInHand, Carddeck carddeck) {
		this.cardsInHand = cardsInHand;
		this.carddeck = carddeck;
		points = calculatePoints();
		pointsByMinValueOfAces = calculatePointsWithAces();
	}

	private int calculatePointsWithAces() {
		int countOfAcesInHand = calculateAcesInHand();
		if (countOfAcesInHand < 1) {
			return points;
		}
		return points - (countOfAcesInHand * 10);
	}

	private int calculateAcesInHand() {
		int count = 0;
		for (Card card : cardsInHand) {
			if (card.getRank() == Rank.ACE) {
				count++;
			}
		}
		return count;
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
			return 100.0;
		}
		pointsToBlackjack = Constants.getPointOfBlackjack() - pointsByMinValueOfAces;
		if (pointsToBlackjack >= 11) {
			return 0.0;
		}
		int numberOfPushingCards = calculatePushingCards(pointsToBlackjack);
		odds = numberOfPushingCards * 100.0 / carddeck.getCarddeck()
				.size();
		return odds;
	}

	private int calculatePushingCards(int pointsToBlackjack) {
		int count = 0;
		List<Card> cards = new ArrayList<>(carddeck.getCarddeck());
		for (Card card : cards) {
			if (card.getRank() != Rank.ACE && card.getRank()
					.getValue() > pointsToBlackjack) {
				count++;
			}
		}
		return count;
	}

	public double blackjackOdds() {
		double odds = -1;
		int pointsToBlackjack = Constants.getPointOfBlackjack() - points;
		if (pointsToBlackjack <= 0 && pointsToBlackjack > 11) {
			return 0.0;
		}
		int numberOfCardsNeededForBlackjack = calculateNumbersNeededForBlackjack(pointsToBlackjack);
		odds = numberOfCardsNeededForBlackjack * 100.0 / carddeck.getCarddeck()
				.size();
		return odds;
	}

	private int calculateNumbersNeededForBlackjack(int pointsToBlackjack) {
		int number = 0;
		List<Card> cards = new ArrayList<>(carddeck.getCarddeck());

		if (pointsToBlackjack > 1) {
			for (Card card : cards) {
				if (card.getRank()
						.getValue() == pointsToBlackjack) {
					number++;
				}
			}
		}
		if (pointsToBlackjack == 1) {
			for (Card card : cards) {
				if (card.getRank() == Rank.ACE) {
					number++;
				}
			}
		}
		return number;
	}

}
