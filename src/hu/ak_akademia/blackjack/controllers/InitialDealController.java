package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InitialDealController {
	private Carddeck carddeck;
	private Diller diller;
	private ArrayList<Player> players;
	private int countOfGameRound;

	public InitialDealController(ArrayList<Player> players, Diller diller, int countOfGameRound) {
		this.countOfGameRound = countOfGameRound;
		carddeck = new Carddeck();
		carddeck.shuffle();
		this.diller = diller;
		this.diller.setState(State.DILLER);
		this.players = players;
		for (Player player : this.players) {
			player.setState(State.PLAYER);
		}
	}

	public int getCountOfGameRound() {
		return countOfGameRound;
	}

	public Carddeck getCarddeck() {
		return carddeck;
	}

	public Diller getDiller() {
		return diller;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane initialDealPane;

	@FXML
	private Label roundCounterLable;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button startGameButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private BorderPane dillerPane;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToNextView() {
		Fade fade = new Fade(initialDealPane, 1000);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				HittingController controller = new HittingController(countOfGameRound, carddeck, diller, players);
				controller.setCurrentHitter(0);
				GeneralStatistic statistic = new GeneralStatistic();
				controller.setGeneralStatistic(statistic);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/HittingView.fxml"));
				loader.setController(controller);
				Parent root;
				try {
					root = loader.load();
					Scene scene = new Scene(root);
					Stage stage = (Stage) initialDealPane.getScene()
							.getWindow();
					stage.setScene(scene);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		fade.out(event);
	}

	@FXML
	void initialize() {
		initialDeal();
		addGamersToHBox();
		Node dillerView = getNode(diller);
		dillerPane.setCenter(dillerView);
		roundCounterLable.setText(countOfGameRound + ". Játszma");

		Fade fade = new Fade(initialDealPane, 1000);
		fade.in();
	}

	private void addGamersToHBox() {
		allPlayersHBox.getChildren()
				.clear();
		for (Player player : players) {

			Node node = getNode(player);
			allPlayersHBox.getChildren()
					.add(node);
		}
	}

	private Node getNode(Gamer gamer) {
		GamerController gc = new GamerController(gamer);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GamerView.fxml"));
		loader.setController(gc);
		Node node = new Node() {
		};
		try {
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	private void initialDeal() {
		int round = 1;
		while (round < 3) {
			for (ListIterator<Player> iterator = players.listIterator(); iterator.hasNext();) {
				iterator.next()
						.addCard(carddeck.getCard());
			}
			Card card = carddeck.getCard();
			if (round > 1) {
				card.setFaceUp(false);
			}
			diller.addCard(card);
			round++;
		}
	}

}
