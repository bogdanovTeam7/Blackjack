package hu.ak_akademia.blackjack.animations;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fade {
	private FadeTransition fadeT;

	public Fade(Node node, int millis) {
		fadeT = new FadeTransition(Duration.millis(millis), node);
	}

	public void out(EventHandler<ActionEvent> event) {
		fadeT.setFromValue(1);
		fadeT.setToValue(0);
		fadeT.setOnFinished(event);
		fadeT.play();
	}

	public void in() {
		fadeT.getNode()
				.setOpacity(0);
		fadeT.setFromValue(0);
		fadeT.setToValue(1);
		fadeT.play();
	}

}
