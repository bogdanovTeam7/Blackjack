package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.card.Carddeck;
import hu.ak_akademia.blackjack.distribution.Participants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class InitialDealController {
	@FXML
	private Participants partcipants;
	@FXML
	private Carddeck carddeck;

	public InitialDealController(Participants partcipants) {
		carddeck = new Carddeck();
		carddeck.shuffle();
		this.partcipants = partcipants;
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
	private Button startGameButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private BorderPane dillerPane;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToNextView(ActionEvent event) {
		// TODO Auto-generated method stub
	}

	@FXML
	void initialize() {
	}

}
