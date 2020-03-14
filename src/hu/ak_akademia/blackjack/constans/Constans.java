package hu.ak_akademia.blackjack.constans;

public final class Constans {

	private static final char spade = '\u2660';
	private static final char heart = '\u2665';
	private static final char diamond = '\u2666';
	private static final char club = '\u2663';
	private static final String cardBack = "\uD83C\uDCA0";

	private static final int pointOfBlackjack = 21;
	private static final int pointMinForDiller = 17;

	private static final int startCoinsForPlayer = 4;
	private static final int playersCoinsToWinTotalGame = 8;
	private static final int startCoinsForDiller = 1000;

	private static final int bet = 1;

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

	public static String getCardback() {
		return cardBack;
	}
	

}
