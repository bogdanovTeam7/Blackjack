package hu.ak_akademia.blackjack.controllers;

import java.io.IOException;
import java.util.ArrayList;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;
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

public class BetOrGameOverController {
	private int countOfGameRound;
	private Diller diller;
	private ArrayList<Player> players;

	public BetOrGameOverController(int countOfGameRound, Diller diller, ArrayList<Player> players) {
		this.countOfGameRound = countOfGameRound;
		this.diller = diller;
		this.players = players;
	}

	@FXML
	private BorderPane betOrGameOverPane;

	@FXML
	private Label betInfoLabel;

	@FXML
	private Label roundCounterLable;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button hittingViewButton;

	@FXML
	private HBox dillerHBox;

	@FXML
	private BorderPane endGamePane;

	@FXML
	private Label winnerOrLoosersLabel;

	@FXML
	private Button exitGameButton;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToHittingView() {
		Fade fade = new Fade(betOrGameOverPane, 1000);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					setNextScene();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		fade.out(event);
	}

	private void setNextScene() throws IOException {
		InitialDealController controller = new InitialDealController(players, diller, 1);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/InitialDealView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) betOrGameOverPane.getScene()
				.getWindow();
		stage.setScene(scene);
	}

	@FXML
	void exitGame() {

	}

	@FXML
	void initialize() {
		makeBets();
		setStates();
		addGamersToHBox();
		Node dillerView = getNode(diller);
		dillerHBox.getChildren()
				.add(dillerView);
		roundCounterLable.setText(Constants.getEnumerationHun(countOfGameRound) + " játszma");

		Fade fade = new Fade(betOrGameOverPane, 1000);
		fade.in();

	}

	private void setStates() {
		for (Player player : players) {
			player.setState(State.PLAYER);
		}
		diller.setState(State.DILLER);
	}

	private void makeBets() {
		for (Player player : players) {
			player.makeBet();
		}
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

}
