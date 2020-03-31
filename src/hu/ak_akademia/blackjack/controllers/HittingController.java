package hu.ak_akademia.blackjack.controllers;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class HittingController {
	private int countOfGameRound;
	private Carddeck carddeck;
	private Diller diller;
	private ArrayList<Player> players;
	private int currentHitter;
	private boolean isBetDone;

	public HittingController(int countOfGameRound, Carddeck carddeck, Diller diller, ArrayList<Player> players) {
		this.countOfGameRound = countOfGameRound;
		this.carddeck = carddeck;
		this.diller = diller;
		this.players = players;
	}

	public void setCurrentHitter(int currentHitter) {
		this.currentHitter = currentHitter;
	}

	public void setBetDone(boolean isBetDone) {
		this.isBetDone = isBetDone;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane distributionPane;

	@FXML
	private Label roundCounterLable;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button hitingButton;

	@FXML
	private Button stayingButton;

	@FXML
	private Button bustedNextButton;

	@FXML
	private Button changeToResultViewButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private BorderPane dillerPane;

	@FXML
	private AnchorPane currentGamerPane;

	@FXML
	private Label gamerNumberNameLabel;

	@FXML
	private Label cardsInHandLabel;

	@FXML
	private Label pointslabel;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToNextGamerView(ActionEvent event) {

	}

	@FXML
	void changeToResultView(ActionEvent event) {

	}

	@FXML
	void hitCard(ActionEvent event) {

	}

	@FXML
	void initialize() throws IOException {
		if (!isBetDone) {
			setBets();
			isBetDone = true;
		}
		setHitter();
		setPlayersViews();
		setDillerView();
		setCurrentGamerPane();

	}

	private void setCurrentGamerPane() {
		String text = "";
		Gamer gamer = (currentHitter < players.size()) ? players.get(currentHitter) : diller;
		text = (currentHitter < players.size()) ? currentHitter + 1 + ". Játékos, " + gamer.getName() : "Osztó, " + diller.getName();
		gamerNumberNameLabel.setText(text);
		text = gamer.cardsInHandtoString()
				.toString();
		cardsInHandLabel.setText(text);
		text = "Pontszám: " + gamer.getPoints();
		pointslabel.setText(text);
	}

	private void setDillerView() {
		dillerPane.getChildren()
				.clear();
		dillerPane.getChildren()
				.add(getOneGamerView(diller));
	}

	private void setPlayersViews() {
		allPlayersHBox.getChildren()
				.clear();
		for (Player player : players) {
			Node node = getOneGamerView(player);
			allPlayersHBox.getChildren()
					.add(node);
		}
	}

	private Node getOneGamerView(Gamer gamer) {
		GamerController controller = new GamerController(gamer);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GamerView.fxml"));
		loader.setController(controller);
		Node node = new Node() {
		};
		try {
			node = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return node;
	}

	private void setHitter() {
		if (currentHitter > players.size()) {
			diller.setState(State.HITTER);
		} else {
			players.get(currentHitter)
					.setState(State.HITTER);
		}
	}

	private void setBets() {
		for (Player player : players) {
			player.makeBet();
		}
	}
}
