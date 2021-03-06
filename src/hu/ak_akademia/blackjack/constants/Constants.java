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
	private static final String COLOR_HITTER = "-fx-background-color: CRIMSON;";
	private static final String COLOR_BUSTED = "-fx-background-color: LIGHTSLATEGREY;";

	private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xepdb1";
	private static final String USER = "blackjack";
	private static final String PASSWORD = "admin";

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

	public static String getColorHitter() {
		return COLOR_HITTER;
	}

	public static String getColorBusted() {
		return COLOR_BUSTED;
	}

	public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getEnumerationHun(int number) {
		switch (number) {
		case 1:
			return "Első";
		case 2:
			return "Második";
		case 3:
			return "Harmadik";
		case 4:
			return "Negyedik";
		case 5:
			return "Ötödik";
		case 6:
			return "Hatodik";
		case 7:
			return "Hetedik";
		case 8:
			return "Nyolcadik";
		case 9:
			return "Kilencedik";
		case 10:
			return "Tizedik";
		default:
			return number + "-dik";
		}
	}

	public static String getEnumerationWithArticleHun(int number) {
		String enumeration = getEnumerationHun(number).toLowerCase();
		if (enumeration.matches("^[aAáÁeÉiÍoÓöÖőŐúÚüÜűŰ].+")) {
			enumeration = "az " + enumeration;
		} else {
			enumeration = "a " + enumeration;
		}
		return enumeration;
	}

}
