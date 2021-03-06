package hu.ak_akademia.blackjack.gamer;

import hu.ak_akademia.blackjack.constants.Constants;

public class Diller extends Gamer {

	public Diller(String name, String picURL, int id) {
		super(name, picURL, id);
		coinsInHand = Constants.getStartCoinsForDiller();
		state = State.PARTICIPATOR;
	}

	public Diller() {
	}

	public Diller(Gamer gamer) {
		this(gamer.getName(), gamer.getPicFileName(), gamer.getId());
	}

	@Override
	public void makeBet() {

	}

	@Override
	public void winBet() {
	}

}
