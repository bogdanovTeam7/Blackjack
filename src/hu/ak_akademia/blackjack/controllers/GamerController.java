package hu.ak_akademia.blackjack.controllers;

import hu.ak_akademia.blackjack.coin.Coin;
import hu.ak_akademia.blackjack.constants.Constants;
import hu.ak_akademia.blackjack.gamer.Diller;
import hu.ak_akademia.blackjack.gamer.Gamer;
import hu.ak_akademia.blackjack.gamer.State;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class GamerController {

	private Gamer gamer;
	private boolean isRatingPhase;
	private int dillersPoints;

	public GamerController(Gamer gamer) {
		this.gamer = gamer;
	}

	public void setRatingPhase(boolean isRatingPhase) {
		this.isRatingPhase = isRatingPhase;
	}

	public void setDillersPoints(int dillersPoints) {
		this.dillersPoints = dillersPoints;
	}

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
	private ImageView coinPicImageView;

	@FXML
	void initialize() {
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
		setCoinNumber();
		setCoinPic(gamer.getCoinsInHand());
		setBetOrResultInformation();
		setViewColor();
	}

	private void setCoinNumber() {
		if (gamer instanceof Diller) {
			coinsNumberLabel.setText("∞");
		} else {
			coinsNumberLabel.setText(Integer.toString(gamer.getCoinsInHand()));
		}
	}

	private void setCoinPic(int coinsInHand) {
		coinPicImageView.setImage(new Image(new Coin().getPicPath(coinsInHand)));
	}

	private void setViewColor() {
		if (gamer.getState() == State.HITTER) {
			gamerPane.setStyle(Constants.getColorHitter());
		} else if (gamer.getState() == State.BUSTED) {
			gamerPane.setStyle(Constants.getColorBusted());
		}
	}

	private void setBetOrResultInformation() {
		if (gamer.getState() == State.APPLICANT) {
			betOrResultInfoLabel.setVisible(false);
			betOrResultNumberLabel.setVisible(false);
			resultLabel.setVisible(false);
		} else if (isRatingPhase) {
			if (gamer instanceof Diller) {
				betOrResultInfoLabel.setText("");
				betOrResultNumberLabel.setText("");
				resultLabel.setText("");
			} else {
				betOrResultInfoLabel.setText("Eredmény:");
				int resultInCoins = getResultInCoin();
				gamer.setCoinsInBet(resultInCoins);
				betOrResultNumberLabel.setText((resultInCoins > 0) ? "+" + resultInCoins : resultInCoins + "");
				String result = getResult(resultInCoins);
				resultLabel.setText(result);
			}
		} else {
			betOrResultInfoLabel.setText("Tét:");
			betOrResultNumberLabel.setText(Integer.toString(gamer.getCoinsInBet()));
			resultLabel.setVisible(false);
		}
	}

	private String getResult(int resultInCoins) {
		if (resultInCoins > 0) {
			return "Nyereség";
		}
		if (resultInCoins < 0) {
			return "Veszteség";
		}
		return "Döntetlen";
	}

	private int getResultInCoin() {
		if (gamer.getPoints() > Constants.getPointOfBlackjack()) {
			return -1;
		} else if (gamer.getState() == State.BLACKJACK) {
			return 2;
		} else if (dillersPoints > Constants.getPointOfBlackjack()) {
			return 1;
		} else if (gamer.getPoints() < dillersPoints) {
			return -1;
		} else if (gamer.getPoints() > dillersPoints) {
			return 1;
		} else {
			return 0;
		}
	}

	private void setgamerPaneOpacity() {
		gamerPane.setOpacity((gamer.getState() == State.PARTICIPATOR) ? 0.3 : 1);
	}
}
