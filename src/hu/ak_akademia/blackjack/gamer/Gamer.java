package hu.ak_akademia.blackjack.gamer;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Rank;
import hu.ak_akademia.blackjack.constans.Constans;

public class Gamer {

	protected String name;
	protected int coinsInHand;
	protected int coinsInBet;
	protected State state;
	protected ArrayList<Card> cardsInHand;
	protected boolean isLostGrandGame;
	protected boolean isWinGrandGame;
	protected String picFileName;

	public Gamer(String name) {
		this.name = name;
		coinsInHand = Constans.getStartCoinsForPlayer();
		state = State.APPLICANT;
		cardsInHand = new ArrayList<>();
	}

	public void makeBet() {
		coinsInHand -= Constans.getBet();
		coinsInBet += Constans.getBet();
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
		if (points > Constans.getPointOfBlackjack()) {
			int numberOfAceInHand = getNumberOfAce();
			for (int i = 0; i < numberOfAceInHand; i++) {
				points -= 10;
				if (points <= Constans.getPointOfBlackjack()) {
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
