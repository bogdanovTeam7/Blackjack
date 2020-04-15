package hu.ak_akademia.blackjack.controllers;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ResultController {
	private ArrayList<Player> players;
	private Diller diller;
	private int countOfGameRound;
	private GeneralStatistic generalStatistic;

	public ResultController(ArrayList<Player> players, Diller diller, int countOfGameRound, GeneralStatistic generalStatistic) {
		this.players = players;
		this.diller = diller;
		this.countOfGameRound = countOfGameRound;
		this.generalStatistic = generalStatistic;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Diller getDiller() {
		return diller;
	}

	public void setDiller(Diller diller) {
		this.diller = diller;
	}

	public int getCountOfGameRound() {
		return countOfGameRound;
	}

	public void setCountOfGameRound(int countOfGameRound) {
		this.countOfGameRound = countOfGameRound;
	}

	public GeneralStatistic getGeneralStatistic() {
		return generalStatistic;
	}

	public void setGeneralStatistic(GeneralStatistic generalStatistic) {
		this.generalStatistic = generalStatistic;
	}

	@FXML
	private BorderPane resultPane;

	@FXML
	private Label roundCounterLable;

	@FXML
	private Button statisticViewButton;

	@FXML
	private Button initialDealViewButton;

	@FXML
	private HBox dillerHBox;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToInitialDealView() throws IOException {

		countOfGameRound++;
		diller.removeAllCardsInHand();
		ArrayList<Player> playersToRemove = new ArrayList<>();
		for (Player player : players) {
			player.removeAllCardsInHand();
			if (player.isLostGrandGame()) {
				playersToRemove.add(player);
			}
		}
		players.removeAll(playersToRemove);

		Fade fade = new Fade(resultPane, 1000);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					setNextScene();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void setNextScene() throws IOException {
				BetOrGameOverController controller = new BetOrGameOverController(countOfGameRound, diller, players);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/BetOrGameOverView.fxml"));
				loader.setController(controller);
				Parent root = loader.load();
				Scene scene = new Scene(root);
				Stage stage = (Stage) resultPane.getScene()
						.getWindow();
				stage.setScene(scene);
			}
		};
		fade.out(event);
	}

	@FXML
	void changeToStatisticView() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/StatisticView.fxml"));
		StatisticViewController controller = new StatisticViewController(players, diller, countOfGameRound, generalStatistic);
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void initialize() throws IOException {

		roundCounterLable.setText(Constants.getEnumerationHun(countOfGameRound) + " játszma");
		setPlayersViews();
		setDillerView();
		for (Player player : players) {
			player.winBet();
		}

		Fade fade = new Fade(resultPane, 1000);
		fade.in();
	}

	private void setDillerView() throws IOException {
		dillerHBox.getChildren()
				.clear();
		Node node = getGamerView(diller, diller.getPoints());
		dillerHBox.getChildren()
				.add(node);
	}

	private void setPlayersViews() throws IOException {
		allPlayersHBox.getChildren()
				.clear();
		for (Player player : players) {
			Node node = getGamerView(player, diller.getPoints());
			allPlayersHBox.getChildren()
					.add(node);
		}
	}

	private Node getGamerView(Gamer gamer, int dillersPoints) throws IOException {
		GamerController controller = new GamerController(gamer);
		controller.setRatingPhase(true);
		controller.setDillersPoints(dillersPoints);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GamerView.fxml"));
		loader.setController(controller);
		Node node = loader.load();
		return node;
	}
}
