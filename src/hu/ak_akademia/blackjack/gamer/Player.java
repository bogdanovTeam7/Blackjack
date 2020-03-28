package hu.ak_akademia.blackjack.gamer;

public class Player extends Gamer {

	public Player(String name, String picURL) {
		super(name, picURL);
		state = State.PARTICIPATOR;
	}

	public Player(Gamer gamer) {
		this(gamer.getName(), gamer.getPicFileName());
	}

}
