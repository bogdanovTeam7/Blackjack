package hu.ak_akademia.blackjack.distribution;

import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;

public class Distributor {

	private GamersDataBase gamers;
	private Participants partipants = new Participants();

	public Distributor() {
		gamers=new GamersDataBase();
	}

	public Distributor(GamersDataBase gamers) {
		this.gamers = gamers;
	}

	public GamersDataBase getGamersList() {
		return gamers;
	}

	public void setGamersList(GamersDataBase gamers) {
		this.gamers = gamers;
	}

	public Participants getPartipants() {
		return partipants;
	}

	public void setPartipants(Participants partipants) {
		this.partipants = partipants;
	}

	public void selectDiller(int index) {
		Gamer gamer = gamers.getGamers()
				.get(index);

		if (gamer.getState() == State.APPLICANT && partipants.getDiller() == null) {
			Diller diller = new Diller(gamer.getName(), gamer.getPicFileName());
			partipants.setDiller(diller);
			gamer.setState(State.DILLER);
		}
	}

	public void selectPlayer(int index) {
		Gamer gamer = gamers.getGamers()
				.get(index);

		if (gamer.getState() == State.APPLICANT) {
			Player player = new Player(gamer.getName(), gamer.getPicFileName());
			partipants.getPlayers()
					.add(player);
			gamer.setState(State.PLAYER);
		}
	}

}
