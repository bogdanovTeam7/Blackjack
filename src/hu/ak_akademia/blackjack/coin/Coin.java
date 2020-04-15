package hu.ak_akademia.blackjack.coin;

public class Coin {
	private String path = "file:res/chip";

	public String getPicPath(int coinAmount) {
		String fullPath = path;
		if (coinAmount < 1) {
			fullPath += "0" + ".png";
		} else if (coinAmount > 7) {
			fullPath += "8" + ".png";
		} else {
			fullPath += coinAmount + ".png";
		}
		return fullPath;
	}
}
