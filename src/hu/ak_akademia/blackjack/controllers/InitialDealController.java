package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class InitialDealController {

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
	private Button startGameButton;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private BorderPane dillerPane;

	@FXML
	private Label gamerNumberNameLabel;

	@FXML
	private Label cardsInHandLabel;

	@FXML
	private Label pointslabel;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToNextView(ActionEvent event) {

	}

	@FXML
	void initialize() {
	}
}
