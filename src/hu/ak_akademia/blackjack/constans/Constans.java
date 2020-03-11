package hu.ak_akademia.blackjack.constans;

public final class Constans {

	static final char spade = '\u2660';
	static final char heart = '\u2665';
	static final char diamond = '\u2666';
	static final char club = '\u2663';

	static final int pointOfBlackjack = 21;
	static final int pointMinForDiller = 17;

	static final int startCoinsForPlayer = 4;
	static final int playersCoinsToWinTotalGame = 8;
	static final int startCoinsForDiller = 1000;

	static final int bet = 1;

	Constans() {
	}

	public static char getSpade() {
		return spade;
	}

	public static char getHeart() {
		return heart;
	}

	public static char getDiamond() {
		return diamond;
	}

	public static char getClub() {
		return club;
	}

	public static int getPointOfBlackjack() {
		return pointOfBlackjack;
	}

	public static int getPointMinForDiller() {
		return pointMinForDiller;
	}

	public static int getStartCoinsForPlayer() {
		return startCoinsForPlayer;
	}

	public static int getPlayersCoinsToWinTotalGame() {
		return playersCoinsToWinTotalGame;
	}

	public static int getStartCoinsForDiller() {
		return startCoinsForDiller;
	}

	public static int getBet() {
		return bet;
	}

}
