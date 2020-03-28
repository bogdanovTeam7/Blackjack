package hu.ak_akademia.blackjack.gamer;

import java.util.ArrayList;
import java.util.ListIterator;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Rank;
import hu.ak_akademia.blackjack.constants.Constants;

public class Gamer {

	protected String name;
	protected int coinsInHand;
	protected int coinsInBet;
	protected State state;
	protected ArrayList<Card> cardsInHand;
	protected boolean isLostGrandGame;
	protected boolean isWinGrandGame;
	protected String picFileName;

	public Gamer() {
	}

	public Gamer(String name, String picURL) {
		this.name = name;
		coinsInHand = Constants.getStartCoinsForPlayer();
		state = State.APPLICANT;
		cardsInHand = new ArrayList<>();
		picFileName = picURL;
	}

	public void makeBet() {
		coinsInHand -= Constants.getBet();
		coinsInBet += Constants.getBet();
	}

	public void winBet(int multiplier) {
		coinsInHand += coinsInBet * (1 + multiplier);
	}

	public void lostBet() {
		coinsInBet = 0;
	}

	public void addCard(Card card) {
		cardsInHand.add(card);
	}

	public int getPoints() {
		int points = 0;
		for (int i = 0; i < cardsInHand.size(); i++) {
			if (cardsInHand.get(i)
					.isFaceUp()) {
				points += cardsInHand.get(i)
						.getRank()
						.getValue();
			}
		}
		if (points > Constants.getPointOfBlackjack()) {
			int numberOfAceInHand = getNumberOfAce();
			for (int i = 0; i < numberOfAceInHand; i++) {
				points -= 10;
				if (points <= Constants.getPointOfBlackjack()) {
					return points;
				}
			}

		}
		return points;
	}

	private int getNumberOfAce() {
		int numberOfAce = 0;
		for (int i = 0; i < cardsInHand.size(); i++) {
			if (cardsInHand.get(i)
					.getRank() == Rank.ACE) {
				numberOfAce++;
			}
		}
		return numberOfAce;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public boolean isLostGrandGame() {
		return isLostGrandGame;
	}

	public void setLostGrandGame(boolean isLostGrandGame) {
		this.isLostGrandGame = isLostGrandGame;
	}

	public boolean isWinGrandGame() {
		return isWinGrandGame;
	}

	public void setWinGrandGame(boolean isWinGrandGame) {
		this.isWinGrandGame = isWinGrandGame;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getName() {
		return name;
	}

	public int getCoinsInHand() {
		return coinsInHand;
	}

	public int getCoinsInBet() {
		return coinsInBet;
	}

	public ArrayList<Card> getCardsInHand() {
		return cardsInHand;
	}

	public String cardsInHandtoString() {
		String cards = "";
		if (cardsInHand.size() > 0) {
			for (ListIterator<Card> iterator = cardsInHand.listIterator(); iterator.hasNext();) {
				cards += iterator.next()
						.toString();
			}
		}
		return cards;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gamer [name=");
		builder.append(name);
		builder.append(", coinsInHand=");
		builder.append(coinsInHand);
		builder.append(", coinsInBet=");
		builder.append(coinsInBet);
		builder.append(", state=");
		builder.append(state);
		builder.append(", cardsInHand=");
		builder.append(cardsInHand);
		builder.append(", isLostGrandGame=");
		builder.append(isLostGrandGame);
		builder.append(", isWinGrandGame=");
		builder.append(isWinGrandGame);
		builder.append(", picFileName=");
		builder.append(picFileName);
		builder.append("]");
		return builder.toString();
	}

}
