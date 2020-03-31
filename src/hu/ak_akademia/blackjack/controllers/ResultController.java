package hu.ak_akademia.blackjack.controllers;

import java.util.ArrayList;

import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Player;
import hu.ak_akademia.blackjack.statistic.GeneralStatistic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane hittingPane;

	@FXML
	private Label roundCounterLable;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button statisticViewButton;

	@FXML
	private Button initialDealViewButton;

	@FXML
	private HBox dillerHBox;

	@FXML
	private HBox allPlayersHBox;

	@FXML
	void changeToInitialDealView(ActionEvent event) {

	}

	@FXML
	void changeToStatisticView(ActionEvent event) {

	}

	@FXML
	void initialize() {
	}
}
