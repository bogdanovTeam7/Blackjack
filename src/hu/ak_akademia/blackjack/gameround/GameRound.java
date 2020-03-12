package hu.ak_akademia.blackjack.gameround;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamerdistribution.Participants;

public class GameRound {

	private Participants participants;
	private Carddeck carddeck;

	public GameRound(Participants participants) {
		this.participants = participants;
		carddeck = new Carddeck();
		carddeck.shuffle();
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
		Card card = carddeck.getCarddeck()
				.get(0);
		if (dealCount < 2) {
			card.setFaceUp(true);
		}
		diller.addCard(card);
		carddeck.removeUpmostCard();
	}

	private void dealToPlayer() {
		for (int i = 0; i < participants.getPlayers()
				.size(); i++) {
			Player player = participants.getPlayers()
					.get(i);
			Card card = carddeck.getCarddeck()
					.get(0);
			card.setFaceUp(true);
			player.addCard(card);
			carddeck.removeUpmostCard();
		}
	}
	
	

}
