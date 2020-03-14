package hu.ak_akademia.blackjack.gameround;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.constans.Constans;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;
import hu.ak_akademia.blackjack.gamerdistribution.Participants;

public class GameRound {

	private Participants participants;
	private Carddeck carddeck;

	public GameRound(Participants participants) {
		this.participants = participants;
		carddeck = new Carddeck();
		carddeck.shuffle();
	}

	public void hit() {
		hitingForPlayers();
		hitingForDiller();
	}

	private void hitingForDiller() {

		Gamer diller = participants.getDiller();
		for (int i = 0; i < diller.getCardsInHand()
				.size(); i++) {
			diller.getCardsInHand()
					.get(i)
					.setFaceUp(true);
		}
		if (diller.getPoints() == Constans.getPointOfBlackjack()) {
			diller.setState(State.BLACKJACK);
		} else {
			diller.setState(State.HITTER);
		}
		// int points=diller.getPoints();
		// megjelenítés-ablak (HITTER ablak, BUTTOM ablak - csak hitGomb, GAMERS ablak)
		while (diller.getPoints() < Constans.getPointMinForDiller()) {
			gamersHit(diller);
		}
		if (diller.getState() != State.BLACKJACK) {
			diller.setState((diller.getPoints() > Constans.getPointOfBlackjack()) ? State.BUSTED : State.STAYED);
		}
		// int points=diller.getPoints();
		// megjelenítés-ablak (HITTER ablak, BUTTOM ablak - csak ÉrtékelésGomb, GAMERS
		// ablak)
	}

	private void hitingForPlayers() {
		for (int i = 0; i < participants.getPlayers()
				.size(); i++) {
			Player player = participants.getPlayers()
					.get(i);
			if (player.getPoints() == Constans.getPointOfBlackjack()) {
				player.setState(State.BLACKJACK);
			} else {
				player.setState(State.HITTER);
			}
			// int points=player.getPoints();
			// megjelenítés-ablak (HITTER ablak, BUTTOM ablak -hit és stayGomb, GAMERS
			// ablak)
			while (player.getState() == State.HITTER) {
				// if -esemény STAY -buttom
				// player.setState(STAYED);
				// break;
				// if - esemény HIT-buttom
				gamersHit(player);
				player.setState(checkForState(player));
				// points=player.getPoints();
				// megjelenítés-ablak (HITTER ablak, BUTTOM ablak -hit és stayGomb, GAMERS
				// ablak)
			}
			// points=player.getPoints();
			// megjelenítés-ablak (HITTER ablak, BUTTOM ablak - következöGomb, GAMERS ablak)
		}
	}

	private void gamersHit(Gamer player) {
		Card card = getUpmostCard();
		player.addCard(card);
	}

	private State checkForState(Player player) {
		int points = player.getPoints();
		if (points < Constans.getPointOfBlackjack()) {
			return State.HITTER;
		}
		if (points == Constans.getPointOfBlackjack()) {
			return State.STAYED;
		} else {
			return State.BUSTED;
		}

	}

	public void initialDeal() {
		int dealCount = 1;
		do {
			dealToPlayer();
			dealToDiller(dealCount);
			dealCount++;
		} while (dealCount < 3);

	}

	private void dealToDiller(int dealCount) {
		Diller diller = participants.getDiller();
		Card card = getUpmostCard();
		if (dealCount == 2) {
			card.setFaceUp(false);
		}
		diller.addCard(card);
	}

	private void dealToPlayer() {
		for (int i = 0; i < participants.getPlayers()
				.size(); i++) {
			Player player = participants.getPlayers()
					.get(i);
			Card card = getUpmostCard();
			player.addCard(card);
		}
	}

	private Card getUpmostCard() {
		Card card = carddeck.getCarddeck()
				.get(0);
		card.setFaceUp(true);
		carddeck.removeUpmostCard();
		return card;
	}
}
