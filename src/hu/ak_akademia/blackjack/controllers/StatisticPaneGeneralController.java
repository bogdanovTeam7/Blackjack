package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StatisticPaneGeneralController {
	private ArrayList<Player> players;
	private Diller diller;
	private int countOfGameRound;
	private GeneralStatistic generalStatistic;

	public StatisticPaneGeneralController(ArrayList<Player> players, Diller diller, int countOfGameRound, GeneralStatistic generalStatistic) {
		this.players = players;
		this.diller = diller;
		this.countOfGameRound = countOfGameRound;
		this.generalStatistic = generalStatistic;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label roundCounterLable;

	@FXML
	private GridPane gameRoundInfoGridPane;

	@FXML
	private GridPane gamersInfoGridPane;

	@FXML
	void initialize() {
		roundCounterLable.setText(Constants.getEnumerationHun(countOfGameRound) + " játszma");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm:ss");
		Text startDate = new Text(generalStatistic.getStartDate()
				.format(formatter));
		startDate.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
		gameRoundInfoGridPane.add(startDate, 1, 2);

		Text endDate = new Text(generalStatistic.getEndDate()
				.format(formatter));
		endDate.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
		gameRoundInfoGridPane.add(endDate, 1, 3);

		double percent = calculateCardsInPlay() * 100.0 / 52;
		String percentInString = new DecimalFormat("#.##").format(percent);
		Text countOfCardsInPlay = new Text(calculateCardsInPlay() + " darab, ami az egész paklinak a " + percentInString + " %-a");
		countOfCardsInPlay.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
		gameRoundInfoGridPane.add(countOfCardsInPlay, 1, 4);

		Text dillerInfo = new Text(diller.getName());
		dillerInfo.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
		gamersInfoGridPane.add(dillerInfo, 1, 2);
		dillerInfo = new Text("Osztó: ");
		dillerInfo.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
		gamersInfoGridPane.add(dillerInfo, 0, 2);

		int index = 3;
		for (ListIterator<Player> it = players.listIterator(); it.hasNext();) {
			Text playerInfo = new Text(Constants.getEnumerationHun(index - 2) + " játékos: ");
			playerInfo.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			if (index % 2 != 0) {
				playerInfo.setFill(Color.DARKVIOLET);
			}
			gamersInfoGridPane.add(playerInfo, 0, index);
			playerInfo = new Text(it.next()
					.getName());
			playerInfo.setFont(Font.font(Constants.getFont(), FontWeight.BOLD, 16));
			if (index % 2 != 0) {
				playerInfo.setFill(Color.DARKVIOLET);
			}
			gamersInfoGridPane.add(playerInfo, 1, index++);
		}
	}

	private int calculateCardsInPlay() {
		int count = 0;
		for (Player player : players) {
			count += player.getCardsInHand()
					.size();
		}
		count += diller.getCardsInHand()
				.size();
		return count;
	}
}
