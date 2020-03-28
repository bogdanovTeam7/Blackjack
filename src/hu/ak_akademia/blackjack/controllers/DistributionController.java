package hu.ak_akademia.blackjack.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import hu.ak_akademia.blackjack.animations.Fade;
import hu.ak_akademia.blackjack.distribution.Distributor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DistributionController implements Initializable, ControlledScreen {
	private Distributor distributor;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane distributionPane;

	@FXML
	private BorderPane guideMainPane;

	@FXML
	private Label menuInformationLabel;

	@FXML
	private Button startGameButton1;

	@FXML
	private BorderPane currentActionPane;

	@FXML
	private Label questionLabel;

	@FXML
	private Button startGameButton;

	@FXML
	private ComboBox<?> namesComboBox;

	@FXML
	private HBox allGamersHBox;

	@FXML
	void changeToNextView(ActionEvent event) {

	}

	@Override
	public void setScreenParent() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Fade fade = new Fade(distributionPane);
		fade.in();
	}

	@FXML
	private void goToNextScreen() {
	}

}
