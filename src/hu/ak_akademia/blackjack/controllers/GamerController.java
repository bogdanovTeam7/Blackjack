package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.gamer.Gamer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class GamerController implements Initializable {

	private Gamer gamer;

	public void setGamer(Gamer gamer) {
		this.gamer = gamer;
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
	void initialize() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setGamerPane();
	}

	private void setGamerPane() {
		gamerImageView.setImage(new Image(gamer.getPicFileName()));
		gamerNameLabel.setText(gamer.getName());
		gamerStateLabel.setText(gamer.getState()
				.printHun());
		cardsInHandLable.setText(gamer.cardsInHandtoString());
		pointsLabel.setText(Integer.toString(gamer.getPoints()));
		pointsLabel.setVisible((cardsInHandLable.getText()
				.equals("")) ? false : true);
		coinsNumberLabel.setText(Integer.toString(gamer.getCoinsInHand()));
		if (false) {
			betOrResultInfoLabel.setText("Tét:");
			betOrResultNumberLabel.setText(Integer.toString(gamer.getCoinsInBet()));
			resultLabel.setVisible(false);
		} else {
			betOrResultInfoLabel.setText("Eredmény:");
			resultLabel.setText("Valami");
			resultLabel.setVisible(true);
		}
	}
}
