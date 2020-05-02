package hu.ak_akademia.blackjack.gamer;

public class Player extends Gamer {

	public Player(String name, String picURL, int id) {
		super(name, picURL, id);
		state = State.PARTICIPATOR;
	}

	public Player(Gamer gamer) {
		this(gamer.getName(), gamer.getPicFileName(), gamer.getId());
	}

}
