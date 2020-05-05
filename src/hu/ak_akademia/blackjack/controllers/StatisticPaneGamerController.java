package hu.ak_akademia.blackjack.controllers;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ListIterator;

import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.statistic.RetrospectiveGamerStatistic;
import hu.ak_akademia.blackjack.statistic.StatisticCalculator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatisticPaneGamerController {
	private Gamer gamer;
	private RetrospectiveGamerStatistic gamerStatistic;

	public StatisticPaneGamerController(Gamer gamer, RetrospectiveGamerStatistic gamerStatistic) {
		this.gamer = gamer;
		this.gamerStatistic = gamerStatistic;
	}

	@FXML
	private Label gamerNumberLable;

	@FXML
	private GridPane gameRoundInfoGridPane;

	@FXML
	void initialize() {
		gamerNumberLable.setText(gamer.getName());

		for (int i = 1; i <= gamerStatistic.getHittingStepsSize(); i++) {
			List<Card> cardsInHand = gamerStatistic.getCardsInHand(i);
			Carddeck carddeck = gamerStatistic.getCarddeck(i);

			if (i > 1) {
				String title = Constants.getEnumerationHun(i - 1) + "\nlapkérés";
				Text hittingNumber = new Text(title);
				hittingNumber.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
				hittingNumber.setFill(Color.DARKGREEN);
				gameRoundInfoGridPane.add(hittingNumber, i, 0);
			}

			String cards = setCardsInHand(cardsInHand);
			Text currentCardsInHand = new Text(cards);
			currentCardsInHand.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			gameRoundInfoGridPane.add(currentCardsInHand, i, 1);

			StatisticCalculator statisticCalculator = new StatisticCalculator(cardsInHand, carddeck);
			Text currentPoints = new Text(statisticCalculator.getPoints() + "");
			currentPoints.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			gameRoundInfoGridPane.add(currentPoints, i, 2);

			double percent = statisticCalculator.blackjackOdds();
			String percentInString = new DecimalFormat("#.#").format(percent);
			Text blackjackOdds = new Text(percentInString + " %");
			blackjackOdds.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			gameRoundInfoGridPane.add(blackjackOdds, i, 3);

			percent = statisticCalculator.pushingOdds();
			percentInString = new DecimalFormat("#.#").format(percent);
			Text pushingOdds = new Text(percentInString + " %");
			pushingOdds.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			gameRoundInfoGridPane.add(pushingOdds, i, 4);
		}

	}

	private String setCardsInHand(List<Card> cardsInHand) {
		String cards = "";
		for (ListIterator<Card> iterator = cardsInHand.listIterator(); iterator.hasNext();) {
			if (iterator.nextIndex() != 0 && iterator.nextIndex() % 3 == 0) {
				cards += "\n";
			}
			cards += iterator.next()
					.toString();
		}
		return cards;
	}
}
