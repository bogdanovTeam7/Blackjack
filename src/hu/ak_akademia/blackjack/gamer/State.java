package hu.ak_akademia.blackjack.gamer;

public enum State {

	APPLICANT, PARTICIPATOR, HITTER, STAYED, BUSTED, PUSHED, BLACKJACK, GAMER, PLAYER, DILLER;

	public String printHun() {
		if (this == APPLICANT) {
			return "Invitálható";
		}
		if (this == PARTICIPATOR) {
			return "Résztvevő";
		}
		if (this == HITTER) {
			return "Lapkérő";
		}
		if (this == STAYED) {
			return "Megállt";
		}
		if (this == BUSTED) {
			return "Besokallt";
		}
		if (this == PUSHED) {
			return "Döntetlen";
		}
		if (this == BLACKJACK) {
			return "Blackjack";
		}
		if (this == PLAYER) {
			return "Játékos";
		}
		if (this == DILLER) {
			return "Osztó";
		}
		return "";
	}

}
