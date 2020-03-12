package hu.ak_akademia.blackjack.gamerdistribution;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Player;

public class Participants {
	
	private ArrayList<Player> players;
	private Diller diller;
	
	public Participants() {
		players=new ArrayList<>();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Diller getDiller() {
		return diller;
	}

	public void setDiller(Diller diller) {
		this.diller = diller;
	}
	
	

}
