package hu.ak_akademia.blackjack.controllers;

import java.util.ArrayList;
import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.card.Card;
import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.gamer.State;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;
import java.io.IOException;
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

public class HittingController {
	private int countOfGameRound;
	private Carddeck carddeck;
	private Diller diller;
	private ArrayList<Player> players;
	private int currentHitter;
	private boolean isBlackjackChecked;
	private GeneralStatistic generalStatistic;

	public HittingController(int countOfGameRound, Carddeck carddeck, Diller diller, ArrayList<Player> players) {
		this.countOfGameRound = countOfGameRound;
		this.carddeck = carddeck;
		this.diller = diller;
		this.players = players;
	}

	public GeneralStatistic getGeneralStatistic() {
		return generalStatistic;
	}

	public void setGeneralStatistic(GeneralStatistic generalStatistic) {
		this.generalStatistic = generalStatistic;
	}

	public void setCurrentHitter(int currentHitter) {
		this.currentHitter = currentHitter;
	}

	public void setBetDone(boolean isBetDone) {
		this.isBlackjackChecked = isBetDone;
	}

	@FXML
	private BorderPane hittingPane;

	@FXML
	private Label roundCounterLable;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button hittingButton;

	@FXML
	private Button stayingButton;

	@FXML
	private Button bustedNextButton;

	@FXML
	private Button changeToResultViewButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private HBox dillerHBox;

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
	private Label bustedInfoLabel;

	@FXML
	void changeToNextGamerView() {
		Gamer currentGamer = getCurrentGamer();
		if (currentGamer.getState() == State.HITTER) {
			currentGamer.setState(State.STAYED);
		}
		generalStatistic.add(currentGamer, carddeck);
		currentHitter++;
		try {
			refreshCurrentStage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void changeToResultView() {
		Fade fade = new Fade(hittingPane, 1000);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ResultController controller = new ResultController(players, diller, countOfGameRound, generalStatistic);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ResultView.fxml"));
				loader.setController(controller);
				try {
					Parent root = loader.load();
					Scene scene = new Scene(root);
					Stage stage = (Stage) hittingPane.getScene()
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
	void hitCard() {
		Gamer currentGamer = getCurrentGamer();
		if (currentGamer instanceof Player) {
			currentGamer.addCard(carddeck.getCard());
		} else {
			while (currentGamer.getPoints() < Constants.getPointMinForDiller()) {
				currentGamer.addCard(carddeck.getCard());
			}
			generalStatistic.add(currentGamer, carddeck);
		}
		if (currentGamer.getPoints() > Constants.getPointOfBlackjack()) {
			currentGamer.setState(State.BUSTED);
		}
		try {
			refreshCurrentStage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void refreshCurrentStage() throws IOException {
		HittingController controller = new HittingController(countOfGameRound, carddeck, diller, players);
		controller.setCurrentHitter(currentHitter);
		controller.setBetDone(true);
		controller.setGeneralStatistic(generalStatistic);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/HittingView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		Stage stage = (Stage) hittingPane.getScene()
				.getWindow();
		stage.setScene(scene);
	}

	@FXML
	void initialize() throws IOException {

		if (!isBlackjackChecked) {
			checkForBlackjack();
			isBlackjackChecked = true;
		}
		roundCounterLable.setText(Constants.getEnumerationHun(countOfGameRound) + " játszma");
		setHitter();
		setPlayersViews();
		setDillerView();
		setCurrentGamerPane();
		setMenuPane(getCurrentGamer());
		if (getCurrentGamer().getState() == State.BUSTED) {
			bustedInfoLabel.setVisible(true);
		}

		Fade fade = new Fade(hittingPane, 700);
		fade.in();
	}

	private void checkForBlackjack() {
		for (Player player : players) {
			if (player.getPoints() == Constants.getPointOfBlackjack()) {
				player.setState(State.BLACKJACK);
			}
		}
	}

	private Gamer getCurrentGamer() {
		Gamer gamer = (currentHitter < players.size()) ? players.get(currentHitter) : diller;
		return gamer;
	}

	private void setMenuPane(Gamer currentGamer) {
		if (currentGamer instanceof Diller && currentGamer.getPoints() >= Constants.getPointMinForDiller()) {
			hittingButton.setVisible(false);
			stayingButton.setVisible(false);
			bustedNextButton.setVisible(false);
			changeToResultViewButton.setVisible(true);
		} else if (currentGamer instanceof Diller) {
			hittingButton.setText("Osztó lapokat kér");
			hittingButton.setVisible(true);
			stayingButton.setVisible(false);
			bustedNextButton.setVisible(false);
			changeToResultViewButton.setVisible(false);
		} else if (currentGamer.getPoints() == Constants.getPointOfBlackjack()) {
			hittingButton.setVisible(false);
			stayingButton.setVisible(true);
			bustedNextButton.setVisible(false);
			changeToResultViewButton.setVisible(false);
		} else if (currentGamer.getState() == State.BUSTED) {
			hittingButton.setVisible(false);
			stayingButton.setVisible(false);
			bustedNextButton.setVisible(true);
			changeToResultViewButton.setVisible(false);
		} else {
			hittingButton.setVisible(true);
			stayingButton.setVisible(true);
			bustedNextButton.setVisible(false);
			changeToResultViewButton.setVisible(false);
		}
	}

	private void setCurrentGamerPane() {
		String text = "";
		Gamer gamer = getCurrentGamer();
		text = (currentHitter < players.size()) ? currentHitter + 1 + ". Játékos, " + gamer.getName() : "Osztó, " + diller.getName();
		gamerNumberNameLabel.setText(text);
		text = gamer.cardsInHandtoString()
				.toString();
		cardsInHandLabel.setText(text);
		text = "Pontszám: " + gamer.getPoints();
		pointslabel.setText(text);
	}

	private void setDillerView() {
		dillerHBox.getChildren()
				.clear();
		dillerHBox.getChildren()
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
		if (currentHitter >= players.size()) {
			for (Card card : diller.getCardsInHand()) {
				card.setFaceUp(true);
			}
			if (diller.getPoints() > Constants.getPointOfBlackjack()) {
				diller.setState(State.BUSTED);
			} else if (diller.getPoints() >= Constants.getPointMinForDiller()) {
				diller.setState(State.STAYED);
			} else if (diller.getState() == State.HITTER) {
				diller.setState(State.STAYED);
			} else {
				diller.setState(State.HITTER);
			}

		} else if (players.get(currentHitter)
				.getState() != State.BUSTED
				&& players.get(currentHitter)
						.getState() != State.BLACKJACK) {
			players.get(currentHitter)
					.setState(State.HITTER);
		}
	}

}
