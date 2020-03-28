package hu.ak_akademia.blackjack.distribution;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.gamer.Gamer;


public class GamersDataBase {
	
	private ArrayList<Gamer> gamers;
	
	{
		gamers=new ArrayList<>();
		gamers.add(new Gamer("András","file:res/András.png"));
		gamers.add(new Gamer("Katalin","file:res/Katalin.png"));
		gamers.add(new Gamer("Dóra","file:res/Dora.png"));
		gamers.add(new Gamer("István","file:res/István.png"));
		gamers.add(new Gamer("Gergő","file:res/Gergő.png"));
		gamers.add(new Gamer("Botond","file:res/Botond.png"));
		gamers.add(new Gamer("Bence","file:res/Bence.png"));
		gamers.add(new Gamer("Vladimir","file:res/Vladimir.png"));
		
		
	}

	public GamersDataBase() {
	}

	public ArrayList<Gamer> getGamers() {
		return gamers;
	}
	
	

}
