package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StatisticPaneGeneralController {

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
	}
}
