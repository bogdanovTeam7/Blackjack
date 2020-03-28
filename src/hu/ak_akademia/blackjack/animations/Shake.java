package hu.ak_akademia.blackjack.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
	private TranslateTransition tt;

	public Shake(Node node) {
		tt = new TranslateTransition(Duration.millis(100), node);
		tt.setFromX(0);
		tt.setFromY(0);
		tt.setByX(15);
		tt.setByY(10);
		tt.setCycleCount(20);
		tt.setAutoReverse(true);
	}

	public void playAnim() {
		tt.playFromStart();
	}
}
