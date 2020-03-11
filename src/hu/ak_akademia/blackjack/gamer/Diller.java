package hu.ak_akademia.blackjack.gamer;

import hu.ak_akademia.blackjack.constans.Constans;

public class Diller extends Gamer {

	public Diller(String name) {
		super(name);
		coinsInHand = Constans.getStartCoinsForDiller();
		state = State.PARTICIPATOR;
	}

	@Override
	public void makeBet() {
	}

	@Override
	public void winBet(int multiplier) {
	}

	@Override
	public void lostBet() {
	}

	
}
