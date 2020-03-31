package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class GamerController implements Initializable {

	private Gamer gamer;
	private boolean isRatingPhase;

	public GamerController(Gamer gamer) {
		this.gamer = gamer;
	}

	public void setRatingPhase(boolean isRatingPhase) {
		this.isRatingPhase = isRatingPhase;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane gamerPane;

	@FXML
	private ImageView gamerImageView;

	@FXML
	private Label gamerNameLabel;

	@FXML
	private Label gamerStateLabel;

	@FXML
	private Label cardsInHandLable;

	@FXML
	private Label pointsLabel;

	@FXML
	private Label betOrResultInfoLabel;

	@FXML
	private Label coinsNumberLabel;

	@FXML
	private Label betOrResultNumberLabel;

	@FXML
	private Label resultLabel;

	@FXML
	private AnchorPane pointsPane;

	@FXML
	void initialize() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setGamerPane();
	}

	private void setGamerPane() {
		setgamerPaneOpacity();
		gamerImageView.setImage(new Image(gamer.getPicFileName()));
		gamerNameLabel.setText(gamer.getName());
		gamerStateLabel.setText(gamer.getState()
				.printHun());
		cardsInHandLable.setText(gamer.cardsInHandtoString());
		pointsLabel.setText(Integer.toString(gamer.getPoints()));
		pointsPane.setVisible((cardsInHandLable.getText()
				.equals("")) ? false : true);
		coinsNumberLabel.setText(Integer.toString(gamer.getCoinsInHand()));
		setBetOrResultInformation();
		setViewColor();
	}

	private void setViewColor() {
		if (gamer.getState() == State.HITTER) {
			gamerPane.setStyle("-fx-background-color: CRIMSON;");
		}
		if (gamer.getState() == State.BUSTED) {
			gamerPane.setStyle("-fx-background-color: PLUM;");
		}
	}

	private void setBetOrResultInformation() {
		if (gamer.getState() == State.APPLICANT) {
			betOrResultInfoLabel.setVisible(false);
			betOrResultNumberLabel.setVisible(false);
			resultLabel.setVisible(false);
		} else if (isRatingPhase) {
			betOrResultInfoLabel.setText("Eredmény:");
			betOrResultNumberLabel.setText("+");
			resultLabel.setText("Majd meglátjuk");
		} else {
			betOrResultInfoLabel.setText("Tét:");
			betOrResultNumberLabel.setText(Integer.toString(gamer.getCoinsInBet()));
			resultLabel.setVisible(false);
		}
	}

	private void setgamerPaneOpacity() {
		gamerPane.setOpacity((gamer.getState() == State.PARTICIPATOR) ? 0.3 : 1);
	}
}
