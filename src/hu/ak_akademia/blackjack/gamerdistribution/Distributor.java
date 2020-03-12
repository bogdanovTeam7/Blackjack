package hu.ak_akademia.blackjack.gamerdistribution;

import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;

public class Distributor {

	private GamersDataBase gamers;
	private Participants partipants;

	public Distributor(GamersDataBase gamers) {
		this.gamers = gamers;
	}

	public void selectDiller(int index) {
		Gamer gamer = gamers.getGamers()
				.get(index);

		if (gamer.getState() == State.APPLICANT && partipants.getDiller() == null) {
			Diller diller = new Diller(gamer.getName());
			partipants.setDiller(diller);
			gamer.setState(State.PARTICIPATOR);
		}
	}

	public void selectPlayer(int index) {
		Gamer gamer = gamers.getGamers()
				.get(index);

		if (gamer.getState() == State.APPLICANT) {
			Player player = new Player(gamer.getName());
			partipants.getPlayers()
					.add(player);
			gamer.setState(State.PARTICIPATOR);
		}
	}

}
