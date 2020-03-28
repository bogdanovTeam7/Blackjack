package hu.ak_akademia.blackjack.gamer;

public class Player extends Gamer {

	public Player(String name,String picURL) {
		super(name, picURL);
		state = State.PLAYER;
	}
	
	

}
