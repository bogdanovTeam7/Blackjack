package hu.ak_akademia.blackjack.constants;

public final class Constants {

	private static final char SPADE = '\u2660';
	private static final char HEART = '\u2665';
	private static final char DIAMOND = '\u2666';
	private static final char CLUB = '\u2663';
	private static final String CARDBACK = "\uD83C\uDCA0";

	private static final int POINT_OF_BLACKJACK = 21;
	private static final int POINT_MIN_FOR_DILLER = 17;

	private static final int START_COINS_FOR_PLAYER = 4;
	private static final int PLAYERS_COINS_TO_WIN_TOTAL_GAME = 8;
	private static final int START_COINS_FOR_DILLER = 1000;

	private static final int BET = 1;

	private static final double PIC_HEIGHT_WIDTH_RATE = 1.2;

	private static final String FONT = "Comic Sans MS";


	public static char getSpade() {
		return SPADE;
	}

	public static char getHeart() {
		return HEART;
	}

	public static char getDiamond() {
		return DIAMOND;
	}

	public static char getClub() {
		return CLUB;
	}

	public static int getPointOfBlackjack() {
		return POINT_OF_BLACKJACK;
	}

	public static int getPointMinForDiller() {
		return POINT_MIN_FOR_DILLER;
	}

	public static int getStartCoinsForPlayer() {
		return START_COINS_FOR_PLAYER;
	}

	public static int getPlayersCoinsToWinTotalGame() {
		return PLAYERS_COINS_TO_WIN_TOTAL_GAME;
	}

	public static int getStartCoinsForDiller() {
		return START_COINS_FOR_DILLER;
	}

	public static int getBet() {
		return BET;
	}

	public static String getCardback() {
		return CARDBACK;
	}

	public static double getPicHeightWidthRate() {
		return PIC_HEIGHT_WIDTH_RATE;
	}

	public static String getFont() {
		return FONT;
	}

}
