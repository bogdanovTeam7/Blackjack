package hu.ak_akademia.blackjack.gamerdistribution;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.gamer.Gamer;

public class GamersDataBase {
	
	private ArrayList<Gamer> gamers;
	
	{
		gamers=new ArrayList<>();
		gamers.add(new Gamer("An."));
		gamers.add(new Gamer("Ka."));
		gamers.add(new Gamer("Do."));
		gamers.add(new Gamer("Is."));
		gamers.add(new Gamer("Ge."));
		gamers.add(new Gamer("Bo."));
		gamers.add(new Gamer("Be."));
		gamers.add(new Gamer("Vo."));
	}

	public GamersDataBase() {
	}

	public ArrayList<Gamer> getGamers() {
		return gamers;
	}
	
	

}
